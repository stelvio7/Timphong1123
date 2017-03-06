package com.nhm.timphong.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

public final class USecurity {

	private static Key __SECRETKEY;

	private static Cipher CipherEncrypt;
	private static Cipher CipherDecrypt;

	static {
		try {
			__SECRETKEY = new SecretKeySpec(USecurity.decBase64String(
					"aW5wdXRzZWNyZXRrZXllYg==").getBytes(), "AES");
			CipherEncrypt = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			CipherEncrypt.init(Cipher.ENCRYPT_MODE, __SECRETKEY);

			CipherDecrypt = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			CipherDecrypt.init(Cipher.DECRYPT_MODE, __SECRETKEY);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}

		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer
					.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}

		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}

	public static String decAESString(String src)
			throws IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return decAESString(src, "UTF-8");
	}

	public static String decAESString(String src, String charsetName)
			throws IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return new String(CipherDecrypt.doFinal(hexToByteArray(src)),
				charsetName);
	}

	public static String encAESString(String src)
			throws IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return encAESString(src, "UTF-8");
	}

	public static String encAESString(String src, String charsetName)
			throws IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return byteArrayToHex(CipherEncrypt.doFinal(src.getBytes(charsetName)));
	}

	public static String decBase64String(String src) throws Exception {
		return new String(Base64.decode(src.getBytes(), Base64.DEFAULT));
	}

	public static String EncBase64String(String src) throws Exception {
		return Base64.encodeToString(src.getBytes(), Base64.DEFAULT);
	}

	public static String decAESWithBase64(String src) {
		try {
			if (src == null || src.equals(""))
				return "";

			return decAESString(decBase64String(src));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return src;
		}
	}

	public static String encAESWithBase64(String src) {
		try {
			if (src == null || src.equals(""))
				return "";

			return EncBase64String(encAESString(src));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return src;
		}
	}

	public static String EncBase64StringWith(String src) {

		try {
			if (src == null || src.equals(""))
				return "";

			return EncBase64String(src);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return src;
		}
	}

}
