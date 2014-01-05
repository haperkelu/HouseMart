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
import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;

/**
 * @ClassName: JavaPNSProvider
 * @Description: TODO
 * @date 2013-3-4 上午7:30:56
 * 
 */
public class BaiduYunAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(BaiduYunAPI.class);	

	private static final String _apiKey = "bFUhrrYwfv9PiNbbIFSIwhxe";
	private static final String _secretKey = "GT9odIylT6ldEjXMlhMFXNTpQhnPvtAo";
	
	private static final String _apiKey4Broker = "q6QqeiVL6y2KxSiKqy5tLU6G";
	private static final String _secretKey4Broker = "y2I6af74teWyG2xvwFhhqe6gXVRpkCkb";
	
	/**
	 * 
	* @Title: pushMessage2Android
	* @Description: TODO
	* @param @param channelId   百度Channel ID
	* @param @param userId   	百度User ID
	* @param @param messageType     消息类型 - 1: 通知, 0: 消息
	* @param @param message       发送消息
	* @param @throws Exception
	* @return void
	* @throws
	 */
	public static void pushMessage2Android(String channelId, String userId, int messageType, String message, boolean isBroker) throws Exception{
		
		ChannelKeyPair pair = new ChannelKeyPair(isBroker ? _apiKey4Broker : _apiKey, isBroker ? _secretKey4Broker : _secretKey);
		
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		try {
			
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3);	//1: web 2: pc 3:android 4:ios 5:wp		
			request.setChannelId(Long.getLong(channelId));	
			request.setUserId(userId);	 
			
			request.setMessageType(messageType);
			request.setMessage(message);
			
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
				
			logger.info(response.getSuccessAmount() + ":" + channelId + ", " + userId + ", " + messageType + ", " + message); 
			
		} catch (ChannelClientException e) {
			// 处理客户端错误异常
			logger.error(channelId + ", " + userId + ", " + messageType + ", " + message + " error: " + e.getMessage());
		} catch (ChannelServerException e) {
			logger.error(channelId + ", " + userId + ", " + messageType + ", " + message + " error: " +
				String.format("request_id: %d, error_code: %d, error_message: %s" , 
					e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
				)
			);
		}
	}
	
	public static void pushMessage2Android(String channelId, String userId, int messageType, String message) throws Exception{
		pushMessage2Android(channelId, userId, messageType, message, false);
	}
	
	public static void pushMessage2Android(int messageType, String message, boolean isBroker) throws Exception{
		
		ChannelKeyPair pair = new ChannelKeyPair(isBroker ? _apiKey4Broker : _apiKey, isBroker ? _secretKey4Broker : _secretKey);
		
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		try {
			
			PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
			request.setDeviceType(3);	//1: web 2: pc 3:android 4:ios 5:wp		
			
			request.setMessageType(messageType);
			request.setMessage(message);
			
			PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);
				
			logger.info(response.getSuccessAmount() + ": broadcast, " +  messageType + ", " + message); 
			
		} catch (ChannelClientException e) {
			// 处理客户端错误异常
			logger.error("broadcase, " + messageType + ", " + message + " error: " + e.getMessage());
		} catch (ChannelServerException e) {
			logger.error("broadcase, " + messageType + ", " + message + " error: " +
				String.format("request_id: %d, error_code: %d, error_message: %s" , 
					e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
				)
			);
		}
	}
}
