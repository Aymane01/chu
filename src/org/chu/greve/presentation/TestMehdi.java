package org.chu.greve.presentation;



import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import org.chu.greve.util.ArabicNameImporter;
import org.chu.greve.util.DataImporter;
import org.chu.greve.util.DocumentCreator;
import org.chu.greve.util.HibernateUtil;
import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.business.SpecialiteBusinessImpl;
import org.chu.greve.dao.EmployeDao;
import org.chu.greve.dao.EmployeDaoHibernate;
import org.chu.greve.dao.GradeDao;
import org.chu.greve.dao.GradeDaoHibernate;
import org.chu.greve.dao.InterneDao;
import org.chu.greve.dao.InterneDaoHibernate;
import org.chu.greve.dao.ResidentDao;
import org.chu.greve.dao.ResidentDaoImpl;
import org.chu.greve.dao.SpecialiteDao;
import org.chu.greve.dao.SpecialiteDaoHibernate;
import org.chu.greve.models.Cadre;
import org.chu.greve.models.Corps;
import org.chu.greve.models.Employe;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Hopital;
import org.chu.greve.models.Interne;
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
		Vector<Specialite> specs = importer.importSpecialite();
		for (Specialite spec  : specs) {
			System.out.println(spec.getIdS()+"\t"+spec.getIntituleFr());
		}
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
		Vector<Specialite> specs = importer.importSpecialite();
		for (Specialite spec : specs) {
			business.addSpecialite(new Specialite(spec.getIntituleFr(), spec.getIntituleAr()));
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
		for (int i = 0; i < data.length; i++) {
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
	public TestMehdi() {
		exp11();
	}
	public static String quote(String data) {
		if(data == null) return "";
		return data.replace("\"", "\\\"");
	}
	public static void main(String[] args) {
		new TestMehdi();
	}
}
