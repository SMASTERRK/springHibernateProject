package com.census.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.census.constants.EncryptionConstants;
import com.census.constants.ErrorConstants;
import com.census.constants.LoggingConstants;
import com.census.dao.ILogin;
import com.census.database.DataProvider;
import com.census.encryption.IEncryption;
import com.census.exceptions.CensusBusinessException;
import com.census.exceptions.EncryptionException;

@Service("loginBO")
public class LoginBO {

	@Autowired
	ILogin loginImpl;
	@Autowired
	IEncryption crypting;

	public static final Logger LOG = Logger.getLogger(LoginBO.class);

	public int validateUser(String userName, String passWord)
			throws com.census.exceptions.CensusApplicationException,
			CensusBusinessException {
		LOG.info(LoggingConstants.VALIDATION_I);
		
		// User Validation for the Login BO

		int result = 0;
		if (userName.equals("") && passWord.equals("")) {
			throw new CensusBusinessException(ErrorConstants.EMPTY_FIELDS);

		} else if (userName.equals("")) {
			throw new CensusBusinessException(ErrorConstants.EMPTY_USERNAME);

		} else if (passWord.equals("")) {
			throw new CensusBusinessException(ErrorConstants.EMPTY_PASSWORD);

		} else {

			DataProvider userInfo = (DataProvider) loginImpl.validation(
					userName, passWord);

			if (userInfo == null) {
				throw new CensusBusinessException(ErrorConstants.INVALID_LOGIN);
			}
			final String secretKey = EncryptionConstants.AES_KEY;
			final String pword = passWord;
			String decryptedPassword = null;
			try {
				decryptedPassword = crypting.encrypt(pword, secretKey);
			} catch (EncryptionException e) {
				throw new CensusBusinessException(e.getLocalizedMessage());
			}
			if (!decryptedPassword.equals(userInfo.getPword())
					|| "0".equals(userInfo.getStatus())) {
				throw new CensusBusinessException(ErrorConstants.WRONG_PASSWORD);
			}

			if ("admin".equals(userInfo.getRole())) {

				result = 1;
			}
			if ("user".equals(userInfo.getRole())) {
				result = 2;
			}
		}
		LOG.info(LoggingConstants.VALIDATION_USER);

		return result;

	}

	public ArrayList<DataProvider> listInfo() {
		ArrayList<DataProvider> regList = loginImpl.registeredList();
		return regList;
	}

}