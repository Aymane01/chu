package org.chu.greve.models;

import java.util.StringTokenizer;

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
	public Service(String[] row) {
		String francais = row[0];
		String arabe = row[1];
		setIntituleAr(arabe);
		if(francais.contains("-")) {
			StringTokenizer token = new StringTokenizer(francais, "-");
			String hopital = token.nextToken();
			String Service = token.nextToken();
			
			setIntituleFr(Service);
			Hopital h = new Hopital();
			h.setIntituleFr(hopital);
			setHopital(h);
		}else {
			setIntituleFr(francais);
		}
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
