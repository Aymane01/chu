package org.chu.greve.models;

import java.io.Serializable;

public class Corps implements Serializable {
	private int id;
	private String intituleFr;
	private String intituleAr;

	public Corps() {
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
