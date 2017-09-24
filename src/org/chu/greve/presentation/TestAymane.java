package org.chu.greve.presentation;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.chu.greve.business.GreveBusiness;
import org.chu.greve.business.GreveBusinessImpl;
import org.chu.greve.business.ProfesseurBusiness;
import org.chu.greve.business.ProfesseurBusinessImpl;
import org.chu.greve.dao.CadreDao;
import org.chu.greve.dao.CadreDaoImpl;
import org.chu.greve.dao.GreveDaoImpl;
import org.chu.greve.dao.HopitalDao;
import org.chu.greve.dao.HopitalDaoImpl;
import org.chu.greve.dao.ProfesseurDaoImpl;
import org.chu.greve.dao.ResidentDao;
import org.chu.greve.dao.ResidentDaoImpl;
import org.chu.greve.models.Cadre;
import org.chu.greve.models.Corps;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Greve;
import org.chu.greve.models.Greviste;
import org.chu.greve.models.Hopital;
import org.chu.greve.models.Professeur;
import org.chu.greve.models.Resident;
import org.chu.greve.models.Service;
import org.chu.greve.models.Specialite;
import org.chu.greve.util.DocumentGeneratorGreve;
import org.chu.greve.util.HibernateUtil;
import org.chu.greve.util.PathResolver;

public class TestAymane {

	public TestAymane() {

		test06();
	}

	public void test06() {

		// TestDocument

		GreveBusiness business = new GreveBusinessImpl(new GreveDaoImpl(HibernateUtil.getSessionFactory()));
		Greve greve = new Greve();
		greve.setIdG(2);
		Greviste greviste = business.listGreviste(greve).get(0);
		greviste.ajouterJour("2017-02-02");
		greviste.ajouterJour("2017-02-02");
		Service s = new Service("d", "مصلحة طب القلب و الشرايين");
		s.setHopital(new Hopital("", "مستشفى الإختصاصات"));
		greviste.getGreviste().setService(s);
		DocumentGeneratorGreve doc = new DocumentGeneratorGreve();
		// System.out.println(doc.generateRetenueSalaire(greviste, null));
		System.out.println(doc.generateDemandeExplication(greviste, "", new Date(), "الساعة الخامسة"));
	}

	public void test05() {
		GreveBusiness business = new GreveBusinessImpl(new GreveDaoImpl(HibernateUtil.getSessionFactory()));
		Greve greve = new Greve("2017-02-02", "2017-06-06");
		// int r = business.addGreve(greve);
		Professeur f = new Professeur();
		// f.setCin("4");
		// Greviste greviste = new Greviste();
		greve.setIdG(1);
		// greviste.setGreve(greve);
		// greviste.setGreviste(f);
		// int r = business.addGreviste(greviste);
		// greve.setIdG(1);
		// List<Greviste> list = business.listGreviste(greve);
		// Greviste gr = list.get(0);
		// System.out.println(gr.getGreve().getDateDebut());
		Greviste g = new Greviste(greve, f);
		g.setId(2);
		// g.ajouterJour("1995-05-01");
		// g.ajouterJour("2016-04-21");
		// g.ajouterJour("2017-07-11");
		// business.addGreviste(g);
		// List<Date> list = business.listJour(g);
		// System.out.println(list.get(0));
		// System.out.println(business.updateJour(g, "2017-09-13",
		// "2007-09-13"));
		System.out.println(business.deletJour(g, "2007-09-13"));
	}

	public void test04() {
		// TEST PROFESSEUR
		ProfesseurBusiness business = new ProfesseurBusinessImpl(
				new ProfesseurDaoImpl(HibernateUtil.getSessionFactory()));
		Corps corps = new Corps();
		Grade grade = new Grade();
		Specialite specialite = new Specialite();
		Service service = new Service();
		Cadre cadre = new Cadre();
		corps.setId(1);
		grade.setIdG(1);
		specialite.setIdS(1);
		service.setIdSe(1);
		cadre.setId(1);
		Professeur prof = new Professeur("oui", "1995-05-08", "1995-05-08", 1, "nom", "prenom", "npAr", "CD6",
				"1995-05-08", "Masculin", "Marocain", "1995-05-08", "1995-05-08", "Autonome", "Rien", 1, 1, 1, corps,
				grade, cadre, service, specialite);
		if (business.addProfesseur(prof) == 1) {
			System.out.println("OK");
		} else
			System.out.println("NOK");
	}

	public void test03() {
		ResidentDao dao = new ResidentDaoImpl(HibernateUtil.getSessionFactory());
		Corps corps = new Corps();
		Grade grade = new Grade();
		Specialite specialite = new Specialite();
		Service service = new Service();
		Cadre cadre = new Cadre();
		corps.setId(1);
		grade.setIdG(1);
		specialite.setIdS(1);
		service.setIdSe(1);
		cadre.setId(1);

		Resident resident = new Resident("Test Status", "Test type", 10101010, "Test", "Test", "Test", "Test",
				"01-01-2017", "Test", "Test", "05/01/2017", "05/01/2017", "Test", "Test", 8, 8, 8, corps, grade, cadre,
				service, specialite);
		dao.addResident(resident);
		// dao.updateResident(resident);
		// dao.deleteResident(resident);
	}

	public void test02() {
		// Test hopital and service
		HopitalDao dao = new HopitalDaoImpl(HibernateUtil.getSessionFactory());
		Hopital hopital = new Hopital(2, "Test Hopital", "Test Hopital");
		Service service = new Service("Test Service", "Test Service");
		Hopital h = new Hopital();
		Service s = dao.selectService("1");
		System.out.println(s.getHopital().getIdH());
		// dao.insertHopital(hopital);
		// service.setHopital(hopital);
		// dao.insertService(service);
		// List<Service> ss = dao.listService(hopital);
		//
		// for (int i = 0; i < ss.size(); i++) {
		// System.out.println(ss.get(i).getIdSe());
		// }
		// h.setIdH(29);
		// dao.deletHopital(h);
		// service.setIdSe(4);
		// dao.deleteService(service);
		// dao.updateService(service);

	}

	public void test01() {
		// Test of CadreDao Functions
		CadreDao cadreDao = new CadreDaoImpl(HibernateUtil.getSessionFactory());
		Cadre c = new Cadre(1, "inge", "النالي مريم");
		Cadre cadre = cadreDao.selectCadre("1");
		System.out.println(cadre.getId());
		// cadreDao.insertCadre(c);
		// cadreDao.insertCadre(c);
		// cadreDao.insertCadre(c);
		// cadreDao.deleteCadre(1);
		// cadreDao.deleteCadre(2);
		// cadreDao.deleteCadre(3);
		// cadreDao.deleteCadre(4);
		// cadreDao.updateCadre(c);

		// List<Cadre> list = cadreDao.listCadre();
		// for (Cadre cadre : list) {
		// System.out.println(cadre.getIntituleAr());
		// }

	}

	public static void main(String[] args) {
		new TestAymane();
	}

}
