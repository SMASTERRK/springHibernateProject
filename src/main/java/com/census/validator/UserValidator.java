package com.census.validator;

import org.springframework.stereotype.Component;

import com.census.constants.MessageConstants;
import com.census.database.DataProvider;
@Component("regValidation")
public class UserValidator {

	public String validateReg(final DataProvider regInfo) {

		String result=null;
		if (!regInfo.getPword().equals(regInfo.getRpword())) {
			regInfo.setPword("");
			regInfo.setRpword("");
			result=MessageConstants.PASSWORD_E;
		}
		int compare=10;
		if (regInfo.getMonbile().length() < compare) {
			regInfo.setMonbile("");
			result=MessageConstants.MOBILE_E;
		}
		if (!regInfo.getEmail().matches("[a-zA-Z0-9]{1,}[@]{1}[a-zA-Z]{1,}.*")
				&& (!regInfo.getEmail().endsWith(".com") || !regInfo.getEmail().endsWith(".org") || !regInfo.getEmail()
						.endsWith(".net"))) {
			regInfo.setEmail("");
			result=MessageConstants.EMAIL_E;
		}
		
		return result;
	}
}
