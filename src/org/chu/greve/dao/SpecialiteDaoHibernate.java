package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.util.HibernateUtil;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Specialite;
import org.hibernate.classic.Session;

public class SpecialiteDaoHibernate implements SpecialiteDao{

	public SpecialiteDaoHibernate() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Specialite select(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();			
		session.beginTransaction();
		
		Specialite spec = (Specialite) session.get(Specialite.class, id);
		
		session.getTransaction().commit();
		return spec;
	}

	@Override
	public List<Specialite> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Specialite spec) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			Specialite s = new Specialite();
			s.setIntituleAr(spec.getIntituleAr());
			s.setIntituleFr(spec.getIntituleFr());
			
			session.save(s);
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
		
		Specialite g = (Specialite) session.load(Specialite.class, id);
		
		session.delete(g);
		session.getTransaction().commit();
			
	}

	@Override
	public void modify(Specialite spec) {
		Session session = HibernateUtil.getSessionFactory().openSession();			
		session.beginTransaction();
		
		Specialite g = (Specialite) session.get(Specialite.class, spec.getIdS());
		g.setIntituleAr(spec.getIntituleAr());
		g.setIntituleFr(spec.getIntituleFr());
		
		session.update(g);
		session.getTransaction().commit();
		
	}

}
