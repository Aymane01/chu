package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.util.HibernateUtil;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Specialite;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class GradeDaoHibernate implements GradeDao{
	private SessionFactory factory;

	private Session session;
	public GradeDaoHibernate() {
	}
	
	public void openSession() {
		session = factory.openSession();
		session.beginTransaction();
	}
	public void closeSession() {
		session.getTransaction().commit();
		session.close();
	}
	public GradeDaoHibernate(SessionFactory factory) {
		this.factory = factory;
	}
	@Override
	public int insert(Grade g) {
		openSession();
		try {
			Grade grd = new Grade();
			grd.setIntituleAr(g.getIntituleAr());
			grd.setIntituleFr(g.getIntituleFr());
			
			session.save(grd);
			closeSession();
			return 1;
		} catch (Exception e) {
			closeSession();
			return 0;
		}
	}

	@Override
	public void delete(int id) {
			openSession();
			
			Grade g = (Grade) session.load(Grade.class, id);
			
			session.delete(g);
			closeSession();
		
	}

	@Override
	public Grade select(int id) {
		openSession();
		
		Grade g = (Grade) session.get(Grade.class, id);
		closeSession();
		return g;
	}


	@Override
	public List<Grade> selectAll() {
		openSession();
		try {
			List<Grade> list = new ArrayList<>();
			String query = "select * from grade";
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Grade.class);
			list = sql.list();
			
			closeSession();
			return list;
		} catch (Exception e) {
			closeSession();
			return null;
		}
	}


	@Override
	public void modify(Grade gr) {
		openSession();
		
		Grade g = (Grade) session.get(Grade.class, gr.getIdG());
		g.setIntituleAr(gr.getIntituleAr());
		g.setIntituleFr(gr.getIntituleFr());
		
		session.update(g);
		closeSession();
		
	}
	
	
	
}
