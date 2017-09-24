package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Corps;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CorpsDaoImpl implements CorpsDao, SessionDao {
	private SessionFactory factory;

	private Session session;

	public CorpsDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public void openSession() {
		session = factory.openSession();
		session.beginTransaction();
	}

	public void closeSession() {
		session.getTransaction().commit();
		session.close();
	}

	public int insertCorps(Corps corps) {
		try {
			openSession();
			session.save(corps);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int deleteCorps(int id) {
		try {
			openSession();
			Corps c = new Corps();
			c.setId(id);
			session.delete(c);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int updateCorps(Corps corps) {
		try {
			openSession();
			session.update(corps);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public List<Corps> listCorps() {

		try {
			List<Corps> list = new ArrayList<>();
			String query = "select * from corps";
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Corps.class);
			list = sql.list();
			closeSession();
			return list;
		} catch (Exception e) {
			closeSession();
			return null;
		}
	}

}
