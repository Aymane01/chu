package org.chu.greve.models;



public class Fonctionnaire {
	private int ppr;
	private String nomFr;
	private String prenomFr;
	private String nomCompletAr;
	private String Cin;
	private String dateN;
	private String sexe;
	private String dateArrive;
	private String budget;
	private String observation;
	
	
	public Fonctionnaire() {
		
	}
	public Fonctionnaire(int ppr, String nomFr, String prenomFr, String nomCompletAr, String cin, String dateN,
			String sexe, String dateArrive) {
		super();
		this.ppr = ppr;
		this.nomFr = nomFr;
		this.prenomFr = prenomFr;
		this.nomCompletAr = nomCompletAr;
		Cin = cin;
		this.dateN = dateN;
		this.sexe = sexe;
		this.dateArrive = dateArrive;
	}
	public Fonctionnaire(String nomFr, String prenomFr, String nomCompletAr, String cin, String dateN,
			String sexe, String dateArrive) {
		super();
		this.ppr = ppr;
		this.nomFr = nomFr;
		this.prenomFr = prenomFr;
		this.nomCompletAr = nomCompletAr;
		Cin = cin;
		this.dateN = dateN;
		this.sexe = sexe;
		this.dateArrive = dateArrive;
	}
	public int getPpr() {
		return ppr;
	}
	public void setPpr(int ppr) {
		this.ppr = ppr;
	}
	public String getNomFr() {
		return nomFr;
	}
	public void setNomFr(String nomFr) {
		this.nomFr = nomFr;
	}
	public String getPrenomFr() {
		return prenomFr;
	}
	public void setPrenomFr(String prenomFr) {
		this.prenomFr = prenomFr;
	}
	public String getNomCompletAr() {
		return nomCompletAr;
	}
	public void setNomCompletAr(String nomCompletAr) {
		this.nomCompletAr = nomCompletAr;
	}
	public String getCin() {
		return Cin;
	}
	public void setCin(String cin) {
		Cin = cin;
	}
	public String getDateN() {
		return dateN;
	}
	public void setDateN(String dateN) {
		this.dateN = dateN;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getDateArrive() {
		return dateArrive;
	}
	public void setDateArrive(String dateArrive) {
		this.dateArrive = dateArrive;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	
}
