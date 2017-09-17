package org.chu.greve.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.business.SpecialiteBusinessImpl;
import org.chu.greve.dao.SpecialiteDaoHibernate;
import org.chu.greve.models.Cadre;
import org.chu.greve.models.Corps;
import org.chu.greve.models.Employe;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Hopital;
import org.chu.greve.models.Interne;
import org.chu.greve.models.Professeur;
import org.chu.greve.models.Resident;
import org.chu.greve.models.Service;
import org.chu.greve.models.Specialite;
import org.mql.jee.doa.jdbc.importer.excelDataImporter;

public class DataImporter {
	
	private SpecialiteBusiness specB;
	
//	public Vector<Interne> importInternes(){
//		Vector<Interne> internes = new Vector<>();
//		excelDataImporter importer = new excelDataImporter("resources/internes.xls");
//		importer.setSid(0);
//		String[][] rows = importer.importContent();
//		for (int i = 1; i < rows.length; i++) {
//			Interne interne = new Interne(rows[i]);
//			internes.add(interne);
//		}
//		return internes;
//	}
	public DataImporter() {
		specB = new SpecialiteBusinessImpl(new SpecialiteDaoHibernate(HibernateUtil.getSessionFactory()));
	}
	
	
	public List<Resident> importResidents(){
		List<Resident> residents = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/residents.xls");
		importer.setSid(0);
		String[][] rows = importer.importContent();
		
		for (int i = 1; i < rows.length; i++) {
			Resident res = new Resident(rows[i]);
			//res.setSpecialite(specB.selectSpecialite(rows[i][7]));
			residents.add(res);
		}
		
		return residents;
	}
	public List<Professeur> importProf(){
		List<Professeur> profs = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/professeurs.xls");
		importer.setSid(0);
		String[][] rows = importer.importContent();
		
		for (int i = 1; i < rows.length; i++) {
			Professeur prof = new Professeur(rows[i]);
			//res.setSpecialite(specB.selectSpecialite(rows[i][7]));
			profs.add(prof);
		}
		
		return profs;
	}
	public List<Interne> importInterne(){
		List<Interne> internes = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/internes.xls");
		importer.setSid(0);
		String[][] rows = importer.importContent();
		for (int i = 1; i < rows.length; i++) {
			Interne interne = new Interne(rows[i]);
			internes.add(interne);
		}
		return internes;
	}
	
	
	public HashMap<String,Specialite> importSpecialite(){
		HashMap<String, Specialite> specs = new HashMap<>();
		//Vector<Specialite> specs = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		importer.setSid(16);
		String[][] rows = importer.importContent();
		int j =0;
		for (int i = 0; i < rows.length; i++) {
			Specialite spec = new Specialite(j, rows[i]);
			spec.setIntituleAr("");
			specs.put(spec.getIntituleFr(), spec);
			j++;
		}
		excelDataImporter importer1 = new excelDataImporter("resources/professeurs.xls");
		String[][] rows1 = importer1.importContent();
		for (int i = 0; i < rows1.length; i++) {
			Specialite s = new Specialite(rows1[i][8],"");
			specs.put(s.getIntituleFr(), s);
		}
		
		excelDataImporter importer2 = new excelDataImporter("resources/residents.xls");
		String[][] rows2 = importer2.importContent();
		for (int i = 0; i < rows2.length; i++) {
			Specialite s = new Specialite(rows2[i][7],"");
			specs.put(s.getIntituleFr(), s);
		}
		
		
		return specs;
	}
	public HashMap<String,Grade> importGrades(){
		HashMap<String,Grade> grades = new HashMap<>();
		
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		importer.setSid(14);
		String[][] rows = importer.importContent();
		int j =0;
		for (int i = 1; i < rows.length; i++) {
			Grade grade = new Grade(j,rows[i]);
			grades.put(grade.getIntituleFr(), grade);
			j++;
		}
		excelDataImporter importer1 = new excelDataImporter("resources/professeurs.xls");
		String[][] rows1 = importer1.importContent();
		for (int i = 1; i < rows1.length; i++) {
			Grade g = new Grade(rows1[i][2],"");
			grades.put(g.getIntituleFr(), g);
		}
		
		return grades;
	}
	public Vector<Cadre> importCadre(){
		Vector<Cadre> cadres = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		importer.setSid(3);
		String[][] rows = importer.importContent();
		for (int i = 0; i < rows.length; i++) {
			Cadre cadre = new Cadre(rows[i]);
			cadres.add(cadre);
		}
		return cadres;
	}
	public Vector<Corps> importCorps(){
		Vector<Corps> corps = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		importer.setSid(2);
		String[][] rows = importer.importContent();
		for (int i = 0; i < rows.length; i++) {
			Corps corp = new Corps(rows[i]);
			corps.add(corp);
		}
		return corps;
	}
	public Vector<Interne> importFonctionnaire(){
		Vector<Interne> internes = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/internes.xls");
		importer.setSid(0);
		String[][] rows = importer.importContent();
		
		for (int i = 0; i < rows.length; i++) {
			Interne interne = new Interne(rows[i]);
			internes.add(interne);
		}
		
		return internes;
	}
	
	public Vector<Hopital> importHopitaux(){
		Vector<Hopital> hops = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		importer.setSid(13);
		String[][] rows = importer.importContent();
		
		for (int i = 0; i < rows.length; i++) {
			Hopital hop = new Hopital(rows[i]);
			System.out.println(hop.getIntituleFr());
			hops.add(hop);
		}
		
		return hops;
		
	}
	public Vector<Employe> importEmploye(){
		Vector<Employe> employes = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		importer.setSid(0);
		
		String[][] rows = importer.importContent();
		
		for (int i = 1; i < rows.length; i++) {
			Employe emp = new Employe(rows[i]);
			employes.add(emp);
		}
		
		return employes;
	}
	
	public Vector<Service> importService(){
		Vector<Service> services = new Vector<>();
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		importer.setSid(8);
		
		String[][] row = importer.importContent();
		for (int i = 0; i < row.length; i++) {
			Service s = new Service(row[i]);
			services.add(s);
		}
		return services;
	}
	
	
}
