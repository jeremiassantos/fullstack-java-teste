package br.com.souninja.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {

	public static String toMD5(String encode) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(encode.getBytes(), 0, encode.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}
}
