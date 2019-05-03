package com.app.dao;


import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.entities.User;
import com.app.utilities.HibernateUtil;

public class UserDao {

	public void saveUser(User user){

		Transaction transaction = null;

		try {
System.out.println("hello");
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();

		} catch (Exception e) {

			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		}
	}

	public User getUser(String emailId) {

		User user = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			user = (User)session.get(User.class,emailId);
			if (user == null) {
				throw new Exception("No such user Found !!!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public boolean updateUserPassword(String emailId, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
//		 Query query = session.createNativeQuery("update myuser set password="+password+"where emailid="+emailId);
		String hql = "UPDATE User set password = :pass WHERE emailId = :myemail";
		 Query query = session.createQuery(hql);
		 query.setParameter("pass", password);
		 query.setParameter("myemail", emailId);
		 System.out.println("heleooeoleleol");
		int result = query.executeUpdate();
		tx.commit();
		if(result > 0) {
			return true;
		}
		return false;
	}

}
