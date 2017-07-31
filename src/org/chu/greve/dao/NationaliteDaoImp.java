package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Cadre;
import org.chu.greve.models.Nationalite;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class NationaliteDaoImp implements NationaliteDao, SessionDao {
	private SessionFactory factory;

	private Session session;

	public NationaliteDaoImp(SessionFactory factory) {
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

	public int insertNationalite(Nationalite nat) {
		try {
			openSession();
			session.save(nat);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public int deleteNationalite(int id) {
		try {
			openSession();
			Nationalite n = new Nationalite();
			n.setId(id);
			session.delete(n);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public int updateNationalite(Nationalite nat) {
		try {
			openSession();
			session.update(nat);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public List<Nationalite> listNationalite() {

		try {
			List<Nationalite> list = new ArrayList<>();
			String query = "select * from corps";
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Nationalite.class);
			list = sql.list();
			closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

}


