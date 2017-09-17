package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
		if(service.getHopital().getIntituleFr() != null) {

			Hopital h = selectHopital(service.getHopital().getIntituleFr());
			service.setHopital(h);
		}
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
			String query = "SELECT idSe, intituleAr, intituleFr, fk_idHopital FROM service  WHERE fk_idHopital = "
					+ hopital.getIdH();
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
	public Hopital selectHopital(String intituleFr) {
		List<Hopital> hops = listHopital();
		for (Hopital hopital : hops) {
			if(hopital.getIntituleFr().equals(intituleFr)) {
				System.out.println(1);
				return hopital;
			}
		}
		return null;
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
}