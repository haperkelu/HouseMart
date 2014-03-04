/**   
* @Title: dddd.java 
* @Package org.housemart.framework.baiduapi 
* @Description: TODO
* @author Pie.Li   
* @date 2014-3-5 上午7:15:19 
* @version V1.0   
*/
package org.housemart.framework.baiduapi;

import java.io.Serializable;

/**
 * @author Pie.Li
 *
 */
public class BaiduApiResultFragment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2995788098952998965L;
	private String cityCode;
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	
	
}
