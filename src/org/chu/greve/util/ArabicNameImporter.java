package org.chu.greve.util;

import java.util.HashMap;

import org.mql.jee.doa.jdbc.importer.excelDataImporter;

public class ArabicNameImporter {
	private HashMap<Long, String> row;
	public ArabicNameImporter() {
		row = new HashMap<>();
		
		}
	public HashMap<Long, String> importArabic(){
		excelDataImporter importer1 = new excelDataImporter("resources/Recap1.xls");
		String[][] rows1 = importer1.importContent();
		for (int i = 1; i < rows1.length; i++) {
			if(!rows1[i][7].equals("")) {
				row.put(Long.parseLong(rows1[i][3]), rows1[i][7]);
			}
		}
		
		excelDataImporter importer2 = new excelDataImporter("resources/Recap2.xls");
		String[][] rows2 = importer2.importContent();
		for (int i = 1; i < rows2.length; i++) {
			if(!rows2[i][7].equals("")) {
				row.put(Long.parseLong(rows2[i][3]), rows2[i][7]);
			}
		}
		
		excelDataImporter importer3 = new excelDataImporter("resources/Recap3.xls");
		String[][] rows3 = importer3.importContent();
		for (int i = 1; i < rows3.length; i++) {
			if(!rows3[i][7].equals("")) {
				row.put(Long.parseLong(rows3[i][3]), rows3[i][7]);
			}
		}
		
		return row;
	}
	
}
