package com.app.utilities;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.app.entities.User;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		System.out.println("hellolll");
		
		if(sessionFactory == null) {
			Configuration configuration = new Configuration();
			
			Properties hibernateSettings = new Properties();
//			hibernateSettings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
//			hibernateSettings.put(Environment.URL, "jdbc:mysql://localhost:3306/machine_learning");
//			hibernateSettings.put(Environment.USER, "root");
//			hibernateSettings.put(Environment.PASS, "");
//			hibernateSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL57Dialect");
			
			hibernateSettings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
			hibernateSettings.put(Environment.URL, "jdbc:oracle:thin:@localhost:1521:XE");
			hibernateSettings.put(Environment.USER, "system");
			hibernateSettings.put(Environment.PASS, "system");
			hibernateSettings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle10gDialect");
			
			hibernateSettings.put(Environment.SHOW_SQL, true);
			hibernateSettings.put(Environment.HBM2DDL_AUTO, "update");
			
			configuration.setProperties(hibernateSettings);
			configuration.addAnnotatedClass(User.class);
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("sess");
		}
		return sessionFactory;
	}
}
