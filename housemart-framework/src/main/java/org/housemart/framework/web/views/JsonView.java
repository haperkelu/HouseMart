/**
 * 
 */

package org.housemart.framework.web.views;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author pai.li
 *
 */
public class JsonView extends AbstractView {

	/** JSON Mapper */
	private final static ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		resp.setHeader("Pragma", "no-cache");  
		resp.setHeader("Content-Type", "application/json");
		Object jsonObj = map.get("json");
		String result = mapper.writeValueAsString(jsonObj);
		resp.getWriter().write(result);
		
	}

}
