package org.chu.greve.models;

import java.util.Date;


public class Grade {
	
	
	int idG;
	String intituleFr;
	String intituleAr;
	
	public Grade() {
		// TODO Auto-generated constructor stub
	}
	public Grade(int id, String intituleFr, String intituleAr) {
		super();
		this.idG = id;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}
	public Grade(int id,String[] row) {
		this.idG = id;
		this.intituleFr = row[0];
		this.intituleAr = "";
	}
	
	public int getIdG() {
		return idG;
	}
	public void setIdG(int idG) {
		this.idG = idG;
	}
	public String getIntituleFr() {
		return intituleFr;
	}
	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}
	public String getIntituleAr() {
		return intituleAr;
	}
	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}
	
	
	
	

}
