package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.chu.greve.business.CadreBusinessImpl;
import org.chu.greve.business.EmployeBusiness;
import org.chu.greve.business.EmployeBusinessImpl;
import org.chu.greve.business.InterneBusiness;
import org.chu.greve.business.InterneBusinessImp;
import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.business.SpecialiteBusinessImpl;
import org.chu.greve.dao.CadreDaoImpl;
import org.chu.greve.dao.EmployeDaoHibernate;
import org.chu.greve.dao.InterneDaoHibernate;
import org.chu.greve.dao.SpecialiteDaoHibernate;
import org.chu.greve.models.Cadre;
import org.chu.greve.models.Employe;
import org.chu.greve.models.Interne;
import org.chu.greve.models.Service;
import org.chu.greve.models.Specialite;
import org.chu.greve.util.HibernateUtil;

public class EmployeAction {
	private EmployeBusiness employeService;
	private List<Employe> employes;
	private Employe employeUpdate;
	private List<Service> listService;

	
	@PostConstruct
	public void init() {
		employeService = new EmployeBusinessImpl(new EmployeDaoHibernate(HibernateUtil.getSessionFactory()));
		employeUpdate = new Employe();
		refreshList();
	}
	
	public Employe getEmploye(String name) {
		for (Employe emp : employes) {
			if(emp.getNomFr().equals(name)) {
				return emp;
			}
		}
		return null;
	}
	
	public void addEmploye(Employe f) {

		int r = employeService.addEmploye(f);
		refreshList();
		if (r == 1) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Nouveau Fonctionnaire enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void removeEmploye(Employe emp) {

		int r = employeService.deleteEmploye(emp.getCin());
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Fonctionnaire supprimée avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la suppression."));
		}

	}

	public void updateEmploye(Employe f) {
		int r = employeService.modifyEmploye(f);;
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Fonctionnaire modifiée avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la modifcation."));
		}
	}

	public void refreshList() {
		employes = employeService.selectAllEmploye();
		Collections.reverse(employes);
	}
	private void refreshListService() {
		//Collections.reverse(listService);
	}

	public EmployeBusiness getEmployeService() {
		return employeService;
	}

	public void setEmployeService(EmployeBusiness employeService) {
		this.employeService = employeService;
	}

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public Employe getEmployeUpdate() {
		return employeUpdate;
	}

	public void setEmployeUpdate(Employe employeUpdate) {
		this.employeUpdate = employeUpdate;
	}

	public List<Service> getListService() {
		return listService;
	}

	public void setListService(List<Service> listService) {
		this.listService = listService;
	}

	
	
	
}
