package com.census.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.census.bean.ModifyTO;
import com.census.constants.ErrorConstants;
import com.census.constants.LoggingConstants;
import com.census.constants.MessageConstants;
import com.census.database.DataProvider;
import com.census.exceptions.CensusApplicationException;
import com.census.exceptions.CensusBusinessException;
import com.census.exceptions.InsertionException;
import com.census.service.AdminBO;
import com.census.service.FileBO;
import com.census.service.LoginBO;
import com.census.service.RegisterBO;
import com.census.utils.RequiredList;

@Controller
@SessionAttributes("userLogin")
public class LoginController {
	@Autowired
	LoginBO loginBO;
	@Autowired
	RequiredList listPopulate;
	
	@Autowired
	RegisterBO registerBO;
	@Autowired
	AdminBO adminBO;
	@Autowired
	FileBO fileBO;
	private final static Logger LOG = Logger.getLogger(LoginController.class);

	// Initial controller for loading the CENSUS_LOGIN PAGE
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView department() {
		LOG.info(LoggingConstants.INITIATED);
		return new ModelAndView("CensusLogin", "user", new DataProvider());
	}

	// Validating the login credentials and based on it redirecting to
	// corresponding JSP pages
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView logincontroller(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		String passWord = request.getParameter("Password");
		String userName = request.getParameter("UserName");
		LOG.info("Login credentials :" + passWord + "," + userName);
		int result;
		try {

			result = loginBO.validateUser(userName, passWord);
			// Directing to the Administrator JSP Page
			if (result == 1) {
				model = adminPageLoader();
				model.addObject("userLogin", "Administrator");
				LOG.info(LoggingConstants.ADMIN_PAGE_R);
			}
			// Directing to the User JSP Page
			if (result == 2) {
				model = listPopulate.getEntireList("UserModification");
				model.addObject("editTO", new ModifyTO());
				model.addObject("userLogin", userName);
				LOG.info(LoggingConstants.USER_LOGIN_R);
			}
			// Re-Directing to the Login JSP Page
		} catch (CensusApplicationException e) {

			model = loginException();
			model.addObject("error", e.getLocalizedMessage());

		} catch (CensusBusinessException e) {
			model = loginException();
			model.addObject("error", e.getLocalizedMessage());
		}
		return model;
	}

	private ModelAndView loginException() {
		ModelAndView model;
		LOG.info(LoggingConstants.LOGIN_SERVLET_E);
		LOG.info(LoggingConstants.CENSUS_LOGIN_R);
		model = new ModelAndView("CensusLogin");
		model.addObject("user", new DataProvider());
		return model;
	}

	// Directing to the Login JSP Page based on the NEW USER Registration
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerFormController(
			@ModelAttribute("regInfo") DataProvider regInfo, ModelMap model) {
		LOG.info(LoggingConstants.REGISTER_SERVLET_I);
		ModelAndView modelview = null;
		modelview = new ModelAndView("CensusLogin");
		try {
			String errorMessage = registerBO.validateRegistered(regInfo);
			if (errorMessage == null) {
				modelview.addObject("regInfo", new DataProvider());
				modelview.addObject("error", MessageConstants.REGISTRATION_S);
				modelview.addObject("user", new DataProvider());
			} else {
				modelview.addObject("error", errorMessage);
				modelview.addObject("regInfo", regInfo);
				modelview.addObject("user", new DataProvider());
			}
		} catch (InsertionException e) {
			modelview.addObject("regInfo", regInfo);
			modelview.addObject("user", new DataProvider());
			modelview.addObject("error", ErrorConstants.USERNAME_ALREADY);
		}

		LOG.info(LoggingConstants.CENSUS_LOGIN_R);

		return modelview;

	}

	// Directing to the Administrator JSP Page once Loading the entire FILE
	@RequestMapping(value = "/filepath")
	public ModelAndView filepath(@RequestParam("file") String fileName) {
		LOG.info(LoggingConstants.FILE_PATH + fileName);
		String res = fileBO.fileProcessing(fileName);
		ModelAndView model1;
		model1 = adminPageLoader();
		model1.addObject("error", res);
		
		return model1;
	}

	// Directing to the Administrator JSP Page after the new user approval
	@RequestMapping(value = "/approveUsers")
	public ModelAndView approveUsers(
			@RequestParam("listapprove") String[] listapprove, ModelMap model) {
		String result = adminBO.approveSelected(listapprove);
		ModelAndView model1;
		model1 = adminPageLoader();
		model1.addObject("error", result);

		return model1;
	}

	private ModelAndView adminPageLoader() {
		ModelAndView model1;
		ArrayList<DataProvider> list = loginBO.listInfo();
		model1 = new ModelAndView("AdminLogin");
		model1.addObject("approval", list);
		return model1;
	}
}
