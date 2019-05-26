package com.census.dao;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.census.bean.Census;
import com.census.constants.LoggingConstants;
import com.census.constants.MessageConstants;
import com.census.database.CensusDetails;
import com.census.database.DistrictDetails;
import com.census.database.StateDetails;
import com.census.exceptions.CensusException;
import com.census.filefactory.FileFactory;
import com.census.filefactory.IFileManager;
import com.census.utils.PojoToPojo;
import com.census.utils.PopulateCensus;

@Repository("filedao")
public class FileDaoImpl implements IFileDao {

	@Autowired
	FileFactory filefactory;

	@Autowired
	SessionFactory sessionFactory;
	private final static Logger LOG = Logger.getLogger(FileDaoImpl.class);

	// Validating the File Path
	@Override
	public ArrayList<String> fileParsing(String fileName)
			throws CensusException {
		IFileManager fManager = filefactory.fileParser(fileName);
		ArrayList<String> res;

		if (fManager == null) {
			throw new CensusException(
					"CSV format error...   Enter the Correct File");
		} else {
			try {
				res = fManager.fileSplitter(fileName);
			} catch (Exception e) {
				throw new CensusException(
						"Invalid file content...    Enter the Correct File");
			}
		}
		return res;
	}

	// Gathering the File Data's
	@Override
	public ArrayList<String> fileSplitter(ArrayList<String> list)
			throws FileNotFoundException, CensusException {
		ArrayList<Census> populatedlist = PopulateCensus.storeCensus(list);
		LOG.info(LoggingConstants.DATABASE_LOADER);
		try {

			Set<String> stateList = new HashSet<String>();
			Map<String, StateDetails> stateMap = new HashMap<String, StateDetails>();
			Set<String> districtList = new HashSet<String>();
			Map<String, DistrictDetails> districtMap = new HashMap<String, DistrictDetails>();
			
			Session session = sessionFactory.openSession();
			for (Census fileRecord : populatedlist) {

				session.beginTransaction();
				StateDetails stateObj = new StateDetails();
				DistrictDetails districtObj = new DistrictDetails();
				CensusDetails censusObj = new CensusDetails();

				// Reflection based setting values into tables

				PojoToPojo.copyObject(fileRecord, stateObj);
				PojoToPojo.copyObject(fileRecord, districtObj);
				PojoToPojo.copyObject(fileRecord, censusObj);

				// Setting Corresponding values into stateObj

				// Checking for New insertion of StateName
				if (stateList.add(stateObj.getStatename())) {
					session.save(stateObj);
					stateMap.put(stateObj.getStatename(), stateObj);
				} else {
					stateObj = stateMap.get(stateObj.getStatename());
				}

				// Setting Corresponding values into districtObj

				districtObj.setStateDetails(stateObj);

				// Checking for New insertion of DistrictName
				if (districtList.add(districtObj.getDistrictName() + ":"
						+ stateObj.getStatename())) {
					session.save(districtObj);
					districtMap.put(districtObj.getDistrictName(), districtObj);

				} else {
					districtObj = districtMap
							.get(districtObj.getDistrictName());
				}

				session.save(districtObj);

				// Setting Corresponding values into stateObj

				censusObj.setStatusRecord("1");
				censusObj.setDistrictdetails(districtObj);
				session.save(censusObj);
				session.getTransaction().commit();
			}
			session.close();
		} catch (Exception e) {
			LOG.info(LoggingConstants.DATABASE_LOADER_ERROR);
			throw new CensusException(MessageConstants.FILE_LOAD_E);
		}

		return list;

	}

}
