package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.chu.greve.business.ResidentBusiness;
import org.chu.greve.business.ResidentBusinessImpl;
import org.chu.greve.dao.CadreDao;
import org.chu.greve.dao.CorpsDao;
import org.chu.greve.dao.GradeDao;
import org.chu.greve.dao.ResidentDaoImpl;
import org.chu.greve.dao.SpecialiteDao;
import org.chu.greve.models.Resident;
import org.chu.greve.util.HibernateUtil;
import org.chu.greve.util.Nationalite;

public class ResidentAction {

	private ResidentBusiness service;
	private String nationalite[];
	private List<Resident> listeResident;
	private Resident residentUpdate;

	public Resident getResidentUpdate() {
		return residentUpdate;
	}

	public void setResidentUpdate(Resident residentUpdate) {
		this.residentUpdate = residentUpdate;
	}

	@PostConstruct
	public void init() {
		service = new ResidentBusinessImpl(new ResidentDaoImpl(HibernateUtil.getSessionFactory()));
		nationalite = Nationalite.getNationalite();
		refreshListResident();
		residentUpdate = new Resident();
	}

	public void addResident(Resident resident) {
		int r = service.addResident(resident);
		if (r == 1) {
			refreshListResident();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Résident enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void updateResident(Resident resident) {
		refreshListResident();
		int r = service.updateResident(resident);
		if (r == 1) {
			refreshListResident();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Résident supprimé avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la modification");
		}
	}

	public void deleteResident(Resident resident) {
		int r = service.deleteResident(resident);
		if (r == 1) {
			refreshListResident();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Résident supprimé avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la suppression");
		}
	}

	private void addMessage(FacesMessage.Severity severity, String label, String message) {
		FacesMessage msg = new FacesMessage(severity, label, message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void refreshListResident() {
		listeResident = service.listResident();
		Collections.reverse(listeResident);
	}

	public String[] getNationalite() {
		return nationalite;
	}

	public void setNationalite(String[] nationalite) {
		this.nationalite = nationalite;
	}

	public List<Resident> getListeResident() {
		return listeResident;
	}

	public void setListeResident(List<Resident> listeResident) {
		this.listeResident = listeResident;
	}
}
