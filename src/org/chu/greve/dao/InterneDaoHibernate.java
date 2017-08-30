package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Interne;
import org.chu.greve.models.Specialite;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class InterneDaoHibernate implements InterneDao{
	private SessionFactory factory;

	private Session session;
	
	public InterneDaoHibernate(SessionFactory factory) {
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
	public Interne select(String CIN) {
		openSession();
		Interne interne = (Interne) session.get(Interne.class, CIN);
		closeSession();
		return interne;
	}

	@Override
	public List<Interne> selectbyName(String nomFr) {
		openSession();
		
		try {
			List<Interne> list = new ArrayList<>();
			String query = "select * from interne where nomCompletFr=" + nomFr;
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Interne.class);
			list = sql.list();
			
			closeSession();
			return list;
		} catch (Exception e) {
			System.out.println("l'exeption est ici");
			closeSession();
			return null;
		}
	}

	@Override
	public List<Interne> selectAll() {
		openSession();
		
		try {
			List<Interne> list = new ArrayList<>();
			String query = "select * from interne";
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Interne.class);
			list = sql.list();
			
			closeSession();
			return list;
		} catch (Exception e) {
			System.out.println("l'exeption est ici selectAll dao");
			closeSession();
			return null;
		}
	}

	@Override
	public int insert(Interne interne) {
		openSession();
		try {
			session.beginTransaction();
			Interne g = new Interne();
			g.setCin(interne.getCin());
			g.setNomFr(interne.getNomFr());
			g.setPrenomFr(interne.getPrenomFr());
			g.setNomCompletAr(interne.getNomCompletAr());
			g.setDateN(interne.getDateN());
			g.setDateRecru(interne.getDateRecru());
			g.setSexe(interne.getSexe());
			g.setStage1(interne.getStage1());
			g.setStage2(interne.getStage2());
			g.setStage3(interne.getStage3());
			g.setStage4(interne.getStage4());
			g.setService(interne.getService());
			
			session.save(g);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	@Override
	public void delete(String CIN) {
		openSession();
		
		Interne g = (Interne) session.load(Interne.class, CIN);
		
		session.delete(g);
		
		closeSession();
		
	}

	@Override
	public void modify(Interne interne) {
		openSession();
		
		Interne g = (Interne) session.get(Interne.class, interne.getCin());
		g.setCin(interne.getCin());
		g.setNomFr(interne.getNomFr());
		g.setDateN(interne.getDateN());
		g.setDateRecru(interne.getDateRecru());
		g.setSexe(interne.getSexe());
		g.setStage1(interne.getStage1());
		g.setStage2(interne.getStage2());
		g.setStage3(interne.getStage3());
		g.setStage4(interne.getStage4());
		
		session.update(g);
		closeSession();
		
	}

}
