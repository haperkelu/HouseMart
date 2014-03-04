/**   
* @Title: JsonUtils.java 
* @Package org.housemart.framework.utils 
* @Description: TODO
* @author Pie.Li   
* @date 2014-3-5 上午7:40:55 
* @version V1.0   
*/
package org.housemart.framework.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Pie.Li
 *
 */
public class JsonUtils {

	
	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static <T> T readValue(String content, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException{
		return mapper.readValue(content, valueType);
	}
	
	public static String writeValue(Object obj) throws JsonGenerationException, JsonMappingException, IOException{
		
		return mapper.writeValueAsString(obj);
		
	}
}
