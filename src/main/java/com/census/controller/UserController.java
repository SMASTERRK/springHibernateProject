package com.census.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.census.bean.ModifyTO;
import com.census.constants.LoggingConstants;
import com.census.constants.MessageConstants;
import com.census.database.CensusDetails;
import com.census.database.DataProvider;
import com.census.service.LoginBO;
import com.census.service.UserBO;
import com.census.utils.RequiredList;

@Controller
@SessionAttributes({ "queryDistrict", "queryOrder", "queryModifyTO", "display" })
public class UserController {
	@Autowired
	RequiredList listPopulate;
	@Autowired
	UserBO userBO;
	@Autowired
	LoginBO loginBO;
	private final static Logger LOG = Logger.getLogger(UserController.class);

	// Directing to the USER MODIFICATION JSP Page once Loading the entire FILE
	@RequestMapping(value = "/modify")
	public ModelAndView modifyValues(@ModelAttribute("editTO") ModifyTO editTO,
			@RequestParam("Idistrict") String districtValue) {
		LOG.info(LoggingConstants.EDITPAGE);
		ModelAndView model1;
		model1 = listPopulate.getEntireList("UserModification");
		String result = userBO.applyConditions(editTO, districtValue);
		model1.addObject("error", result);
		model1.addObject("editTO", new ModifyTO());
		return model1;
	}

	// Directing to the DISPLAY JSP Page once Loading the entire FILE
	@RequestMapping(value = "/displayPage")
	public ModelAndView displayPage() {
		LOG.info(LoggingConstants.DISPLAY_PAGE);

		ModelAndView model1;
		model1 = listPopulate.getRecordList("DisplayDownload");
		model1.addObject("display", "display");
		return model1;
	}

	// Directing TO the DISPLAY Page
	@RequestMapping(value = "/displayCondition")
	public ModelAndView displayCondition(
			@ModelAttribute("editTO") ModifyTO editTO,
			@RequestParam("Idistrict") String districtValue,
			@RequestParam("order") String order) {
		ModelAndView model1;
		LOG.info(LoggingConstants.DISPLAY_PAGE);
		model1 = listPopulate.getEntireList("DisplayDownload");
		ArrayList<CensusDetails> list = userBO.displayConditions(editTO,
				districtValue, order);
		model1.addObject("queryDistrict", districtValue);
		model1.addObject("queryOrder", order);
		model1.addObject("queryModifyTO", editTO);
		if (list.size() == 0) {
			model1.addObject("error", MessageConstants.EMPTY_LIST);
		}
		model1.addObject("entireList", list);
		model1.addObject("editTO", new ModifyTO());
		return model1;
	}

	// Directing from the DISPLAY Page to DOWNLOAD function
	@RequestMapping(value = "/downloadResult")
	public ModelAndView displayCondition(
			@ModelAttribute("queryDistrict") String districtValue,
			@ModelAttribute("queryOrder") String order,
			@ModelAttribute("queryModifyTO") ModifyTO editTO) {
		LOG.info(LoggingConstants.DOWNLOAD);
		ModelAndView model1;
		model1 = listPopulate.getEntireList("DisplayDownload");
		String result = userBO.downloadBySpecifications(editTO, districtValue,
				order);
		model1.addObject("error", result);
		model1.addObject("editTO", new ModifyTO());
		return model1;
	}

	// Re-Directing to the Administrator Page
	@RequestMapping(value = "/adminPrevious")
	public ModelAndView adminPrevious() {
		LOG.info(LoggingConstants.PREVIOUS);
		ModelAndView model1;
		ArrayList<DataProvider> list = loginBO.listInfo();
		model1 = new ModelAndView("AdminLogin");
		model1.addObject("approval", list);
		model1.addObject("userLogin", "Administrator");
		return model1;
	}

	// Re-Directing from the Administrator Page to DISPLAY Page
	@RequestMapping(value = "/directdisplayPage")
	public ModelAndView directdisplayPage() {
		LOG.info(LoggingConstants.DISPLAY_PAGE);

		ModelAndView model1;
		model1 = listPopulate.getRecordList("DisplayDownload");
		model1.addObject("display", "Directdisplay");
		return model1;
	}

	// Re-Directing to the LOGIN Page after SIGN OUT
	@RequestMapping(value = "intialPage", method = RequestMethod.POST)
	public ModelAndView department(HttpSession session) {
		LOG.info(LoggingConstants.LOGIN_CLOSED);
		session.invalidate();
		return new ModelAndView("CensusLogin", "user", new DataProvider());
	}

}
