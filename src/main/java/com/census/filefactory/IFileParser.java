package com.census.filefactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IFileParser {
	public abstract ArrayList<String> fileSplitter(String fileName) throws FileNotFoundException;
}
