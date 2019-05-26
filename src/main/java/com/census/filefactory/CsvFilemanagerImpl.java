package com.census.filefactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.census.constants.LoggingConstants;
import com.census.constants.MessageConstants;
import com.census.database.CensusDetails;

@Component("csvfile")
public class CsvFilemanagerImpl implements IFileManager {
	private final static Logger LOG = Logger.getLogger(CsvFilemanagerImpl.class);
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

	@Override
	public String generateFile(ArrayList<CensusDetails> res) {
		String result = null;
		LOG.info(LoggingConstants.DOWNLOAD);
		try {
			FileWriter writer = new FileWriter(
					"C:\\Users\\626085\\Desktop\\Assignment\\ThreeDays workout\\Download\\output.csv");

			writer.append("CENSUS_ID");
			writer.append(',');
			writer.append("STATE_NAME");
			writer.append(',');
			writer.append("DISTRICT_NAME");
			writer.append(',');
			writer.append("PERSONS");
			writer.append(',');
			writer.append("NUMBER_OF_HOUSEHOLDS");
			writer.append(',');
			writer.append("TOTAL_EDUCATED");
			writer.append(',');
			writer.append("TOTL_WORKERS");
			writer.append('\n');

			for (CensusDetails censusInfo : res) {
				writer.append(censusInfo.getCensusId());
				writer.append(',');
				writer.append(censusInfo.getDistrictdetails().getStateDetails().getStatename());
				writer.append(',');
				writer.append(censusInfo.getDistrictdetails().getDistrictName());
				writer.append(',');
				writer.append(censusInfo.getPersons().toString());
				writer.append(',');
				writer.append(censusInfo.getNumberofhouseholds().toString());
				writer.append(',');
				writer.append(censusInfo.getEducated().toString());
				writer.append(',');
				writer.append(censusInfo.getWorkers().toString());
				writer.append('\n');
			}
			result = MessageConstants.DOWNLOAD_SUCCESS;
			writer.flush();
			writer.close();
		} catch (IOException e) {
			result=MessageConstants.DOWNLOAD_E;
		}
		return result;
	}

}