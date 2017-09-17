package org.chu.greve.presentation;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.chu.greve.util.ArabicNameImporter;
import org.chu.greve.util.DataImporter;
import org.chu.greve.util.DocumentCreator;
import org.chu.greve.util.HibernateUtil;
import org.chu.greve.business.CadreBusinessImpl;
import org.chu.greve.business.Cadrebusiness;
import org.chu.greve.business.CorpsBusinessImpl;
import org.chu.greve.business.Corpsbusiness;
import org.chu.greve.business.EmployeBusiness;
import org.chu.greve.business.EmployeBusinessImpl;
import org.chu.greve.business.GradeBusiness;
import org.chu.greve.business.GradeBusinessImpl;
import org.chu.greve.business.HopitalService;
import org.chu.greve.business.HopitalServiceImpl;
import org.chu.greve.business.InterneBusiness;
import org.chu.greve.business.InterneBusinessImp;
import org.chu.greve.business.ProfesseurBusiness;
import org.chu.greve.business.ProfesseurBusinessImpl;
import org.chu.greve.business.ResidentBusiness;
import org.chu.greve.business.ResidentBusinessImpl;
import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.business.SpecialiteBusinessImpl;
import org.chu.greve.dao.CadreDao;
import org.chu.greve.dao.CadreDaoImpl;
import org.chu.greve.dao.CompteDao;
import org.chu.greve.dao.CompteDaoImpl;
import org.chu.greve.dao.CorpsDao;
import org.chu.greve.dao.CorpsDaoImpl;
import org.chu.greve.dao.EmployeDao;
import org.chu.greve.dao.EmployeDaoHibernate;
import org.chu.greve.dao.GradeDao;
import org.chu.greve.dao.GradeDaoHibernate;
import org.chu.greve.dao.HopitalDao;
import org.chu.greve.dao.HopitalDaoImpl;
import org.chu.greve.dao.InterneDao;
import org.chu.greve.dao.InterneDaoHibernate;
import org.chu.greve.dao.ProfesseurDao;
import org.chu.greve.dao.ProfesseurDaoImpl;
import org.chu.greve.dao.ResidentDao;
import org.chu.greve.dao.ResidentDaoImpl;
import org.chu.greve.dao.SpecialiteDao;
import org.chu.greve.dao.SpecialiteDaoHibernate;
import org.chu.greve.models.Cadre;
import org.chu.greve.models.Compte;
import org.chu.greve.models.Corps;
import org.chu.greve.models.Employe;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Hopital;
import org.chu.greve.models.Interne;
import org.chu.greve.models.Professeur;
import org.chu.greve.models.Resident;
import org.chu.greve.models.Service;
import org.chu.greve.models.Specialite;
import org.hibernate.classic.Session;
import org.mql.jee.doa.jdbc.exporter.ExcelDataExporter;
import org.mql.jee.doa.jdbc.importer.excelDataImporter;
import org.mql.jee.doa.jdbc.mapper.RowMapper;

public class TestMehdi {
	private String[][] donnee ;
	void exp01() {
		DataImporter importer = new DataImporter();
		Vector<Resident> residents = (Vector<Resident>) importer.importResidents();
		for (Resident resident : residents) {
			System.out.println(resident.getCin());
		}
	}
	void exp02() {
		DataImporter importer = new DataImporter();
		//Vector<Specialite> specs = importer.importSpecialite();
//		for (Specialite spec  : specs) {
//			System.out.println(spec.getIdS()+"\t"+spec.getIntituleFr());
//		}
	}
	void exp03() {
//		DataImporter importer = new DataImporter();
//		Vector<Fonctionnaire> fcts = importer.importFonctionnaire();
//		for (Fonctionnaire fct  : fcts) {
//			System.out.println(fct.getCIN()+"\t"+fct.getNomFr()+"\t"+fct.getPPR());
//		}
	}
	void exp04() {
//		PersonneManager pm = new PersonneManager();
//		
//		DataImporter importer = new DataImporter();
//		Vector<Hopital> hops = importer.importHopitaux();
//		for (Hopital hop  : hops) {
//			System.out.println(hop.getId()+"\t"+hop.getNomFr());
//			pm.addHopital(hop.getId(),hop.getNomFr(),hop.getNomAR());
//		}
//		//pm.addHopital(11, "CHU", "");
//		
//	
//		HibernateUtil.sessionF.close();
	}
	
	void exp06() {
		InterneDao dao = new InterneDaoHibernate(HibernateUtil.getSessionFactory());
		DataImporter importer = new DataImporter();
		List<Interne> internes =  importer.importFonctionnaire();
		for (Interne interne  : internes) {
			dao.insert(interne);
			System.out.println(interne.getCin());
		}
		System.out.println(dao.select("CD228227").getDateN());
		HibernateUtil.sessionF.close();
	}
	void exp07() {
		GradeDao dao = new GradeDaoHibernate();
		dao.insert(new Grade(6,"","النالي مريم"));
		System.out.println(dao.select(203).getIntituleAr());;
		//System.out.println(dao.select(3).getIntituleAr());
		HibernateUtil.sessionF.close();
	}
	void exp08() {
		SpecialiteDao dao = new SpecialiteDaoHibernate(HibernateUtil.getSessionFactory());
		SpecialiteBusiness business = new SpecialiteBusinessImpl(dao);
		DataImporter importer = new DataImporter();
		HashMap<String,Specialite> specs = importer.importSpecialite();
		Set s = specs.keySet();
		Iterator it = s.iterator();
		while (it.hasNext()) {
			Object cle = it.next();
			business.addSpecialite(new Specialite(specs.get(cle).getIntituleFr(), specs.get(cle).getIntituleAr()));

			
		}
		
//		CadreDao dao = new CadreDaoImpl(HibernateUtil.getSessionFactory());
//		Cadrebusiness business = new CadreBusinessImpl(dao);
//		DataImporter importer = new DataImporter();
//		Vector<Cadre> cadres = importer.importCadre();
//		for (Cadre cadre : cadres) {
//			business.createCadre(cadre);
//		}
		
	}
	void exp081() {
		GradeDao dao = new GradeDaoHibernate(HibernateUtil.getSessionFactory());
		GradeBusiness business = new GradeBusinessImpl(dao);
		DataImporter importer = new DataImporter();
		HashMap<String,Grade> grades = importer.importGrades();
		Set s = grades.keySet();
		Iterator it = s.iterator();
		while (it.hasNext()) {
			Object cle = it.next();
			business.addGrade(new Grade(grades.get(cle).getIntituleFr(), grades.get(cle).getIntituleAr()));

			
		}
	}
	void exp082() {
		HopitalDao dao = new HopitalDaoImpl(HibernateUtil.getSessionFactory());
		HopitalServiceImpl business = new HopitalServiceImpl(dao);
		DataImporter importer = new DataImporter();
		Vector<Service> services =  importer.importService();
		for (Service service : services) {
			business.addService(service);
		}
	}
	void exp087() {
		HopitalDao dao = new HopitalDaoImpl(HibernateUtil.getSessionFactory());
		HopitalService business = new HopitalServiceImpl(dao);
		DataImporter importer = new DataImporter();
		Vector<Hopital> hopitals =  importer.importHopitaux();
		for (Hopital hopital : hopitals) {
			business.addHopital(new Hopital(hopital.getIntituleFr(), hopital.getIntituleAr()));
		}
	}
	void exp083() {
		InterneDao dao = new InterneDaoHibernate(HibernateUtil.getSessionFactory());
		InterneBusiness business = new InterneBusinessImp(dao);
		DataImporter importer = new DataImporter();
		List<Interne> internes =  importer.importInterne();
		for (Interne interne : internes) {
			business.addInterne(interne);
		}
		
	}
	
	void exp084() {
		ResidentDao dao = new ResidentDaoImpl(HibernateUtil.getSessionFactory());
		ResidentBusiness business = new ResidentBusinessImpl(dao);
		DataImporter importer = new DataImporter();
		List<Resident> residents =  importer.importResidents();
		for (Resident resident : residents) {
			business.addResident(resident);
		}
		
	}
	void exp085() {
		EmployeDaoHibernate dao = new EmployeDaoHibernate(HibernateUtil.getSessionFactory());
		EmployeBusiness business = new EmployeBusinessImpl(dao);
		DataImporter importer = new DataImporter();
		List<Employe> employes =  importer.importEmploye();
		for (Employe employe : employes) {
			business.addEmploye(employe);
		}
		business.addEmploye(new Employe());
	}
	void exp086() {
		ProfesseurDao dao = new ProfesseurDaoImpl(HibernateUtil.getSessionFactory());
		ProfesseurBusiness business = new ProfesseurBusinessImpl(dao);
		DataImporter importer = new DataImporter();
		List<Professeur> profs =  importer.importProf();
		for (Professeur professeur : profs) {
			business.addProfesseur(professeur);
		}
//		Grade g = new Grade();
//		g.setIdG(1);
//		Professeur p = new Professeur("oui", "", "", 1548, "", "", "", "", "", "", "", "", "", "", "", 458, 55, 5, new Corps(),g, new Cadre(), new Service(), new Specialite());
//		business.addProfesseur(p);
//		
	}
	void exp088() {
		CorpsDao dao = new CorpsDaoImpl(HibernateUtil.getSessionFactory());
		Corpsbusiness business = new CorpsBusinessImpl(dao);
		DataImporter importer = new DataImporter();
		Vector<Corps> corps =  importer.importCorps();
		for (Corps corp : corps) {
			business.createCorps(corp);
		}
	}
	void exp09() {
		
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		String[][] data = importer.importContent();
		
		ArabicNameImporter arabic = new ArabicNameImporter();
		HashMap<Long, String> rows = arabic.importArabic();
		
		System.out.println("Nombre d'enregistrement en arabe : " + rows.size());
		for (Entry<Long, String> row : rows.entrySet()) {
			for (int i = 1; i < data.length ; i++) {
				if(!data[i][6].equals("")) {
					if(Long.parseLong(data[i][6]) == row.getKey() ) {
						data[i][4] = row.getValue();
					}
				}
			}
		}
		donnee = new String[data.length][250];
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < 250; j++) {
				donnee[i][j] = data[i][j];
			}
		}
		
		ExcelDataExporter exporter = new ExcelDataExporter("resources/KACUPEE2.xls");
		exporter.export(donnee);
	
	}
	void exp10() {
		SpecialiteDao dao = new SpecialiteDaoHibernate(HibernateUtil.getSessionFactory());
		SpecialiteBusiness business = new SpecialiteBusinessImpl(dao);
		//List<Specialite> specs= business.selectAllSpecilite();
//		business.selectSpecialite(id)
//		System.out.println(specs.get(24).getIntituleAr());
//		System.out.println(specs.size());
		
	}
	void exp11() {
		DocumentCreator creator = new DocumentCreator("resources/test.docx");
		//Interne interne = new Interne("Mehdi ZKaghat", "", "", "CD597779", "", "", "20/06/2013",5,4,254);
		Resident resident = new Resident("benevole", "", 15487, "Kaghat", "Mehdi", "", "CD597779", "1995/07/23", "Homme", "MAROCAINE", "2013/06/20", "INTERNE", "", 1, 5, 88, new Corps(), new Grade(), new Cadre(), new Service(), new Specialite(1, "XX", ""));
		
		
		creator.createAttestationSalaireResident(resident, false);
		System.out.println("fin ");
	}
	void exp12() {
		InterneDao dao = new InterneDaoHibernate(HibernateUtil.getSessionFactory());
		System.out.println(dao.selectAll().size());
	}
	void exp13() {
		ResidentDao dao = new ResidentDaoImpl(HibernateUtil.getSessionFactory());
		
		Resident resident = new Resident();
		resident.setCin("XXX1548");
		int r = dao.addResident(resident);
		
		System.out.println(r);
	}
	void exp14() {
		EmployeDao dao = new EmployeDaoHibernate(HibernateUtil.getSessionFactory());
		List<Employe> foncts = dao.selectAll();
		for (Employe emp : foncts) {
			System.out.println(emp.getCin());
		}
	}
	void exp15() {
		DocumentCreator creator = new DocumentCreator("resources/test.docx");
		Interne interne = new Interne("Mehdi ZKaghat", "", "", "CD597779", "", "", "20/06/2013",5,4,254);
		interne.setStage2("Psychiatrie");
		interne.setStage4("Pediatrie");
		Resident resident = new Resident("benevole", "", 15487, "Kaghat", "Mehdi", "", "CD597779", "1995/07/23", "Homme", "MAROCAINE", "2013/06/20", "INTERNE", "", 1, 5, 88, new Corps(), new Grade(), new Cadre(), new Service(), new Specialite(1, "XX", ""));
		
		
		creator.CreateAttestationTravailInterne(interne);
		System.out.println("fin ");
	}
	void exp16() {
		DocumentCreator creator = new DocumentCreator("resources/test.docx");
		Professeur p = new Professeur("Chef de service ", "", "", 26598, "Mehdi Kaghat", "", "", "CD597779", "", "MASCULIN", "MAROCAINE", "", "", "INTERNE", "", 2, 3, 123, new Corps(1, "Medical", ""), new Grade(1, "INSPECTEUR", ""), new Cadre(), new Service(1, "Radiologie", "", new Hopital(1, "hopital CHU", "")), new Specialite(1, "Pediatre", ""));
		creator.createAttestationTravail(p);
	}
	void exp17() {
		InterneDao dao = new InterneDaoHibernate(HibernateUtil.getSessionFactory());
		List<Interne> e = dao.selectAll();
		System.out.println(e.get(0).getStage1());
	}
	void exp18() {
		System.out.println("%03d");
	}
	public TestMehdi() {
		exp18();
	}
	public static String quote(String data) {
		if(data == null) return "";
		return data.replace("\"", "\\\"");
	}
	public static void main(String[] args) {
		new TestMehdi();
	}
}
