package org.chu.greve.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.chu.greve.dao.EmployeDao;
import org.chu.greve.dao.EmployeDaoHibernate;
import org.chu.greve.dao.GreveDao;
import org.chu.greve.dao.InterneDao;
import org.chu.greve.dao.InterneDaoHibernate;
import org.chu.greve.dao.ProfesseurDao;
import org.chu.greve.dao.ProfesseurDaoImpl;
import org.chu.greve.dao.ResidentDao;
import org.chu.greve.dao.ResidentDaoImpl;
import org.chu.greve.models.Employe;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Greve;
import org.chu.greve.models.Greviste;
import org.chu.greve.models.Interne;
import org.chu.greve.models.Professeur;
import org.chu.greve.models.Resident;
import org.chu.greve.util.HibernateUtil;

public class GreveBusinessImpl implements GreveBusiness {
	private GreveDao greveDao;
	private ResidentDao residentDao;
	private ProfesseurDao professeurDao;
	private EmployeDao employeDao;
	private InterneDao interneDao;
	private Map<String, Fonctionnaire> listFontionnaire;

	public GreveBusinessImpl(GreveDao greveDao) {
		this.greveDao = greveDao;

		residentDao = new ResidentDaoImpl(HibernateUtil.getSessionFactory());
		professeurDao = new ProfesseurDaoImpl(HibernateUtil.getSessionFactory());
		employeDao = new EmployeDaoHibernate(HibernateUtil.getSessionFactory());
		interneDao = new InterneDaoHibernate(HibernateUtil.getSessionFactory());
	}

	public GreveDao getGreveDao() {
		return greveDao;
	}

	public void setGreveDao(GreveDao greveDao) {
		this.greveDao = greveDao;
	}

	public int addGreve(Greve greve) {

		return greveDao.addGreve(greve);
	}

	public int updateGreve(Greve greve) {

		return greveDao.updateGreve(greve);
	}

	public int deleteGreve(Greve greve) {

		return greveDao.deleteGreve(greve);
	}

	public List<Greve> listGreve() {

		return greveDao.listGreve();
	}

	public int addGreviste(Greviste greviste) {
		Fonctionnaire f = listFontionnaire.get(greviste.getGreviste().getCin());
		greviste.setGreviste(f);
		greviste.setService(greviste.getGreviste().getService());
		greviste.setRetenuSalaire("Non");
		return greveDao.addGreviste(greviste);
	}

	public int deleteGreviste(Greviste greviste) {

		return greveDao.deleteGreviste(greviste);
	}

	public List<Greviste> listGreviste(Greve greve) {
		listFontionnaire = list();
		List<Greviste> listGreviste = greveDao.listGreviste(greve);
		for (int i = 0; i < listGreviste.size(); i++) {
			Greviste greviste = listGreviste.get(i);
			greviste.setGreviste(listFontionnaire.get(greviste.getGreviste().getCin()));

		}
		return listGreviste;
	}

	private Map<String, Fonctionnaire> list() {
		Map<String, Fonctionnaire> fonctionnaires = new HashMap<>();
		List<Professeur> prof = professeurDao.listProfesseur();
		List<Resident> resident = residentDao.listResident();
		List<Employe> empl = employeDao.selectAll();
		List<Interne> interne = interneDao.selectAll();
		for (Professeur p : prof) {
			fonctionnaires.put(p.getCin(), p);
		}
		for (Resident r : resident) {
			fonctionnaires.put(r.getCin(), r);
		}
		for (Employe e : empl) {
			fonctionnaires.put(e.getCin(), e);
		}
		for (Interne i : interne) {
			fonctionnaires.put(i.getCin(), i);
		}
		return fonctionnaires;
	}

	public List<Fonctionnaire> listFonctionnaire() {
		listFontionnaire = list();
		return new ArrayList<Fonctionnaire>(listFontionnaire.values());
	}

	public int deleteJours(Greviste greviste) {
		return greveDao.deleteJours(greviste);
	}

	public List<String> listJour(Greviste greviste) {
		List<Date> days = greveDao.listJour(greviste);
		List<String> list = new ArrayList<>();
		for (Date date : days) {
			list.add(date.toString());
		}

		return list;
	}

	public int deletJour(Greviste greviste, String jour) {
		return greveDao.deletJour(greviste, jour);
	}

	public int updateJour(Greviste greviste, String jour, String nouveauJour) {
		return greveDao.updateJour(greviste, jour, nouveauJour);
	}

	public int retenuSalaire(Greviste g) {
		return greveDao.retenuSalaire(g);
	}
}
