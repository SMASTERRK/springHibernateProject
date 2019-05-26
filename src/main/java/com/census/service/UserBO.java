package com.census.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.census.bean.ModifyTO;
import com.census.constants.LoggingConstants;
import com.census.constants.MessageConstants;
import com.census.constants.QueryConstants;
import com.census.dao.IUserModificationDao;
import com.census.database.CensusDetails;
import com.census.database.DistrictDetails;
import com.census.database.StateDetails;
import com.census.filefactory.IFileManager;

@Service("userBO")
public class UserBO {

	@Autowired
	IUserModificationDao userModfication;
	private final static Logger LOG = Logger.getLogger(UserBO.class);
	@Autowired
	IFileManager csvfile;

	public List<StateDetails> stateList() {
		return userModfication.getStateList();

	}

	public List<DistrictDetails> getAlldistrictList(String stateName) {
		return userModfication.getdistrictList(stateName);
	}

	public List<DistrictDetails> getdistrictList() {
		return userModfication.getAlldistrictList();
	}

	public String applyConditions(ModifyTO editTO, String districtValue) {
		LOG.info(LoggingConstants.MODIFY_PROCESS_DAO);
		String result = null;
		if (editTO.getChoice() != null) {
			if (editTO.getChoice().equals("delete")) {
				result = userModfication.deleteValue(editTO, districtValue);
			} else if (editTO.getChoice().equals("insert")) {
				result = userModfication.insertValue(editTO, districtValue);

			} else if (editTO.getChoice().equals("update")) {
				result = userModfication.updateValue(editTO, districtValue);
			}
			if (result == null) {
				result = MessageConstants.MODIFY_PROCESS_E;
			}
		} else {
			result = MessageConstants.CHOOSE_MODIFY;
		}
		return result;
	}

	public ArrayList<CensusDetails> displayConditions(ModifyTO editTO,
			String districtValue, String order) {
		ArrayList<CensusDetails> list = userModfication.displayByCondition(
				editTO, districtValue, order);
		if (list == null) {
			String hql = (String) QueryConstants.ENTIRELIST;
			list = (ArrayList<CensusDetails>) userModfication
					.displayResult(hql);
		}
		return list;
	}

	public List<CensusDetails> display(String hql) {
		return userModfication.displayResult(hql);
	}

	public String downloadBySpecifications(ModifyTO editTO,
			String districtValue, String order) {
		ArrayList<CensusDetails> list = displayConditions(editTO,
				districtValue, order);
		return csvfile.generateFile(list);
	}

	public List<CensusDetails> getInfoUpdate(String districtName) {
		return userModfication.getUpdatePojo(districtName);
	}

}
