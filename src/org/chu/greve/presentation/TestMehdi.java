package org.chu.greve.presentation;



import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import org.chu.greve.util.ArabicNameImporter;
import org.chu.greve.util.DataImporter;
import org.chu.greve.util.HibernateUtil;
import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.business.SpecialiteBusinessImpl;
import org.chu.greve.dao.GradeDao;
import org.chu.greve.dao.GradeDaoHibernate;
import org.chu.greve.dao.SpecialiteDao;
import org.chu.greve.dao.SpecialiteDaoHibernate;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Specialite;
import org.hibernate.classic.Session;
import org.mql.jee.doa.jdbc.exporter.ExcelDataExporter;
import org.mql.jee.doa.jdbc.importer.excelDataImporter;
import org.mql.jee.doa.jdbc.mapper.RowMapper;

public class TestMehdi {
	private String[][] donnee ;
	void exp01() {
		DataImporter importer = new DataImporter();
		Vector<Grade> grades = importer.importGrades();
		for (Grade grade  : grades) {
			System.out.println(grade.getIntituleAr());
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
		GradeDao dao = new GradeDaoHibernate();
		DataImporter importer = new DataImporter();
		Vector<Grade> grades = importer.importGrades();
		for (Grade grade  : grades) {
			dao.insert(new Grade(grade.getIdG(),grade.getIntituleFr(),grade.getIntituleAr()));
			System.out.println(grade.getIntituleFr());
		}
		
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
	public TestMehdi() {
		exp07();
	}
	public static String quote(String data) {
		if(data == null) return "";
		return data.replace("\"", "\\\"");
	}
	public static void main(String[] args) {
		new TestMehdi();
	}
}
