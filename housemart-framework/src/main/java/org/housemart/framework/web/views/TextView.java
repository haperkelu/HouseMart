package org.housemart.framework.web.views;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class TextView extends AbstractView {
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Object path = model.get("path");
		response.setHeader("Pragma", "no-cache"); 
		
		if(path != null){
			File file = new File(path.toString());
			long contentLength = file.length();
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			ByteArrayOutputStream outstream = new ByteArrayOutputStream( contentLength > 0 ? (int) contentLength : 1024);
			
			byte[] buffer = new byte[4096];
			int len;
			while ((len = is.read(buffer)) > 0) {
	            outstream.write(buffer, 0, len);
	        }
			String ret = outstream.toString();
			outstream.close();
			is.close();
			response.getWriter().write(ret);
		} else {
			
			Object content = model.get("content");
			if(content != null){
				response.getWriter().write(content.toString());
			}
			
		}
		
        



		
	}

	
	
}
