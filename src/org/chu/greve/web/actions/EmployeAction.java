package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.chu.greve.business.EmployeBusiness;
import org.chu.greve.business.EmployeBusinessImpl;
import org.chu.greve.dao.CadreDao;
import org.chu.greve.dao.CorpsDao;
import org.chu.greve.dao.GradeDao;
import org.chu.greve.dao.EmployeDaoHibernate;
import org.chu.greve.dao.SpecialiteDao;
import org.chu.greve.models.Employe;
import org.chu.greve.util.HibernateUtil;
import org.chu.greve.util.Nationalite;

public class EmployeAction {

	private EmployeBusiness service;
	private String nationalite[];
	private List<Employe> listeEmploye;
	private Employe employeUpdate;

	public Employe getEmployeUpdate() {
		return employeUpdate;
	}

	public void setEmployeUpdate(Employe employeUpdate) {
		this.employeUpdate = employeUpdate;
	}

	@PostConstruct
	public void init() {
		service = new EmployeBusinessImpl(new EmployeDaoHibernate(HibernateUtil.getSessionFactory()));
		nationalite = Nationalite.getNationalite();
		refreshListEmploye();
		employeUpdate = new Employe();
	}

	public void addEmploye(Employe employe) {
		int r = service.addEmploye(employe);
		if (r == 1) {
			refreshListEmploye();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Résident enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void updateEmploye(Employe employe) {
		refreshListEmploye();
		int r = service.modifyEmploye(employe);
		if (r == 1) {
			refreshListEmploye();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Résident supprimé avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la modification");
		}
	}

	public void deleteEmploye(String CIN) {
		int r = service.deleteEmploye(CIN);
		if (r == 1) {
			refreshListEmploye();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Résident supprimé avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la suppression");
		}
	}

	private void addMessage(FacesMessage.Severity severity, String label, String message) {
		FacesMessage msg = new FacesMessage(severity, label, message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void refreshListEmploye() {
		listeEmploye = service.selectAllEmploye();
		Collections.reverse(listeEmploye);
	}

	public String[] getNationalite() {
		return nationalite;
	}

	public void setNationalite(String[] nationalite) {
		this.nationalite = nationalite;
	}

	public List<Employe> getListeEmploye() {
		return listeEmploye;
	}

	public void setListeEmploye(List<Employe> listeEmploye) {
		this.listeEmploye = listeEmploye;
	}
}
