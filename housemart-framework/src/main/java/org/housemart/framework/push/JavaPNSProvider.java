/** 
* @Title: JavaPNSProvider.java
* @Package org.housemart.framework.push
* @Description: TODO
* @author Pie.Li
* @date 2013-3-4 上午7:30:56
* @version V1.0 
*/
package org.housemart.framework.push;

import java.util.ArrayList;
import java.util.List;

import org.housemart.framework.config.SecurityConfigService;
import org.housemart.framework.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

/**
 * @ClassName: JavaPNSProvider
 * @Description: TODO
 * @date 2013-3-4 上午7:30:56
 * 
 */
public class JavaPNSProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JavaPNSProvider.class);	
	private static final String _path = SecurityConfigService.loadSecurityConfig("housemart.javapns.certificate.path");
	private static final String _broker_path = SecurityConfigService.loadSecurityConfig("housemart.javapns.certificate.broker.path");
	private static final String _password = SecurityConfigService.loadSecurityConfig("housemart.javapns.certificate.password");

	/**
	 * 
	 * @Title: pushMessageToAPNS
	 * @Description: TODO
	 * @param @param devideToken 客户端token串
	 * @param @param message 发送消息
	 * @param @param count 应用图标上小红圈上的数值
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public static String pushMessageToAPNS(String devideToken, String message, int count) throws Exception
	{
		return JavaPNSProvider.pushMessageToAPNS(devideToken, message, count, true);
	}
	
	/**
	 * 
	* @Title: pushMessageToAPNS
	* @Description: TODO
	* @param @param devideToken   客户端token串
	* @param @param message       发送消息
	* @param @param count         应用图标上小红圈上的数值    
	* @param @throws Exception
	* @return void
	* @throws
	 */
	public static String pushMessageToAPNS(String devideToken, String message, int count, boolean flag) throws Exception
	{
		if (message == null)
		{
			return "";
		}
		
		if (message.trim().length() == 0)
		{
			return "";
		}
		
		PushNotificationPayload payLoad = PushNotificationPayload.complex();
		payLoad.addAlert(message);
		payLoad.addBadge(count); 
		payLoad.addSound("default"); 
		
		PushNotificationManager pushManager = new PushNotificationManager();	
		if(flag == true)
		{
			pushManager.initializeConnection(new AppleNotificationServerBasicImpl(_path, _password,  true));
		}
		else
		{
			pushManager.initializeConnection(new AppleNotificationServerBasicImpl(_broker_path, _password,  true));
		}		
		
		Device device = new BasicDevice();
		logger.info("original token:" + devideToken);
		final String decodedToken = CommonUtils.decodeDeviceId(devideToken);
		device.setToken(decodedToken);
		logger.info("token:" + decodedToken);
		
		PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
		pushManager.stopConnection();
		
		List<PushedNotification> list = new ArrayList<PushedNotification>();
		list.add(notification);
		List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(list);
		
		if(!CollectionUtils.isEmpty(failedNotifications))
		{
			int failed = failedNotifications.size();  
			StringBuilder erroMsg = new StringBuilder();
			erroMsg.append("失败条数=" + failed + "; ");
			for (PushedNotification failedNotification : failedNotifications)
			{
				Device tempDevide = failedNotification.getDevice();
				erroMsg.append("deviceId=" + tempDevide.getDeviceId()
						+ "; token="  + tempDevide.getToken() + ";"
						+ "flag=" + flag);
			}
			logger.error(erroMsg.toString());
			logger.error("发送失败：" + message);
			return erroMsg.toString();
		}
		else
		{
			if (notification.isSuccessful())
			{
				logger.info("发送成功：" + message);
				return "";
			}
			else
			{
				logger.error("发送失败：" + message);
				return "发送失败";
			}
		}
	}
	
}
