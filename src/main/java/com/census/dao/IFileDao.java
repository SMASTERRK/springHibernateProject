package com.census.dao;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.census.exceptions.CensusException;



public interface IFileDao {
	public abstract ArrayList<String> fileSplitter(ArrayList<String> list) throws FileNotFoundException,CensusException;
	public ArrayList<String> fileParsing(final String fileName)
			throws CensusException;
}
