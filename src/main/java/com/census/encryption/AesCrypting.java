package com.census.encryption;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.census.constants.ErrorConstants;
import com.census.exceptions.EncryptionException;

@Component("crypting")
public class AesCrypting implements IEncryption {
	public static final Logger LOG = Logger.getLogger(AesCrypting.class);
	private static SecretKeySpec secretKey;
	private static byte[] key;

	@Override
	public void setKey(String myKey) throws EncryptionException {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (Exception e) {
			LOG.error(ErrorConstants.ENCRYPT_SETKEY_E);
			throw new EncryptionException(ErrorConstants.APPLICATION_ERROR);
		}
	}

	@Override
	public String encrypt(String strToEncrypt, String secret)
			throws EncryptionException {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(
					cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			LOG.error(ErrorConstants.ENCRYPT_SETKEY_E);
			throw new EncryptionException(ErrorConstants.APPLICATION_ERROR);
		}

	}

	@Override
	public String decrypt(String strToDecrypt, String secret)
			throws EncryptionException {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(
					strToDecrypt)));
		} catch (Exception e) {
			LOG.error(ErrorConstants.ENCRYPT_SETKEY_E);
			throw new EncryptionException(ErrorConstants.APPLICATION_ERROR);
		}
	}
}