package com.tbea.lib.ep;
import java.security.*;

import javax.crypto.spec.SecretKeySpec;

public class encryptLarger {
	

	public static byte[] encryptLarger(byte[] data, Key key) throws Exception {

	javax.crypto.Cipher rsa = javax.crypto.Cipher.getInstance("RSA");

	rsa.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
	//rsa.init(opmode, key);

	SecureRandom random = new SecureRandom();

	final byte[] secretKey = new byte[16];

	random.nextBytes(secretKey);

	final javax.crypto.Cipher aes = javax.crypto.Cipher.getInstance("AES");

	SecretKeySpec k = new SecretKeySpec(secretKey,"AES");

	aes.init(javax.crypto.Cipher.ENCRYPT_MODE, k);

	final byte[] ciphedKey = rsa.doFinal(secretKey);

	final byte[] ciphedData = aes.doFinal(data);

	byte[] result = new byte[128 + ciphedData.length];

	System.arraycopy(ciphedKey, 0, result, 0, 128);

	System.arraycopy(ciphedData, 0, result, 128,ciphedData.length);

	return result;

	}



}
