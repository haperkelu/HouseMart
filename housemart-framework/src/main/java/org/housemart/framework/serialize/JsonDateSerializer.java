/**   
* @Title: JsonDateSerializer.java 
* @Package org.housemart.framework.serialize 
* @Description: TODO
* @author Pie.Li   
* @date 2014-1-3 上午10:10:58 
* @version V1.0   
*/
package org.housemart.framework.serialize;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 
 * @author Administrator
 *
 */
public class JsonDateSerializer  extends JsonSerializer<Date>{
 
	/** **/
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
 
        String formattedDate = DATE_FORMAT.format(date); 
        gen.writeString(formattedDate);
    }
 
}

