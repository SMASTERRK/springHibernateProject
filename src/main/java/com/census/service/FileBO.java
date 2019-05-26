package com.census.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.census.constants.LoggingConstants;
import com.census.constants.MessageConstants;
import com.census.dao.IFileDao;
import com.census.exceptions.CensusException;


@Service("fileBO")
public class FileBO {
	public static final Logger LOG=Logger.getLogger(FileBO.class); 
	@Autowired
	IFileDao filedao;
	public String fileProcessing(final String fileName) {
		ArrayList<String> list = new ArrayList<String>();
		String result=null;
		if (fileName != null) {
			try {
				list = filedao.fileParsing(fileName);
				try {
					filedao.fileSplitter(list);
					result=MessageConstants.FILE_LOAD_S;
				} catch (FileNotFoundException e) {
					result=e.getLocalizedMessage();
				}

			} catch (CensusException e) {
				LOG.info(LoggingConstants.FILE_SERVICE_E);
				result=e.getLocalizedMessage();
			}
	}
		return result;
	}
}

