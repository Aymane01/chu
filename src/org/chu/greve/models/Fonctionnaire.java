package org.chu.greve.models;

public class Fonctionnaire {
	private Integer ppr;
	private String nomFr;
	private String prenomFr;
	private String nomCompletAr;
	private String Cin;
	private String dateN;
	private String sexe;
	private String dateArrive;
	private String budget;
	private String observation;

	private String nationalite;
	private Integer echelle;
	private Integer echelon;
	private Integer indice;
	private Service service;
	private Grade grade;
	private Corps corps;
	private Cadre cadre;
	private Specialite specialite;

	public Fonctionnaire() {
		service = new Service();
		grade = new Grade();
		corps = new Corps();
		cadre = new Cadre();
		specialite = new Specialite();
		// TODO Auto-generated constructor stub
	}

	public Fonctionnaire(int ppr2, String nomFr2, String prenomFr2, String nomCompletAr2, String cin2, String dateN2,
			String sexe2, String nationalite, String dateArrive2, String budget2, String observation2, int echelle2,
			int echelon2, int indice2, Corps corps2, Grade grade2, Cadre cadre2, Service service2,
			Specialite specialite2) {
		this(ppr2, nomFr2, prenomFr2, nomCompletAr2, cin2, dateN2, sexe2, dateArrive2);
		setSpecialite(specialite2);
		service = new Service();
		grade = new Grade();
		corps = new Corps();
		cadre = new Cadre();
		specialite = new Specialite();
	}

	public Fonctionnaire(Integer ppr, String nomFr, String prenomFr, String nomCompletAr, String cin, String dateN,
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
		this.echelle = echelle;
		this.echelon = echelon;
		this.indice = indice;

	}

	public Fonctionnaire(String nomFr, String prenomFr, String nomCompletAr, String cin, String dateN, String sexe,
			String dateArrive, Integer echelle, Integer echelon, Integer indice) {
		super();
		this.ppr = ppr;
		this.nomFr = nomFr;
		this.prenomFr = prenomFr;
		this.nomCompletAr = nomCompletAr;
		Cin = cin;
		this.dateN = dateN;
		this.sexe = sexe;
		this.dateArrive = dateArrive;
		this.echelle = echelle;
		this.echelon = echelon;
		this.indice = indice;
	}

	public Integer getPpr() {
		return ppr;
	}

	public void setPpr(Integer ppr) {
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

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
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

	public Integer getEchelle() {
		return echelle;
	}

	public void setEchelle(Integer echelle) {
		this.echelle = echelle;
	}

	public Integer getEchelon() {
		return echelon;
	}

	public void setEchelon(Integer echelon) {
		this.echelon = echelon;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Corps getCorps() {
		return corps;
	}

	public void setCorps(Corps corps) {
		this.corps = corps;
	}

	public Cadre getCadre() {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

}
