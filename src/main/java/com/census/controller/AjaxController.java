package com.census.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.census.bean.ModifyTO;
import com.census.constants.LoggingConstants;
import com.census.database.CensusDetails;
import com.census.database.DistrictDetails;
import com.census.service.UserBO;
import com.census.utils.RequiredList;

@Controller
public class AjaxController {
	@Autowired
	UserBO userBO;
	@Autowired
	RequiredList listPopulate;
	private final static Logger LOG = Logger.getLogger(AjaxController.class);

	// Directing to the User JSP Page for Modification of records
	@RequestMapping(value = "/editPage")
	public ModelAndView editPage() {
		LOG.info(LoggingConstants.EDITPAGE);
		ModelAndView model1;
		model1 = listPopulate.getEntireList("UserModification");
		model1.addObject("editTO", new ModifyTO());
		return model1;
	}

	// Directing to the USER JSP Page for AJAX loading
	@RequestMapping(value = "getdistrictList", method = RequestMethod.GET)
	public @ResponseBody List<DistrictDetails> getAlldistrictsList(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String stateName = request.getParameter("stateName");
		LOG.info(LoggingConstants.POPULATED_DISTRICT);
		return userBO.getAlldistrictList(stateName);
	}

	// Directing to the USER JSP Page for AJAX loading
	@RequestMapping(value = "getUpdatePojo", method = RequestMethod.GET)
	public @ResponseBody List<CensusDetails> getUpdatePojo(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<CensusDetails> result = null;
		String districtName = request.getParameter("districtName");
		if (userBO.getInfoUpdate(districtName).size() > 0) {
			result = userBO.getInfoUpdate(districtName);
		}
		return result;
	}
}
