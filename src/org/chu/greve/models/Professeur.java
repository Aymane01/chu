package org.chu.greve.models;

public class Professeur extends Fonctionnaire {
	private String isChef;
	private String dateNomination;
	private String dateIntegration;

	public Professeur() {

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
