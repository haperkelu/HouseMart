/**   
* @Title: BaiduApiJsonResultBean.java 
* @Package org.housemart.framework.baiduapi 
* @Description: TODO
* @author Pie.Li   
* @date 2014-3-5 上午7:04:17 
* @version V1.0   
*/
package org.housemart.framework.baiduapi;

import java.io.Serializable;

/**
 * @author Pie.Li
 *
 */
public class BaiduApiJsonResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4453919035829276433L;

	private int status;
	private BaiduApiResultFragment result;
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the result
	 */
	public BaiduApiResultFragment getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(BaiduApiResultFragment result) {
		this.result = result;
	}
	
	
}


