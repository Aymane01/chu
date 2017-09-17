package org.chu.greve.dao;

import java.util.ArrayList;
import java.util.List;

import org.chu.greve.models.Cadre;
import org.chu.greve.models.Employe;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Resident;
import org.chu.greve.models.Service;
import org.chu.greve.models.Specialite;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ResidentDaoImpl implements ResidentDao, SessionDao {
	private SessionFactory factory;
	private Session session;

	public ResidentDaoImpl() {

	}

	public ResidentDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public int addResident(Resident resident) {
		remplirSpecialite(resident);
		try {
			resident.setCorps(null);
			openSession();
			session.save(resident);
			closeSession();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int updateResident(Resident resident) {
		try {
			openSession();
			session.update(resident);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public int deleteResident(Resident resident) {
		try {
			openSession();
			Resident r = (Resident) session.load(Resident.class, resident.getCin());
			session.delete(r);
			closeSession();
			return 1;
		} catch (Exception e) {
			return 0;
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

	@Override
	public List<Resident> listResident() {
		try {
			List<Resident> list = new ArrayList<>();
			String query = "SELECT * FROM Resident";
			openSession();
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Resident.class);
			list = sql.list();
			// closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void remplirSpecialite(Resident f) {
		List<Specialite> list = new ArrayList<>();
		String query = "select * from Specialite where intituleFr='" + f.getSpecialite().getIntituleFr().replace("'", "''") + "'";
		try {
			SQLQuery sql = session.createSQLQuery(query);
			sql.addEntity(Specialite.class);
			list = sql.list();
			if(list.isEmpty()) {
				f.setSpecialite(null);
			}else {
				f.setSpecialite(list.get(0));
			}
		} catch (Exception e) {
			System.out.println("Erreur ici");
		}
		
	}
}
