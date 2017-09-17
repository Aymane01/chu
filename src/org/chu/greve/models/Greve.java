package org.chu.greve.models;

public class Greve {
	private int idG;
	private String dateDebut;
	private String dateFin;

	public Greve() {
	}

	public Greve(int idG, String dateDebut, String dateFin) {

		this.idG = idG;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Greve(String dateDebut, String dateFin) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public int getIdG() {
		return idG;
	}

	public void setIdG(int idG) {
		this.idG = idG;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

}
