package org.housemart.framework.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang.StringUtils;
import org.housemart.framework.utils.CommonUtils;

/**
 * 
 * @author pai.li
 * 
 */
public class AuthenticationService {

	private Cipher cipher = null;
	private SecretKeySpec secretKey = null;
	private IvParameterSpec iv = null;
	private final static String appCodeKey = "housemart-client";

	public AuthenticationService() throws NoSuchAlgorithmException,
			NoSuchPaddingException {

		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		secretKey = new SecretKeySpec(appCodeKey.getBytes(), "AES");
		iv = new IvParameterSpec("1234567890123456".getBytes());

	}

	public String generateAppCode() throws IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		byte[] result = cipher.doFinal(("4444" + "_" + CommonUtils
				.formatDateSimply()).getBytes());
		return byte2hexString(result);

	}

	/**
	 * 
	 * @param buf
	 * @return
	 */
	private String byte2hexString(byte buf[]) {

		StringBuffer strBuf = new StringBuffer(buf.length * 2);

		for (byte item : buf) {

			if (((int) item & 0xff) < 0x10) {
				strBuf.append("0");
			}
			strBuf.append(Long.toString((int) item & 0xff, 16));
		}

		return strBuf.toString();
	}
	
	/**
	 * 
	 * @param src
	 * @return
	 */
	private byte[] hex2ByteArr(String input) {
		if (StringUtils.isEmpty(input)){
			return null;
		}
		
		byte[] encrypted = new byte[input.length() / 2];
		for (int i = 0; i < encrypted.length; i++) {
			int high = Integer.parseInt(input.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(input.substring(i * 2 + 1, i * 2 + 2), 16);

			encrypted[i] = (byte) (high * 16 + low);
		}
		return encrypted;
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws ParseException
	 */
	public boolean decryptAppCode(String input) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, ParseException {

		if (StringUtils.isEmpty(input) || input.trim().length() == 0) {
			return false;
		}
		
		byte[] originalStr = hex2ByteArr(input);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		String result =  new String(cipher.doFinal(originalStr)); 
		
		String[] resultArr = result.split("_");
		if(resultArr.length == 2) {
			int randomNum = Integer.parseInt(resultArr[0]);
			Date sendTime = CommonUtils.parseDateSimply(resultArr[1]);
			return true;
		}
		return false;
	}

}
