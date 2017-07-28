package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.util.HibernateUtil;
import org.chu.greve.models.Grade;
import org.hibernate.classic.Session;

public class GradeDaoHibernate implements GradeDao{

	public GradeDaoHibernate() {
	}


	@Override
	public int insert(Grade g) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			Grade grd = new Grade();
			grd.setIntituleAr(g.getIntituleAr());
			grd.setIntituleFr(g.getIntituleFr());
			
			session.save(grd);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void delete(int id) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
			session.beginTransaction();
			
			Grade g = (Grade) session.load(Grade.class, id);
			
			session.delete(g);
			session.getTransaction().commit();
		
	}

	@Override
	public Grade select(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();			
		session.beginTransaction();
		
		Grade g = (Grade) session.get(Grade.class, id);
		
		session.getTransaction().commit();
		return g;
	}


	@Override
	public List<Grade> selectAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();			
		session.beginTransaction();
		
		
		
		session.getTransaction().commit();
		return null;
	}


	@Override
	public void modify(Grade gr) {
		Session session = HibernateUtil.getSessionFactory().openSession();			
		session.beginTransaction();
		
		Grade g = (Grade) session.get(Grade.class, gr.getIdG());
		g.setIntituleAr(gr.getIntituleAr());
		g.setIntituleFr(gr.getIntituleFr());
		
		session.update(g);
		session.getTransaction().commit();
		
	}
	
	
	
}
