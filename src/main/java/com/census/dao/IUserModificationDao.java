package com.census.dao;

import java.util.ArrayList;
import java.util.List;

import com.census.bean.ModifyTO;
import com.census.database.CensusDetails;
import com.census.database.DistrictDetails;
import com.census.database.StateDetails;

public interface IUserModificationDao {
	public abstract List<StateDetails> getStateList();

	public abstract List<DistrictDetails> getdistrictList(String stateName);

	public abstract List<DistrictDetails> getAlldistrictList();

	public abstract ArrayList<CensusDetails> displayResult(String hql);

	public abstract ArrayList<CensusDetails> displayByCondition(
			ModifyTO editTO, String districtValue, String order);

	public abstract List<CensusDetails> getUpdatePojo(String districtName);

	public abstract String deleteValue(ModifyTO editTO, String districtValue);

	public abstract String insertValue(ModifyTO editTO, String districtValue);

	public abstract String updateValue(ModifyTO editTO, String districtValue);

}
