package com.census.dao;

import com.census.database.DataProvider;
import com.census.exceptions.InsertionException;

public interface IRegDataInsertion {
	public abstract void updation(DataProvider regInfo) throws InsertionException;
}
