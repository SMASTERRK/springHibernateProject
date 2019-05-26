package com.census.utils;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.census.bean.ModifyTO;
import com.census.constants.QueryConstants;
import com.census.database.CensusDetails;
import com.census.database.DistrictDetails;
import com.census.database.StateDetails;
import com.census.service.UserBO;
@Component("listPopulate")
public class RequiredList {
	@Autowired
	UserBO userBO;

	final public  ModelAndView getEntireList(String model) {
		ModelAndView modelandview = new ModelAndView(model);
		List<StateDetails> statelist=userBO.stateList();
		List<DistrictDetails> districtList=userBO.getdistrictList();
		modelandview.addObject("statelist", statelist);
		modelandview.addObject("districtList", districtList);
		return modelandview;

	}
	final public  ModelAndView getRecordList(String model) {
		ModelAndView modelandview=getEntireList(model);
		String hql = (String) QueryConstants.ENTIRELIST;
		List<CensusDetails> entireList = userBO.display(hql);
		modelandview.addObject("entireList", entireList);
		modelandview.addObject("editTO", new ModifyTO());
		return modelandview;

	}
}
