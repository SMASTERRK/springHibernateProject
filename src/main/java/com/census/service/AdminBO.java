package com.census.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.census.dao.IAdminDao;

@Service("adminBO")
public class AdminBO {
	
	@Autowired
	IAdminDao adminDaoImpl;
	public String approveSelected(final String[] listapprove) {
		@SuppressWarnings("unused")
		String result;
		return result=adminDaoImpl.approveRegistered(listapprove);
		
	}

}
