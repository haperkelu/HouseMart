/** 
* @Title: StringMessage.java
* @Package org.housemart.framework.message
* @Description: TODO
* @author Pie.Li
* @date 2013-4-19 下午6:31:20
* @version V1.0 
*/
package org.housemart.framework.message;

import java.io.Serializable;

/**
 * @ClassName: StringMessage
 * @Description: TODO
 * @date 2013-4-19 下午6:31:20
 * 
 */
public class StringMessage implements Serializable, Message {

	private String content;
	
	private int type;
	
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -7076807439925931646L;

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return this.content;
	}

	@Override
	public long getMessageId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBizType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
