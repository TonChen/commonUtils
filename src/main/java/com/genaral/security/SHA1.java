package com.genaral.security;

import com.genaral.object.ObjectUtil;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {

	public static String byte2hex(byte[] b) {
		StringBuilder sbDes = new StringBuilder();
		String tmp = null;
		for (int i = 0; i < b.length; i++) {
			tmp = (Integer.toHexString(b[i] & 0xFF));
			if (tmp.length() == 1) {
				sbDes.append("0");
			}
			sbDes.append(tmp);
		}
		return sbDes.toString();
	}
	
	public static String encrypt(String strSrc, String charset) {
		MessageDigest  digest = null;
		byte[] bt = null;
		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String strDes = null;
		if(!ObjectUtil.isEmpty(charset)) {
			bt = strSrc.getBytes(Charset.forName(charset));
		} else {
			bt = strSrc.getBytes();
		}
		digest.update(bt);
		strDes = byte2hex(digest.digest());
		return strDes;
	}
	
}
