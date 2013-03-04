package org.housemart.pic.api;

import java.io.Serializable;

/**
 * 
* @ClassName: PicSaveResult
* @Description: TODO
* @date 2013-2-24 下午10:32:25
*
 */
public class PicSaveResult implements Serializable {
	
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 277781566789680357L;
	
	private int code;
	private String url;
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
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

}
