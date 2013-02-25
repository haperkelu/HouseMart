/** 
* @Title: HessianPicServiceInterface.java
* @Package org.housemart.pic.service
* @Description: TODO
* @author Pie.Li
* @date 2013-2-24 下午9:33:42
* @version V1.0 
*/

package org.housemart.pic.api;
/**
 * @ClassName: HessianPicServiceInterface
 * @Description: TODO
 * @date 2013-2-24 下午9:33:42
 * 
 */
public interface HessianPicServiceInterface {
	
	/**
	 * 
	* @Title: savePicToCloud
	* @Description: TODO
	* @param @param picId
	* @param @param fileName
	* @param @param image
	* @param @return
	* @return int   1:正常  2：异常
	* @throws
	 */
	PicSaveResult savePicToCloud(int picId, String fileName, String fileType, String localFilePath);
	
}
