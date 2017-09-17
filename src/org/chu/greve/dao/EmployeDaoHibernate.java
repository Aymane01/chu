package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Cadre;
import org.chu.greve.models.Corps;
import org.chu.greve.models.Employe;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Interne;
import org.chu.greve.models.Specialite;
import org.chu.greve.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class EmployeDaoHibernate implements EmployeDao {
	private SessionFactory factory;

	private Session session;
	
	public EmployeDaoHibernate(SessionFactory factory) {
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
	public Employe select(String CIN) {
		openSession();
		Employe emp = (Employe) session.get(Employe.class, CIN);
		closeSession();
		return emp;
	}
	@Override
	public List<Employe> selectbyName(String nomFr) {
			openSession();
		
		try {
			List<Employe> list = new ArrayList<>();
			String query = "select * from Employe where nomFr=" + nomFr;
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Employe.class);
			list = sql.list();
			
			closeSession();
			return list;
		} catch (Exception e) {
			System.out.println("l'exeption est ici 1 ");
			closeSession();
			return null;
		}
	}
	@Override
	public List<Employe> selectAll() {
		openSession();
		
			List<Employe> list = new ArrayList<>();
			String query = "select * from Employe";
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Employe.class);
			list = sql.list();
			
			closeSession();
			return list;
		
	}
	@Override
	public int insert(Employe f) {
		openSession();
		remplirGrade(f);// fait pour la premiere insertion a partir des fichiers excel
		remplirCadre(f);
		remplirSpecialite(f);
		remplirCorps(f);
		try {
			
			session.save(f);
			closeSession();

			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}
	public void remplirGrade(Employe f) {
		List<Grade> list = new ArrayList<>();
		String query = "select * from Grade where intituleFr='" + f.getGrade().getIntituleFr().replace("'", "''") + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Grade.class);
		list = sql.list();
		if(list.isEmpty()) {
			f.setGrade(null);
		}else {
			f.setGrade(list.get(0));
		}
	}
	public void remplirCadre(Employe f) {
		List<Cadre> list = new ArrayList<>();
		String query = "select * from Cadre where intituleFr='" + f.getCadre().getIntituleFr().replace("'", "''") + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Cadre.class);
		list = sql.list();
		if(list.isEmpty()) {
			f.setCadre(null);
		}else {
			f.setCadre(list.get(0));
		}
	}
	public void remplirSpecialite(Employe f) {
		List<Specialite> list = new ArrayList<>();
		String query = "select * from Specialite where intituleFr='" + f.getSpecialite().getIntituleFr().replace("'", "''") + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Specialite.class);
		list = sql.list();
		if(list.isEmpty()) {
			f.setSpecialite(null);
		}else {
			f.setSpecialite(list.get(0));
		}
	}
	public void remplirCorps(Employe f) {
		List<Corps> list = new ArrayList<>();
		String query = "select * from Corps where intituleFr='" + f.getCorps().getIntituleFr().replace("'", "''") + "'";
		SQLQuery sql = session.createSQLQuery(query);
		sql.addEntity(Corps.class);
		list = sql.list();
		if(list.isEmpty()) {
			f.setCorps(null);
		}else {
			f.setCorps(list.get(0));
		}
	}
	@Override
	public int delete(String CIN) {
		openSession();
		System.out.println(CIN);
		try {
			Employe g = (Employe) session.load(Employe.class, CIN);
			session.delete(g);
			
			closeSession();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int modify(Employe f) {
		try {
			openSession();
			
			Employe g = (Employe) session.get(Employe.class, f.getCin());
			
			g.setCin(f.getCin());
			g.setNomFr(f.getNomFr());
			g.setDateN(f.getDateN());
			g.setSexe(f.getSexe());
			g.setService(f.getService());
			g.setPpr(f.getPpr());
			g.setNomCompletAr(f.getNomCompletAr());
			g.setBudget(f.getBudget());
			g.setEchelle(f.getEchelle());
			g.setEchelon(f.getEchelon());
			g.setIndice(f.getIndice());
			
			g.setGrade(f.getGrade());
			g.setCorps(f.getCorps());
			g.setCadre(f.getCadre());
			g.setSpecialite(f.getSpecialite());
			
			session.update(g);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
