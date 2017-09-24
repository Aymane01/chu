package org.chu.greve.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	public static SessionFactory sessionF = new Configuration().configure().buildSessionFactory();
	public static Session session = getSessionFactory().openSession();

	public static SessionFactory getSessionFactory() {
		return sessionF;
	}

	public static Session getSession() {
		return session;
	}
}
