package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Cadre;
import org.chu.greve.models.Corps;
import org.chu.greve.models.Employe;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Professeur;
import org.chu.greve.models.Specialite;
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
		openSession();
		//professeur.setCorps(null);
		//professeur.setSpecialite(null);
//		professeur.setCadre(null);
		professeur.setService(null);
		professeur.setCadre(null);
		remplirGrade(professeur);
		remplirCorps(professeur);
		remplirSpecialite(professeur);
//		remplirCadre(professeur);
		try {
			session.save(professeur);
			closeSession();
			return 1;

		} catch (Exception e) {
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
			for (Professeur professeur : list) {
				insertSpecialite(professeur);
				insertGrade(professeur);
				//insertCorps(professeur);
			}
			return list;

		} catch (Exception e) {
			return null;
		}
	}
	public void insertSpecialite(Professeur p) {
		List<Specialite> list = new ArrayList<>();
		String query = "select * from Specialite where idS='" + p.getSpecialite().getIdS() + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Specialite.class);
		list = sql.list();
		if(list.isEmpty()) {
			p.setSpecialite(null);
		}else {
			p.setSpecialite(list.get(0));
		}
	}
	public void insertGrade(Professeur p) {
		List<Grade> list = new ArrayList<>();
		String query = "select * from Grade where idG='" + p.getGrade().getIdG() + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Grade.class);
		list = sql.list();
		if(list.isEmpty()) {
			p.setGrade(null);
		}else {
			p.setGrade(list.get(0));
		}
	}
	public void insertCorps(Professeur p) {
		List<Corps> list = new ArrayList<>();
		String query = "select * from Corps where idCr=" + p.getCorps().getId() ;
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Corps.class);
		list = sql.list();
		if(list.isEmpty()) {
			p.setCorps(null);
		}else {
			p.setCorps(list.get(0));
		}
	}
	public void remplirGrade(Professeur p) {
		List<Grade> list = new ArrayList<>();
		String query = "select * from Grade where intituleFr='" + p.getGrade().getIntituleFr().replace("'", "''") + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Grade.class);
		list = sql.list();
		if(list.isEmpty()) {
			p.setGrade(null);
		}else {
			p.setGrade(list.get(0));
		}
	}
	public void remplirCadre(Professeur p) {
		List<Cadre> list = new ArrayList<>();
		String query = "select * from Cadre where intituleFr='" + p.getCadre().getIntituleFr().replace("'", "''") + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Cadre.class);
		list = sql.list();
		if(list.isEmpty()) {
			p.setCadre(null);
		}else {
			p.setCadre(list.get(0));
		}
	}
	public void remplirSpecialite(Professeur p) {
		List<Specialite> list = new ArrayList<>();
		String query = "select * from Specialite where intituleFr='" + p.getSpecialite().getIntituleFr().replace("'", "''") + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Specialite.class);
		list = sql.list();
		if(list.isEmpty()) {
			p.setSpecialite(null);
		}else {
			p.setSpecialite(list.get(0));
		}
	}
	public void remplirCorps(Professeur p) {
		List<Corps> list = new ArrayList<>();
		String query = "select * from Corps where intituleFr='" + p.getCorps().getIntituleFr().replace("'", "''") + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Corps.class);
		list = sql.list();
		if(list.isEmpty()) {
			p.setCorps(null);
		}else {
			p.setCorps(list.get(0));
		}
	}
	
}
