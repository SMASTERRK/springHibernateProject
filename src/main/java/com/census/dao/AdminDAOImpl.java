package com.census.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.census.constants.LoggingConstants;
import com.census.constants.MessageConstants;
import com.census.database.DataProvider;

@Repository("adminDaoImpl")
public class AdminDAOImpl implements IAdminDao {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	DataProvider regDetails;
	private final static Logger LOG = Logger.getLogger(AdminDAOImpl.class);

	@Override
	// Approving the new user
	public String approveRegistered(String[] list) {
		String result = null;
		LOG.info(LoggingConstants.ADMINDAO);
		try {
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			for (int i = 0; i < list.length; i++) {
				String listname = list[i];
				String username = listname.replace("/", "");
				Criteria cr = session.createCriteria(regDetails.getClass());
				cr.add(Restrictions.like("uname", username));
				List<DataProvider> userList = cr.list();
				DataProvider regObj = userList.get(0);
				regObj.setStatus("1");
				session.save(regObj);
			}
			result = MessageConstants.ADMIN_DAO_R;
			session.getTransaction().commit();
		} catch (Exception e) {
			LOG.error(LoggingConstants.ADMINDAO_E);
			result = MessageConstants.ADMIN_DAO_E;
		}
		LOG.info(LoggingConstants.ADMINDAO_R + result);
		return result;
	}
}
