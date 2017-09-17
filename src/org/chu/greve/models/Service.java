package org.chu.greve.models;

public class Service {
	private int idSe;
	private String intituleFr;
	private String intituleAr;
	private Hopital hopital;

	public Service() {
		hopital = new Hopital();
	}

	public Service(String intituleFr, String intituleAr, Hopital hopital) {

		this(intituleFr, intituleAr);
		this.hopital = hopital;
	}

	public Service(String intituleFr, String intituleAr) {
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public Service(int idSe, String intituleFr, String intituleAr, Hopital hopital) {
		this(intituleFr, intituleAr, hopital);
		this.idSe = idSe;
	}

	public int getIdSe() {
		return idSe;
	}

	public void setIdSe(int idSe) {
		this.idSe = idSe;
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

	public Hopital getHopital() {
		return hopital;
	}

	public void setHopital(Hopital hopital) {
		this.hopital = hopital;
	}
}
