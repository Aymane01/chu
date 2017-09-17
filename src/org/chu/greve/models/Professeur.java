package org.chu.greve.models;

public class Professeur extends Fonctionnaire {
	private String isChef;
	private String dateNomination;
	private String dateIntegration;

	public Professeur() {
		setCorps(new Corps());
	}

	public Professeur(String isChef, String dateNomination, String dateIntegration, int ppr, String nomFr,
			String prenomFr, String nomCompletAr, String cin, String dateN, String sexe, String nationalite,
			String dateArrive, String dateMutation, String budget, String observation, int echelle, int echelon,
			int indice, Corps corps, Grade grade, Cadre cadre, Service service, Specialite specialite) {
		super(ppr, nomFr, prenomFr, nomCompletAr, cin, dateN, sexe, nationalite, dateArrive, budget, observation,
				echelle, echelon, indice, corps, grade, cadre, service, specialite);
		this.isChef = isChef;
		this.dateNomination = dateNomination;
		this.dateIntegration = dateIntegration;
		setService(service);
		setSpecialite(specialite);
		setGrade(grade);
		setCorps(corps);
		setCadre(cadre);
	}
	public Professeur(String[] row) {
		if(row[0] != "") {
			setIndice(Integer.parseInt(row[0]));
		}
		setCin(row[6]);
		setNomFr(row[3]);
		setNomCompletAr(row[5]);
		if(row[4] != "") {
			setPpr(Integer.parseInt(row[4]));
		}
		setGrade(new Grade(row[2]));
		setSexe(row[1]);
		setDateN(row[7]);
		setSpecialite(new Specialite(row[8]));
		setDateIntegration(row[10]);
		setCorps(new Corps(row[11]));
		setGrade(new Grade(row[2]));
		System.out.println(row[2]);
		setIsChef(row[13]);
		setDateNomination(row[14]);
		setObservation(row[15]);
		
		
		
//		Service s = new Service(1,"","",new Hopital(1, "", ""));
//		setService(s);
	}

	public String getIsChef() {
		return isChef;
	}

	public void setIsChef(String isChef) {
		this.isChef = isChef;
	}

	public String getDateNomination() {
		return dateNomination;
	}

	public void setDateNomination(String dateNomination) {
		this.dateNomination = dateNomination;
	}

	public String getDateIntegration() {
		return dateIntegration;
	}

	public void setDateIntegration(String dateIntegration) {
		this.dateIntegration = dateIntegration;
	}

}
