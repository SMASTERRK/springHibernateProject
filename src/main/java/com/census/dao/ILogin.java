package com.census.dao;

import java.util.ArrayList;

import com.census.database.DataProvider;


public interface ILogin {
	public Object validation(String userName, String passWord);

	public ArrayList<DataProvider> registeredList();

}
