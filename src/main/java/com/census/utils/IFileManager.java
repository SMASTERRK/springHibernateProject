package com.census.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IFileManager {
	public ArrayList<String> fileSplitter(final String fileName)
			throws FileNotFoundException;
}
