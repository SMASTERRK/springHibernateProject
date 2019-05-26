package com.census.encryption;

import com.census.exceptions.EncryptionException;

public interface IEncryption {
	public abstract void setKey(String myKey)throws EncryptionException;
	public abstract String encrypt(String strToEncrypt, String secret)throws EncryptionException;
	public abstract String decrypt(String strToDecrypt, String secret)throws EncryptionException;
}
