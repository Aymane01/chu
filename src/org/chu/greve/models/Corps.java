package org.chu.greve.models;

import java.io.Serializable;

public class Corps implements Serializable {
	private int id;
	private String intituleFr;
	private String intituleAr;

	public Corps() {
		super();
	}
	public Corps(String fr) {
		this.intituleFr = fr;
	}
	public Corps(int id, String intituleFr, String intituleAr) {
		this.id = id;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public Corps(String intituleFr, String intituleAr) {
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}
	public Corps(String[] row) {
		setIntituleFr(row[0]);
		setIntituleAr("");
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
