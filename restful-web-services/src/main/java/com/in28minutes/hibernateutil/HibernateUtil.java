package com.in28minutes.hibernateutil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFacoty();

	private static SessionFactory buildSessionFacoty() {
		try {
			if(sessionFactory==null) {
				
				Configuration configuration=new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cgf.xml"));
				
//				StandardServiceRegistryBuilder serviceRegistryBuilder=new StandardServiceRegistryBuilder();
//				serviceRegistryBuilder.applySettings(configuration.getProperties());

				ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				
				sessionFactory=configuration.buildSessionFactory(serviceRegistry);
								
			}
			return sessionFactory;
		}catch (Throwable e) {
			System.out.println("Initail Session Factory creation failed"+e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}

}
