package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Employe;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Interne;
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
			String query = "select * from Employe where nomCompletFr=" + nomFr;
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
		try {
			session.beginTransaction();
			Employe g = new Employe();
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
			session.save(g);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}
	@Override
	public int delete(String CIN) {
		openSession();
		
		try {
			Employe g = (Employe) session.load(Employe.class, CIN);
			
			session.delete(g);
			
			closeSession();
			return 1;
		} catch (Exception e) {
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
			
			session.update(g);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
