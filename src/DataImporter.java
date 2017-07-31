

import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import org.chu.greve.models.Grade;
import org.chu.greve.models.Specialite;
import org.mql.jee.doa.jdbc.importer.excelDataImporter;

public class DataImporter {
	
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
	public Vector<Grade> importGrades(){
		Vector<Grade> grades = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		importer.setSid(14);
		String[][] rows = importer.importContent();
		for (int i = 0; i < rows.length; i++) {
			Grade grade = new Grade(i,rows[i]);
			grades.add(grade);
		}
		return grades;
	}
	
	public Vector<Specialite> importSpecialite(){
		
		Vector<Specialite> specs = new Vector<>();
		
		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
		importer.setSid(16);
		String[][] rows = importer.importContent();
		int j =0;
		for (int i = 0; i < rows.length; i++) {
			Specialite spec = new Specialite(j, rows[i]);
			specs.add(spec);
			j++;
		}
		
		return specs;
	}
	
//	public Vector<Fonctionnaire> importFonctionnaire(){
//		Vector<Fonctionnaire> fcts = new Vector<>();
//		
//		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
//		importer.setSid(0);
//		String[][] rows = importer.importContent();
//		
//		for (int i = 0; i < rows.length; i++) {
//			Fonctionnaire fct = new Fonctionnaire(rows[i]);
//			fcts.add(fct);
//		}
//		
//		return fcts;
//	}
//	
//	public Vector<Hopital> importHopitaux(){
//		Vector<Hopital> hops = new Vector<>();
//		
//		excelDataImporter importer = new excelDataImporter("resources/KACUPEE.xls");
//		importer.setSid(13);
//		String[][] rows = importer.importContent();
//		
//		for (int i = 0; i < rows.length; i++) {
//			Hopital hop = new Hopital(i,rows[i]);
//			hops.add(hop);
//		}
//		
//		return hops;
//		
//	}
	
	
	
}
