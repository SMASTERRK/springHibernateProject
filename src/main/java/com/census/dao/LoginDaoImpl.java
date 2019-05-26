package com.census.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.census.constants.LoggingConstants;
import com.census.database.DataProvider;

@Repository("loginImpl")
public class LoginDaoImpl implements ILogin {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	DataProvider regDetails;
	private final static Logger LOG = Logger.getLogger(LoginDaoImpl.class);

	// Validating the credentials Data's from Database
	@Override
	public Object validation(String userName, String passWord) {
		LOG.info(LoggingConstants.LOGIN_VALIDATION);
		Object res = null;
		// SessionFactory SF=new
		// AnnotationConfiguration().addPackage("com.census.database").addAnnotatedClass(DataProvider.class).configure().buildSessionFactory();
	
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(regDetails.getClass());
		cr.add(Restrictions.like("uname", userName));
		cr.add(Restrictions.like("status", "1"));

		List<DataProvider> userList = cr.list();
		session.getTransaction().commit();
		if (!userList.isEmpty()) {
			res = userList.get(0);
		}
		session.close();
		return res;
	}

	// Gathering the Registered Data's from Database
	@Override
	public ArrayList<DataProvider> registeredList() {
		LOG.info(LoggingConstants.REGISTERED_LIST);
		ArrayList<DataProvider> regList = null;
		// SessionFactory SF=new
		// AnnotationConfiguration().addPackage("com.census.database").addAnnotatedClass(DataProvider.class).configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(regDetails.getClass());
		cr.add(Restrictions.like("status", "0"));
		// Adding Restrictions for Register approval list based on the
		// currentDate

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);
		Date dateBefore2Days = calendar.getTime();
		java.sql.Date sqlDate = new java.sql.Date(dateBefore2Days.getTime());
		cr.add(Restrictions.gt("regDate", sqlDate));

		regList = (ArrayList<DataProvider>) cr.list();
		session.getTransaction().commit();
		session.close();
		return regList;

	}

}
