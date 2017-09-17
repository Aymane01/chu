package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.chu.greve.business.ProfesseurBusiness;
import org.chu.greve.business.ProfesseurBusinessImpl;
import org.chu.greve.dao.ProfesseurDaoImpl;
import org.chu.greve.models.Professeur;
import org.chu.greve.util.HibernateUtil;
import org.chu.greve.util.Nationalite;

public class ProfesseurAction {
	private ProfesseurBusiness service;
	private List<Professeur> listProfesseur;
	private Professeur professeurUpdate;
	private String[] nationalite;

	@PostConstruct
	public void init() {
		service = new ProfesseurBusinessImpl(new ProfesseurDaoImpl(HibernateUtil.getSessionFactory()));
		refreshListProfesseur();
		nationalite = Nationalite.getNationalite();
		professeurUpdate = new Professeur();
	}

	public void addProfesseur(Professeur professeur) {
		int r = service.addProfesseur(professeur);
		if (r == 1) {
			refreshListProfesseur();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Professeur ajouté avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de l'enregistrement");
		}
	}

	public void updateProfesseur(Professeur professeur) {
		int r = service.updateProfesseur(professeur);
		if (r == 1) {
			refreshListProfesseur();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Professeur modifié avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la modification");
		}
	}

	public void deleteProfesseur(Professeur professeur) {
		int r = service.deleteProfesseur(professeur);
		if (r == 1) {
			refreshListProfesseur();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Professeur supprimé avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la suppression");
		}
	}

	public List<Professeur> getListProfesseur() {
		return listProfesseur;
	}

	public void setListProfesseur(List<Professeur> listProfesseur) {
		this.listProfesseur = listProfesseur;
	}

	public Professeur getProfesseurUpdate() {
		return professeurUpdate;
	}

	public void setProfesseurUpdate(Professeur professeurUpdate) {
		this.professeurUpdate = professeurUpdate;
	}

	private void addMessage(FacesMessage.Severity severity, String label, String message) {
		FacesMessage msg = new FacesMessage(severity, label, message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void refreshListProfesseur() {
		listProfesseur = service.listProfesseur();
		Collections.reverse(listProfesseur);
	}

	public String[] getNationalite() {
		return nationalite;
	}

	public void setNationalite(String[] nationalite) {
		this.nationalite = nationalite;
	}
}