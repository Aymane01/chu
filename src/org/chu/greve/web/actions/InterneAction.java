package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.chu.greve.business.InterneBusiness;
import org.chu.greve.business.InterneBusinessImp;
import org.chu.greve.dao.CadreDao;
import org.chu.greve.dao.CorpsDao;
import org.chu.greve.dao.GradeDao;
import org.chu.greve.dao.InterneDaoHibernate;
import org.chu.greve.dao.SpecialiteDao;
import org.chu.greve.models.Interne;
import org.chu.greve.util.HibernateUtil;
import org.chu.greve.util.Nationalite;

public class InterneAction {

	private InterneBusiness service;
	private String nationalite[];
	private List<Interne> listeInterne;
	private Interne interneUpdate;

	public Interne getInterneUpdate() {
		return interneUpdate;
	}

	public void setInterneUpdate(Interne interneUpdate) {
		this.interneUpdate = interneUpdate;
	}

	@PostConstruct
	public void init() {
		service = new InterneBusinessImp(new InterneDaoHibernate(HibernateUtil.getSessionFactory()));
		nationalite = Nationalite.getNationalite();
		refreshListInterne();
		interneUpdate = new Interne();
	}

	public void addInterne(Interne interne) {
		int r = service.addInterne(interne);
		if (r == 1) {
			refreshListInterne();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Interne enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void updateInterne(Interne interne) {
		refreshListInterne();
		int r = service.modifyInterne(interne);
		if (r == 1) {
			refreshListInterne();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Interne modifié avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la modification");
		}
	}

	public void deleteInterne(String CIN) {
		System.out.println(CIN);
		int r = service.deleteInterne(CIN);
		if (r == 1) {
			refreshListInterne();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Interne supprimé avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la suppression");
		}
	}

	private void addMessage(FacesMessage.Severity severity, String label, String message) {
		FacesMessage msg = new FacesMessage(severity, label, message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void refreshListInterne() {
		listeInterne = service.selectAllInterne();
		Collections.reverse(listeInterne);
	}

	public String[] getNationalite() {
		return nationalite;
	}

	public void setNationalite(String[] nationalite) {
		this.nationalite = nationalite;
	}

	public List<Interne> getListeInterne() {
		return listeInterne;
	}

	public void setListeInterne(List<Interne> listeInterne) {
		this.listeInterne = listeInterne;
	}
}
