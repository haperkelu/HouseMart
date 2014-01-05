package org.housemart.framework.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public class CommonUtils {

	/**
	 * 
	 * @Title: formatDateSimply
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String formatDateSimply() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());

	}

	/**
	 * 
	 * @Title: parseDateSimply
	 * @Description: TODO
	 * @param @param input
	 * @param @return
	 * @param @throws ParseException
	 * @return Date
	 * @throws
	 */
	public static Date parseDateSimply(String input) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(input);

	}

	/**
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: encodeDeviceId
	* @Description: TODO
	* @param @param deviceId
	* @param @return
	* @return String
	* @throws
	 */
	public static String decodeDeviceId(String deviceId)  {
		
		byte[] deviceBytes = null;
		try {
			deviceBytes = Base64.decodeBase64(deviceId.getBytes("UTF-8"));
		} catch (Exception e) {
		}
		StringBuffer result = new StringBuffer();
		int tmp;
		String tmpStr;
		for (int i = 0; i < deviceBytes.length; i++) {
			tmp = deviceBytes[i];
			if (tmp < 0) {
				tmp += 256;
			}

			tmpStr = Integer.toHexString(tmp);
			if (tmpStr.length() == 1) {
				result.append('0');
			}

			result.append(tmpStr);
		}

		return result.toString();
	}

}
