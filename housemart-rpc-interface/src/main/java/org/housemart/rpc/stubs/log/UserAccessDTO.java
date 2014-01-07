/**   
* @Title: UserAccessDTO.java 
* @Package org.housemart.rpc.stubs.log 
* @Description: TODO
* @author Pie.Li   
* @date 2014-1-7 上午10:52:43 
* @version V1.0   
*/
package org.housemart.rpc.stubs.log;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pie.Li
 *
 */
public class UserAccessDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6727553013453814749L;

	private String url;
	private String content;
	private Date addTime;
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
}
