package org.chu.greve.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	public static SessionFactory sessionF= new Configuration().configure().buildSessionFactory();
	
	public static SessionFactory getSessionFactory() {
		return sessionF;
	}
}

