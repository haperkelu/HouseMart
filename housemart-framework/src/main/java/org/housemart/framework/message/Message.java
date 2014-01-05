/** 
* @Title: Message.java
* @Package org.housemart.framework.message
* @Description: TODO
* @author Pie.Li
* @date 2013-4-19 下午6:25:22
* @version V1.0 
*/
package org.housemart.framework.message;

/**
 * @ClassName: Message
 * @Description: TODO
 * @date 2013-4-19 下午6:25:22
 * 
 */
public interface Message {

	public long getMessageId();
	
	public int getBizType();
	
	public String getContent();
	
}
