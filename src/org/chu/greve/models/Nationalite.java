package org.chu.greve.models;

public class Nationalite {

	private int id;
	private String intitule;

	public Nationalite() {

	}

	public Nationalite(int id, String intitule) {
		this.id = id;
		this.intitule = intitule;
		
	}

	public Nationalite(String intitule) {

		this.intitule = intitule;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}



}

