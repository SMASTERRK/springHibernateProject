package com.census.filefactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.census.filefactory.IFileManager;


@Repository("filefactory")
public class FileFactory {

	@Autowired
	IFileManager csvfile;
	public IFileManager fileParser(final String fileName) {
		if (fileName.contains(".csv"))

			return csvfile;
		else
			return null;
	}
}
