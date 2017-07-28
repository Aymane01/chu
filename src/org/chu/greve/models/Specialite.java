package org.chu.greve.models;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Entity;

@Entity
public class Specialite {
	int idS;
	String intituleFr;
	String intituleAr;
	public Specialite() {
		// TODO Auto-generated constructor stub
	}
public Specialite(int id, String intituleFr, String intituleAr) {
		super();
		this.idS = id;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}
public Specialite(int id, String[] row) {
	super();
	this.idS = id;
	this.intituleFr = row[0];
	this.intituleAr = row[1];
}

public int getIdS() {
	return idS;
}
public void setIdS(int idS) {
	this.idS = idS;
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
