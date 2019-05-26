package com.census.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component("csvfile")
public class CsvFilemanagerImpl implements IFileManager {

	public ArrayList<String> fileSplitter(final String fileName)
			throws FileNotFoundException {


		final ArrayList<String> list = new ArrayList<String>();
		Scanner file = null;

		file = new Scanner(new File(fileName));

		@SuppressWarnings("unused")
		final String titleLine = file.nextLine();
		while (file.hasNextLine()) {
			list.add(file.nextLine());
		}
		file.close();
		return list;
	}

}