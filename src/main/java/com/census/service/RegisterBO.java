package com.census.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.census.dao.IRegDataInsertion;
import com.census.database.DataProvider;
import com.census.exceptions.InsertionException;
import com.census.validator.UserValidator;

@Service("registerBO")
public class RegisterBO {
	@Autowired
	UserValidator regValidation;
	
	@Autowired
	IRegDataInsertion regInsert;

	public String validateRegistered(DataProvider regInfo) throws InsertionException {
		
		String result=regValidation.validateReg(regInfo);
		if(result==null){
		regInsert.updation(regInfo);
		}
		return result;
	}

}
