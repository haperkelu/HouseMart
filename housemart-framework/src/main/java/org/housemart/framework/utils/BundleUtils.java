/**
 * Created on 2013-1-19
 * 
 */
package org.housemart.framework.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

public class BundleUtils {
	private static Map<String, ResourceBundle> map = new HashMap<String, ResourceBundle>();
	private static String BizDataPath = "biz-data";

	public static String getString(String key, String bizCode) {
		String result = null;
		ResourceBundle bundle = null;

		if ((bundle = map.get(bizCode)) == null) {
			bundle = ResourceBundle.getBundle(BizDataPath + "/" + bizCode);
			map.put(bizCode, bundle);
		}

		if (bundle != null) {
			result = bundle.getString(key);
		}

		return result;
	}

	public static String getKey(String value, String keyPrefix, String bizCode) {
		String key = null;
		ResourceBundle bundle = null;

		if ((bundle = map.get(bizCode)) == null) {
			bundle = ResourceBundle.getBundle(BizDataPath + "/" + bizCode);
			map.put(bizCode, bundle);
		}

		if (bundle != null) {
			for (String keyElement : bundle.keySet()) {
				if (StringUtils.isNotBlank(keyElement)) {
					if (bundle.getString(keyElement).indexOf(value) > -1) {
						if (StringUtils.isBlank(keyPrefix)) {
							key = keyElement;
							break;
						} else {
							if (keyElement.startsWith(keyPrefix)) {
								key = keyElement;
								break;
							}
						}
					}
				}
			}
		}

		return key;
	}

}
