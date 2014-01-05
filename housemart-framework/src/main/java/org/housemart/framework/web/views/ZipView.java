/** 
* @Title: ZipView.java
* @Package org.housemart.framework.web.views
* @Description: TODO
* @author Pie.Li
* @date 2013-3-20 下午8:33:40
* @version V1.0 
*/
package org.housemart.framework.web.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/**
 * @ClassName: ZipView
 * @Description: TODO
 * @date 2013-3-20 下午8:33:40
 * 
 */
public class ZipView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = (String) model.get("path");
		response.setHeader("Content-Disposition","attachment; filename=All-Zip-File");  
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream()); 
		
		File file = new File(path);
		if (file.exists()) {
			String[] subFiles = file.list();
			File[] files = new File[subFiles.length];
			int index = 0;
			if (subFiles != null) {
				for (String item : subFiles) {
					files[index ++]=new File(path + "/" + item);
				}
				zipFile(files, "", zos);
			}
		}
	}
	
	private void zipFile(File[] subs, String baseName, ZipOutputStream zos) throws IOException {       
	      for (int i=0;i<subs.length;i++) {  
	       File f= subs[i];  
	       zos.putNextEntry(new ZipEntry(baseName + f.getName()));     
	       FileInputStream fis = new FileInputStream(f);     
	       byte[] buffer = new byte[1024];     
	       int r = 0;     
	       while ((r = fis.read(buffer)) != -1) {     
	           zos.write(buffer, 0, r);     
	       }     
	       fis.close();   
	      }  
	 }  

}
