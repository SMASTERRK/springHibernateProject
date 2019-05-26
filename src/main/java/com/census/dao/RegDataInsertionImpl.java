package com.census.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.census.constants.EncryptionConstants;
import com.census.constants.LoggingConstants;
import com.census.database.DataProvider;
import com.census.encryption.AesCrypting;
import com.census.exceptions.EncryptionException;
import com.census.exceptions.InsertionException;
import com.census.utils.PojoToPojo;

@Repository("regInsert")
public class RegDataInsertionImpl implements IRegDataInsertion {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	PojoToPojo copynewPojo;
	@Autowired
	AesCrypting crypting;

	private final static Logger LOG = Logger
			.getLogger(RegDataInsertionImpl.class);

	// Loading the Registered Data's into Database
	@Override
	public void updation(DataProvider provider) throws InsertionException {
		LOG.info(LoggingConstants.REGISTER);
		// SessionFactory SF = new
		// AnnotationConfiguration().addPackage("com.census.database").addAnnotatedClass(DataProvider.class).configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		final String secretKey = EncryptionConstants.AES_KEY;
		final String pword = provider.getPword();
		String encryptedString;
		try {
			encryptedString = crypting.encrypt(pword, secretKey);
		} catch (EncryptionException encryptexception) {
			provider.setPword("");
			provider.setRpword("");
			throw new InsertionException(encryptexception.getMessage());
		}
		provider.setPword(encryptedString);
		provider.setStatus("0");

		// Adding current date of Registration to table

		java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
		provider.setRegDate(sqlDate);

		Transaction transaction = session.beginTransaction();
		session.save(provider);
		try {
			transaction.commit();
		} catch (Exception exception) {
			provider.setPword("");
			provider.setUname("");
			provider.setRpword("");
			throw new InsertionException(exception.getMessage());
		}
		session.close();
	}
}
