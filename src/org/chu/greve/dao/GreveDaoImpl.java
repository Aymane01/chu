package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.chu.greve.models.Greve;
import org.chu.greve.models.Greviste;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GreveDaoImpl implements SessionDao, GreveDao {
	private SessionFactory factory;
	private Session session;

	public GreveDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public int addGreve(Greve greve) {

		try {
			openSession();
			session.save(greve);
			closeSession();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			closeSession();
			return 0;
		}
	}

	public int updateGreve(Greve greve) {
		try {
			openSession();
			session.update(greve);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int deleteGreve(Greve greve) {
		try {
			openSession();
			session.delete(greve);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public List<Greve> listGreve() {
		try {
			List<Greve> list = new ArrayList<>();
			String query = "SELECT * FROM Greve";
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Greve.class);
			list = sql.list();
			closeSession();
			return list;
		} catch (Exception e) {
			closeSession();
			return null;
		}
	}

	public int addGreviste(Greviste greviste) {
		try {
			openSession();
			session.save(greviste);
			int id = greviste.getId();
			closeSession();
			openSession();
			String query = "INSERT INTO jourGreve VALUES";
			for (String j : greviste.getJours()) {
				query += "('" + j + "','" + id + "'),";
			}
			query = query.substring(0, query.length() - 1);
			SQLQuery sql = session.createSQLQuery(query);
			sql.executeUpdate();
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int deleteGreviste(Greviste greviste) {
		try {
			openSession();
			session.delete(greviste);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public List<Greviste> listGreviste(Greve greve) {
		try {
			List<Greviste> list = new ArrayList<>();
			String query = "SELECT * FROM GREVISTE WHERE IDGREVE = " + greve.getIdG();
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Greviste.class);
			list = sql.list();
			return list;
		} catch (Exception e) {
			closeSession();
			return null;
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

	public int deleteJours(Greviste greviste) {
		try {
			String query = "DELETE FROM jourGreve WHERE IDGREVISTE = " + greviste.getId();
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.executeUpdate();
			closeSession();
			return 1;

		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public List<Date> listJour(Greviste greviste) {
		try {
			List<Date> list = new ArrayList<>();
			String query = "SELECT jour FROM jourGreve WHERE idGreviste = " + greviste.getId();
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			list = sql.list();
			closeSession();
			return list;
		} catch (Exception e) {
			closeSession();
			return null;
		}
	}

	public int deletJour(Greviste greviste, String jour) {

		try {
			String query = "DELETE FROM jourGreve WHERE jour= '" + jour + "' AND idGreviste= " + greviste.getId();
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.executeUpdate();
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int updateJour(Greviste greviste, String ancienJour, String nouveauJour) {
		try {
			String query = "UPDATE  jourGreve SET jour= '" + nouveauJour + "' WHERE jour= '" + ancienJour
					+ "' AND idGreviste= " + greviste.getId();
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.executeUpdate();
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	public int retenuSalaire(Greviste g) {
		String query = "UPDATE Greviste SET retenu_salaire='Oui' WHERE id= " + g.getId();
		try {
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.executeUpdate();
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

}
