package com.census.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.census.bean.ModifyTO;
import com.census.constants.LoggingConstants;
import com.census.constants.MessageConstants;
import com.census.database.CensusDetails;
import com.census.database.DistrictDetails;
import com.census.database.StateDetails;

@Repository("userModfication")
public class UserModficationDaoImpl implements IUserModificationDao {
	@Autowired
	SessionFactory sessionFactory;
	private final static Logger LOG = Logger
			.getLogger(UserModficationDaoImpl.class);

	// Getting the Entire stateList
	@Override
	public List<StateDetails> getStateList() {

		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(StateDetails.class);
		List<StateDetails> result = cr.list();
		session.close();

		return result;
	}

	// Getting the districtList by state selection
	@Override
	public List<DistrictDetails> getdistrictList(String stateName) {
		String stateId = stateName;

		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(DistrictDetails.class);
		cr.add(Restrictions.eqOrIsNull("stateDetails.stateid", stateId));
		List<DistrictDetails> result = cr.list();
		session.close();
		return result;
	}

	// Getting the entiredistrictList
	@Override
	public List<DistrictDetails> getAlldistrictList() {

		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(DistrictDetails.class);
		List<DistrictDetails> result = cr.list();
		session.close();
		return result;
	}

	// Updating the records with new values
	@Override
	public String updateValue(ModifyTO editTO, String districtValue) {
		LOG.info(LoggingConstants.UPDATE_R);
		String result = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CensusDetails.class);
			criteria.add(Restrictions.eqOrIsNull("districtdetails.districtId",
					districtValue));
			criteria.add(Restrictions.eqOrIsNull("statusRecord", "1"));
			CensusDetails censusObj = (CensusDetails) criteria.uniqueResult();
			censusObj.setEducated(Long.parseLong(editTO.getEducated()));
			censusObj.setNumberofhouseholds(Long.parseLong(editTO
					.getNumberofhouseholds()));
			censusObj.setPersons(Long.parseLong(editTO.getPersons()));
			censusObj.setWorkers(Long.parseLong(editTO.getWorkers()));
			session.save(censusObj);
			result = MessageConstants.MODIFY_PROCESS_U;
			session.getTransaction().commit();

		} catch (Exception e) {
			result = MessageConstants.MODIFY_PROCESS_E;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	// Generating the HQL criteria by given conditions
	@Override
	public ArrayList<CensusDetails> displayByCondition(ModifyTO editTO,
			String districtValue, String order) {

		LOG.info(LoggingConstants.DISPLAY_PROCESS_DAO);
		ArrayList<CensusDetails> result = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CensusDetails.class);
			criteria.add(Restrictions.eq("statusRecord", "1"));
			if (!districtValue.equalsIgnoreCase("select")) {
				criteria.add(Restrictions.eq("districtdetails.districtId",
						districtValue));
			}
			if (!editTO.getPersons().equalsIgnoreCase("")) {
				criteria.add(Restrictions.between("persons",
						Long.parseLong(editTO.getPersons()),
						Long.parseLong(editTO.getRangePersons())));
			}
			if (!editTO.getNumberofhouseholds().equalsIgnoreCase("")) {
				criteria.add(Restrictions.between("numberofhouseholds",
						Long.parseLong(editTO.getNumberofhouseholds()),
						Long.parseLong(editTO.getRangeNumberofhouseholds())));
			}
			if (!editTO.getEducated().equalsIgnoreCase("")) {
				criteria.add(Restrictions.between("educated",
						Long.parseLong(editTO.getEducated()),
						Long.parseLong(editTO.getRangeEducated())));
			}
			if (!editTO.getWorkers().equalsIgnoreCase("")) {
				criteria.add(Restrictions.between("workers",
						Long.parseLong(editTO.getWorkers()),
						Long.parseLong(editTO.getRangeWorkers())));
			}
			if (order.equalsIgnoreCase("districtName")) {
				criteria.createCriteria("districtdetails").addOrder(
						Order.asc("districtName"));
			} else if (order.equalsIgnoreCase("statename")) {
				criteria.createCriteria("districtdetails")
						.createCriteria("stateDetails")
						.addOrder(Order.asc("statename"));
			} else {
				criteria.addOrder(Order.asc(order));
			}
			result = (ArrayList<CensusDetails>) criteria.list();
		} catch (Exception e) {
			LOG.error(LoggingConstants.DISPLAY_E);
			result = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	// Displaying the records with new values
	@Override
	public ArrayList<CensusDetails> displayResult(String hql) {
		Session session = null;
		ArrayList<CensusDetails> list = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();
			if (hql != null) {
				Query query = session.createQuery(hql);
				list = (ArrayList<CensusDetails>) query.list();
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			list = null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	// Getting values by district selection
	@Override
	public List<CensusDetails> getUpdatePojo(String districtName) {

		List<CensusDetails> result = null;
		String districtId = districtName;

		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(CensusDetails.class);
		cr.add(Restrictions
				.eqOrIsNull("districtdetails.districtId", districtId));
		cr.add(Restrictions.eqOrIsNull("statusRecord", "1"));
		result = cr.list();
		session.close();
		return result;
	}

	// Deleting the records
	@Override
	public String deleteValue(ModifyTO editTO, String districtValue) {
		LOG.info(LoggingConstants.DROP_R);
		String result = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(CensusDetails.class);
			cr.add(Restrictions.eqOrIsNull("districtdetails.districtId",
					districtValue));
			cr.add(Restrictions.eq("statusRecord", "1"));
			List<CensusDetails> list = cr.list();
			for (CensusDetails censusObj : list) {
				censusObj.setStatusRecord("0");
				session.save(censusObj);
			}
			session.getTransaction().commit();
			result = MessageConstants.MODIFY_PROCESS_D;
			if (list.isEmpty()) {
				result = MessageConstants.MODIFY_PROCESS_E;
			}
		} catch (Exception e) {
			result = MessageConstants.MODIFY_PROCESS_E;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	// inserting the records with new values
	@Override
	public String insertValue(ModifyTO editTO, String districtValue) {
		LOG.info(LoggingConstants.INSERT_R);
		String result = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(DistrictDetails.class);
			cr.add(Restrictions.eqOrIsNull("districtId", districtValue));
			DistrictDetails districtObj = (DistrictDetails) cr.uniqueResult();
			CensusDetails censusObj = new CensusDetails();
			censusObj.setDistrictdetails(districtObj);
			censusObj.setStatusRecord("1");
			censusObj.setEducated(Long.parseLong(editTO.getEducated()));
			censusObj.setNumberofhouseholds(Long.parseLong(editTO
					.getNumberofhouseholds()));
			censusObj.setPersons(Long.parseLong(editTO.getPersons()));
			censusObj.setWorkers(Long.parseLong(editTO.getWorkers()));
			session.save(censusObj);
			result = MessageConstants.MODIFY_PROCESS_I;
			session.getTransaction().commit();

		} catch (Exception e) {
			result = MessageConstants.MODIFY_PROCESS_E;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

}
