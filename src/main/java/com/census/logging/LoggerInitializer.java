package com.census.logging;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;




import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.census.constants.LoggingConstants;

public class LoggerInitializer extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	Logger LOG=Logger.getLogger(LoggerInitializer.class);


	@Override
	public void init(final ServletConfig config) throws ServletException {
		final ServletContext context=config.getServletContext();
		final String delimiter=context.getRealPath("/");
		final String realPath=delimiter;
		final String fileName=realPath+"WEB-INF\\log4j.properties";
		PropertyConfigurator.configure(fileName);
		LOG.info(LoggingConstants.LOGGER_SERVLET);
	}
	
	
}
