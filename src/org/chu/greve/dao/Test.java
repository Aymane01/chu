package org.chu.greve.dao;

import org.chu.greve.models.Cadre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

	public Test() {

		SessionFactory factory;
		Session session;
		try {
			factory = new Configuration().configure().buildSessionFactory();

			session = factory.openSession();
			session.beginTransaction();
			Cadre c01 = new Cadre(12, "teest", "test");
			Cadre c02 = new Cadre(11, "teest", "test");
			session.save(c01);
			session.save(c02);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Test();
	}

}
