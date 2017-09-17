package org.chu.greve.models;

public class Hopital {
	private int idH;
	private String intituleFr;
	private String intituleAr;

	public Hopital() {
	}

	public Hopital(String intituleFr, String intituleAr) {
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public Hopital(int idH, String intituleFr, String intituleAr) {
		this(intituleFr, intituleAr);
		this.idH = idH;

	}

	public int getIdH() {
		return idH;
	}

	public void setIdH(int idH) {
		this.idH = idH;
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
