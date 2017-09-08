package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.util.HibernateUtil;
import org.chu.greve.models.Corps;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Specialite;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class SpecialiteDaoHibernate implements SpecialiteDao{
	private SessionFactory factory;

	private Session session;
	public SpecialiteDaoHibernate(SessionFactory factory) {
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
	public Specialite select(int id) {
		openSession();
		
		Specialite spec = (Specialite) session.get(Specialite.class, id);
		
		closeSession();
		return spec;
	}

	@Override
	public List<Specialite> selectAll() {
		openSession();
		try {
			List<Specialite> list = new ArrayList<>();
			String query = "select * from specialite";
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Specialite.class);
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
	public int insert(Specialite spec) {
		openSession();
		try {
			session.beginTransaction();
			
			Specialite s = new Specialite();
			s.setIntituleAr(spec.getIntituleAr());
			s.setIntituleFr(spec.getIntituleFr());
			
			session.save(s);
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
		
		Specialite g = (Specialite) session.load(Specialite.class, id);
		
		session.delete(g);
		
		closeSession();
	}

	@Override
	public void modify(Specialite spec) {
		openSession();
		
		Specialite g = (Specialite) session.get(Specialite.class, spec.getIdS());
		g.setIntituleAr(spec.getIntituleAr());
		g.setIntituleFr(spec.getIntituleFr());
		
		session.update(g);
		closeSession();
		
	}

	@Override
	public Specialite select(String intituleFr) {
		openSession();
		try {
			List<Specialite> list = new ArrayList<>();
			String query = "select * from specialite where intituleFr='" + intituleFr + "'" ;
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Specialite.class);
			list = sql.list();
			if(list.isEmpty()) {
				insert(new Specialite(intituleFr, ""));
				list = sql.list();
				System.out.println("xxxxx");
				return list.get(0);
			}else {
				return list.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
