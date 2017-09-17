package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Cadre;
import org.chu.greve.models.Hopital;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CadreDaoImpl implements CadreDao, SessionDao {
	private SessionFactory factory;

	private Session session;

	public CadreDaoImpl(SessionFactory factory) {
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

	public int insertCadre(Cadre cadre) {
		try {
			openSession();
			session.save(cadre);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public int deleteCadre(int id) {
		try {
			openSession();
			Cadre c = new Cadre();
			c.setId(id);
			session.delete(c);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public int updateCadre(Cadre cadre) {
		try {
			openSession();
			session.update(cadre);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public List<Cadre> listCadre() {

		try {
			List<Cadre> list = new ArrayList<>();
			String query = "select * from cadre";
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Cadre.class);
			list = sql.list();
			closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public Cadre selectCadre(String id) {
		try {
			List<Cadre> list = new ArrayList<>();
			String query = "select * from cadre where idCa = " + id;
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Cadre.class);
			list = sql.list();
			closeSession();
			return list.get(0);
		} catch (Exception e) {
			return null;
		}
	}

}
