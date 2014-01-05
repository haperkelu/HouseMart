package org.housemart.framework.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 
* @ClassName: SecurityConfigService
* @Description: TODO
* @date 2013-3-14 上午7:48:30
*
 */
public class SecurityConfigService {

	private static final Properties _props = new Properties();
	private static final String _path = "/mnt/housemart-config/security.properties";
	static {
		try {
			_props.load(new InputStreamReader(new FileInputStream(new File(_path)), "UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		};
	}
	
	/**
	 * 
	* @Title: loadSecurityConfig
	* @Description: TODO
	* @param @param key
	* @param @return
	* @return String
	* @throws
	 */
	public static String loadSecurityConfig(String key){
		return _props.getProperty(key);
	}
	
}
