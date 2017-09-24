package org.chu.greve.models;

import java.util.ArrayList;
import java.util.List;

public class Greviste {
	private int id;
	private Greve greve;
	private Fonctionnaire greviste;
	private Service service;
	private String retenuSalaire;
	private List<String> jours;

	public Greviste() {
		jours = new ArrayList<>();
		greviste = new Fonctionnaire();
	}

	public Greviste(Greve greve, Fonctionnaire greviste) {
		this();
		this.greve = greve;
		this.greviste = greviste;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Greve getGreve() {
		return greve;
	}

	public void setGreve(Greve greve) {
		this.greve = greve;
	}

	public Fonctionnaire getGreviste() {
		return greviste;
	}

	public void setGreviste(Fonctionnaire greviste) {
		this.greviste = greviste;
	}

	public void ajouterJour(String jour) {
		jours.add(jour);
	}

	public List<String> getJours() {
		return jours;
	}

	public void setJours(List<String> jours) {
		this.jours = jours;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getRetenuSalaire() {
		return retenuSalaire;
	}

	public void setRetenuSalaire(String retenuSalaire) {
		this.retenuSalaire = retenuSalaire;
	}
}
