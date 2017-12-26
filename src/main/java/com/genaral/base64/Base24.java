package com.genaral.base64;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Base24 {
//	private static char[] sel = {
//		'B','C','D','F','G','H','J','K',
//		'M','P','Q','R','T','V','W','X',
//		'Y','2','3','4','6','7','8','9'
//	};
	 
	private static String sel = "BCDFGHJKMPQRTVWXY2346789";
//	private static String sel = "ABCDEFGHJKLMNPQRSTUVWXYZ";
	 
	public static String encode(String text) {
		int i = 0;
		int Pos = 0;
		char []Buf = new char[text.length()<<1];
 
		while ((i = Pos) < text.length()) {
			Buf[i<<1] = sel.charAt((text.charAt(Pos)) >> 4);
			Buf[(i<<1) + 1] = sel.charAt(23 - (text.charAt(Pos) & 0x0F));
			Pos++;
		}
		return new String(Buf);
	}
	 
	public static String decode(String text) {
		if (text.length() % 2 != 0)
			return null;
		int[] NPos = new int[2];
		char[] N = new char[2];
		char[] Buf = new char[text.length() >> 1];
 
		for (int i = 0; i < (text.length() >> 1); i++) {
			NPos[0] = sel.indexOf(text.charAt(i<<1));
			NPos[1] = 23 - sel.indexOf(text.charAt((i<<1) + 1));
			if (NPos[0] < 0 || NPos[1] < 0) {
				return null;
			}
			Buf[i] = ((char)((NPos[0] << 4) | NPos[1]));
		}
		return new String(Buf);
	}
    
    public static void main(String[] args) throws Exception {
    	String s = "123456";
//    	Byte[] bytes = s.getBytes();
//    	for (Byte b : bytes) {
//			System.out.println(b.ge);
//		}
    	int x = 9;
    	SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
    	List<String> test = new ArrayList<String>();
//    	for(int i=0; i<10; i++) {
    	int i=0;
    	while(true) {
    		Integer m = secureRandom.nextInt();
    		System.out.println(m);
//    		for (int j = 31; j >= 0; i--) {
//    			System.out.print(((m >>> i) & 1) + " ");
//    		}
    		String binary = m.toBinaryString(m);
//    		System.out.println("binary=="+binary);
    		String encode = encode(Base64Utils.encode(binary.getBytes()));
    		if(test.contains(encode)) {
    			System.out.println("出现重复"+i);
    			break;
    		}
//    		byte[] rsa = RSAUtils.encryptByPrivateKey(encode.getBytes(), Base64Utils.encode("sdfsdfasdfsodfu".getBytes()));
//    		RSAUtils.decryptByPrivateKey(rsa, Base64Utils.encode("sdfsdfasdfsodfu".getBytes()));
    		test.add(encode);
    		System.out.println("count=================================="+i++);
    		System.out.println("encode==="+encode);
//    		System.out.println("decode==="+decode(encode(binary)));
    	}
    	
    	
//    	Integer i = 123456;
//    	System.out.println("s==="+i.toBinaryString(1));
    }
}
