package com.framework.common.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";// 加密/解密算法/工作模式/填充方式

	public static final String KEY_ALGORITHM = "AES";// 密钥算法

	public byte[] initKey(String password) {
		byte key[] = null;
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance(KEY_ALGORITHM);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			keyGen.init(128, secureRandom);
			SecretKey secretKey = keyGen.generateKey();
			key = secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return key;
	}

	public String encrypt(byte input[], byte[] key) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
			Cipher c1 = Cipher.getInstance(CIPHER_ALGORITHM);
			c1.init(1, secretKey);
			byte cipherByte[] = c1.doFinal(input);
			return byte2hex(cipherByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] decrypt(String input, byte[] key) {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		try {
			Cipher c1 = Cipher.getInstance(CIPHER_ALGORITHM);
			c1.init(2, secretKey);
			return c1.doFinal(hex2byte(input));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String byte2hex(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();

	}

	private byte[] hex2byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;

		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
