package org.chu.greve.models;

public class Cadre {

	private int id;
	private String intituleFr;
	private String intituleAr;

	public Cadre(int id, String intituleFr, String intituleAr) {
		this.id = id;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
