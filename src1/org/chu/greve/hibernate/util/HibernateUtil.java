package org.chu.greve.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	public static SessionFactory sessionF= new Configuration().configure().buildSessionFactory();
	
	public static SessionFactory getSessionFactory() {
		return sessionF;
	}
}

