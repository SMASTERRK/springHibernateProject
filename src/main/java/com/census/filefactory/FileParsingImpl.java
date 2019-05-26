package com.census.filefactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service("fileparser")
public class FileParsingImpl implements IFileParser{

	@Override
	public ArrayList<String> fileSplitter(String fileName)
			throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
