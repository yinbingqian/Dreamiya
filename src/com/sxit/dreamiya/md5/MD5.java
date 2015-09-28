package com.sxit.dreamiya.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对外提供getMD5(String)方法
 * 
 * @author randyjia
 * 
 */
public class MD5 {
	public static int BIT32 = 32;
	public static int BIT16 = 16;

	public static String value(String plainText, int digit) {
		String res = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			switch (digit) {
			case 16:
				res = buf.toString().substring(8, 24);
				break;
			case 32:
				res = buf.toString();
				break;
			default:
				break;
			}
			// System.out.println("result: " + buf.toString());// 32位的加密
			// System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	// public static void main(String[] args) {
	// String str = MD5.value("12345678", 32);
	// System.out.println(str);
	//
	// }
}