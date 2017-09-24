package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Professeur;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProfesseurDaoImpl implements ProfesseurDao, SessionDao {
	private SessionFactory factory;
	private Session session;

	public ProfesseurDaoImpl(SessionFactory factory) {
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

	public int addProfesseur(Professeur professeur) {
		try {
			openSession();
			session.save(professeur);
			closeSession();
			return 1;

		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int updateProfesseur(Professeur professeur) {
		try {
			openSession();
			session.update(professeur);
			closeSession();
			return 1;

		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int deleteProfesseur(Professeur professeur) {
		try {
			openSession();
			session.delete(professeur);
			closeSession();
			return 1;

		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public List<Professeur> listProfesseur() {
		try {
			List<Professeur> list = new ArrayList<>();
			openSession();
			String query = "SELECT * FROM PROFESSEUR";
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Professeur.class);
			list = sql.list();
			return list;

		} catch (Exception e) {
			closeSession();
			return null;
		}

	}

}
