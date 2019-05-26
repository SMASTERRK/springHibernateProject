package com.census.filefactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.census.database.CensusDetails;

public interface IFileManager {
	public ArrayList<String> fileSplitter(final String fileName)
			throws FileNotFoundException;
	public String generateFile(ArrayList<CensusDetails> res);
}
