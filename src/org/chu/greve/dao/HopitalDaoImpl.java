package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Cadre;
import org.chu.greve.models.Hopital;
import org.chu.greve.models.Service;
import org.chu.greve.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HopitalDaoImpl implements HopitalDao, SessionDao {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session;

	public HopitalDaoImpl() {
	}

	public HopitalDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int insertHopital(Hopital hopital) {
		try {
			openSession();
			session.save(hopital);
			closeSession();

			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	public int insertService(Service service) {
		try {
			openSession();
			session.save(service);
			closeSession();
			return 1;

		} catch (Exception e) {
			return 0;
		}
	}

	public void openSession() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	public void closeSession() {
		session.getTransaction().commit();
		session.close();

	}

	public List<Service> listService(Hopital hopital) {
		try {
			List<Service> list = new ArrayList<>();
			String query = "SELECT * FROM service  WHERE fk_idHopital = " + hopital.getIdH();
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Service.class);
			list = sql.list();
			closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Hopital> listHopital() {
		try {
			List<Hopital> list = new ArrayList<>();
			String query = "SELECT * FROM Hopital";
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Hopital.class);
			list = sql.list();
			closeSession();
			return list;
		} catch (Exception e) {
			return null;

		}
	}

	public int deletHopital(Hopital h) {
		try {
			String query = "DELETE FROM SERVICE WHERE fk_idHopital = " + h.getIdH();

			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.executeUpdate();
			session.delete(h);
			closeSession();

			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	public int deleteService(Service service) {
		try {
			openSession();
			session.delete(service);
			closeSession();

			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public int updateHopital(Hopital hopital) {
		try {
			openSession();
			session.update(hopital);
			closeSession();

			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public int updateService(Service service) {
		try {
			openSession();
			session.update(service);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public Service selectService(String id) {
		List<Service> list = new ArrayList<>();
		List<Integer> idH = new ArrayList<>();
		String query = "SELECT * FROM Service WHERE IDSE = " + id;
		String queryH = "SELECT fk_idHopital FROM Service WHERE IDSE = " + id;
		openSession();
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Service.class);
		list = sql.list();
		sql = session.createSQLQuery(queryH);
		idH = sql.list();
		String idHopital = String.valueOf(idH.get(0));
		closeSession();
		Service s = list.get(0);
		s.setHopital(selectHopital(idHopital));
		return s;
	}

	public Hopital selectHopital(String id) {
		List<Hopital> list = new ArrayList<>();
		String query = "SELECT * FROM Hopital WHERE IDH = " + id;
		openSession();
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Hopital.class);
		list = sql.list();
		closeSession();
		return list.get(0);
	}
}