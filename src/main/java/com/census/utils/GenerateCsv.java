package com.census.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.census.constants.LoggingConstants;
import com.census.constants.MessageConstants;
import com.census.database.CensusDetails;


@Component("generateFile")
public class GenerateCsv {
	private final static Logger LOG = Logger.getLogger(GenerateCsv.class);
	public String generateCsvFile(ArrayList<CensusDetails> res) {
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
