package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Resident;
import org.chu.greve.models.Service;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ResidentDaoImpl implements ResidentDao, SessionDao {
	private SessionFactory factory;
	private Session session;

	public ResidentDaoImpl() {

	}

	public ResidentDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public int addResident(Resident resident) {
		try {
			openSession();
			session.save(resident);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int updateResident(Resident resident) {
		try {
			openSession();
			session.update(resident);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int deleteResident(Resident resident) {
		try {
			openSession();
			session.delete(resident);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public void openSession() {
		session = factory.openSession();
		session.beginTransaction();

	}

	public void closeSession() {
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Resident> listResident() {
		try {
			List<Resident> list = new ArrayList<>();
			String query = "SELECT * FROM Resident";
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Resident.class);
			list = sql.list();
			// closeSession();
			return list;
		} catch (Exception e) {
			closeSession();
			return null;
		}
	}

}
