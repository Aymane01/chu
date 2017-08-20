package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.chu.greve.business.InterneBusiness;
import org.chu.greve.business.InterneBusinessImp;
import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.business.SpecialiteBusinessImpl;
import org.chu.greve.dao.InterneDaoHibernate;
import org.chu.greve.dao.SpecialiteDaoHibernate;
import org.chu.greve.models.Interne;
import org.chu.greve.models.Specialite;
import org.chu.greve.util.HibernateUtil;

public class InterneAction {
	private InterneBusiness interneService;
	private List<Interne> internes;
	private Interne interneUpdate;
	private String query;

	public InterneBusiness getInterneService() {
		return interneService;
	}

	public void setInterneService(InterneBusiness interneService) {
		this.interneService = interneService;
	}

	public List<Interne> getInternes() {
		return internes;
	}

	public void setInternes(List<Interne> internes) {
		this.internes = internes;
	}

	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Interne getInterneUpdate() {
		return interneUpdate;
	}

	public void setInterneUpdate(Interne interneUpdate) {
		this.interneUpdate = interneUpdate;
	}

	@PostConstruct
	public void init() {
		interneService = new InterneBusinessImp(new InterneDaoHibernate(HibernateUtil.getSessionFactory()));
		refreshList();
	}

	public List<String> getPropositions(String query){
		List<String> propositions = new Vector<>();
		for (Interne interne : internes) {
			if(interne.getNomFr().contains(query)) {
				propositions.add(interne.getNomFr());
			}
		}
		return propositions;
	}
	
	public Interne getInterne(String name) {
		for (Interne interne : internes) {
			if(interne.getNomFr().equals(name)) {
				return interne;
			}
		}
		return null;
	}
	
	public void addInterne(Interne interne) {

		int r = interneService.addInterne(interne);
		refreshList();
		if (r == 1) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Nouveau Interne enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void removeInterne(Interne interne) {

		int r = interneService.deleteInterne(interne.getCin());
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Interne supprimée avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la suppression."));
		}

	}

	public void updateInterne(Interne interne) {
		int r = interneService.modifyInterne(interne);;
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Interne modifiée avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la modifcation."));
		}
	}

	public void refreshList() {
		internes = interneService.selectAllInterne();
		Collections.reverse(internes);
	}

}
