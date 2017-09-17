package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Cadre;
import org.chu.greve.models.Compte;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CompteDaoImpl implements CompteDao {
	private SessionFactory factory;

	private Session session;

	public CompteDaoImpl(SessionFactory factory) {
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
	@Override
	public int insertCompte(Compte compte) {
		try {
			openSession();
			session.save(compte);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int deleteCompte(int id) {
		try {
			openSession();
			Compte c = new Compte();
			c.setId(id);
			session.delete(c);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int updateCompte(Compte compte) {
		try {
			openSession();
			session.update(compte);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Compte> listCompte() {
		try {
			List<Compte> list = new ArrayList<>();
			String query = "select * from Compte";
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Compte.class);
			list = sql.list();
			closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

}
