/**   
* @Title: BaiduAPIWrapper.java 
* @Package org.housemart.framework.utils 
* @Description: TODO
* @author Pie.Li   
* @date 2014-3-4 上午7:55:11 
* @version V1.0   
*/
package org.housemart.framework.baiduapi;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.housemart.framework.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author Pie.Li
 *
 */
public class BaiduAPIWrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaiduAPIWrapper.class);
	
	private final static String URL = "http://api.map.baidu.com/geocoder/v2/?ak=sISCv8X1F06Xk0ezvCeZvmS3&output=json";
	
	public static String invokeRequestByLatLng(String lat, String lng){
		
		HttpClient client = new DefaultHttpClient();
		//location=39.983424,116.322987
		HttpGet get = new HttpGet(URL + "&location=" + lat + "," + lng);
			
		String result  = "";
		try {
			result = client.execute(get, new ResponseHandler<String>() {

				public String handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
							return new String(EntityUtils.toByteArray(entity), "UTF-8");
						}
					}
					return "";
				}
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return result;
		
	}
	
	
	public static String invokeRequestCityCodeByLatLng(String lat, String lng) {
		String result = invokeRequestByLatLng(lat, lng);
		if(!StringUtils.isEmpty(result)){
			try {
				BaiduApiJsonResultBean bean = JsonUtils.readValue(result, BaiduApiJsonResultBean.class);
				return bean.getResult().getCityCode();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}			
		}
		return null;
	}
	
}
