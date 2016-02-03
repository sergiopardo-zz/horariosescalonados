package com.gonet.horariosescalonados.util;

import java.io.File;

public class HibernateUtils {
	/*private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory() {
		try {
			File f = new File ("hibernate.cfg.xml");
			
			return new Configuration().configure(f).addAnnotatedClass(com.gonet.horariosescalonados.bean.BeanZeit.class).buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void main(String[] args) {
		SessionFactory factory = getSessionFactory();
		System.out.println("Session factory object created : " + factory);
		Session session = factory.openSession();
		try {
			System.out.println("Session object created : " + session);
			// We can use this session object for doing CRUD (inserting,
			// updating and deleting rows)
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}*/
}
