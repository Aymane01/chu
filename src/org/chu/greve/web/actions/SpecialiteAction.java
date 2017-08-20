package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.business.SpecialiteBusinessImpl;
import org.chu.greve.dao.SpecialiteDaoHibernate;
import org.chu.greve.models.Specialite;
import org.chu.greve.util.HibernateUtil;

public class SpecialiteAction {
	private SpecialiteBusiness specService;
	private List<Specialite> specialites;
	private Specialite specialiteUpdate;

	
	@PostConstruct
	public void init() {
		specService = new SpecialiteBusinessImpl(new SpecialiteDaoHibernate(HibernateUtil.getSessionFactory()));
		System.out.println("HERE");
		refreshList();
	}

	public SpecialiteBusiness getSpecService() {
		return specService;
	}

	public void setSpecService(SpecialiteBusiness specService) {
		this.specService = specService;
	}

	public List<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<Specialite> specialites) {
		this.specialites = specialites;
	}


	public Specialite getSpecialiteUpdate() {
		return specialiteUpdate;
	}

	public void setSpecialiteUpdate(Specialite specUpdate) {
		this.specialiteUpdate = specUpdate;
	}

	public void addSpecialite(Specialite specialite) {

		int r = specService.addSpecialite(specialite);
		refreshList();
		if (r == 1) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Specialite enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void removeSpecialite(Specialite specialite) {

		int r = specService.deleteSpecialite(specialite.getIdS());
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Specialite supprimée avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la suppression."));
		}

	}

	public void updateSpecialite(Specialite specialite) {
		int r = specService.modifySpecialite(specialite);;
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Specialite modifiée avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la modifcation."));
		}
	}

	public void refreshList() {
		specialites = specService.selectAllSpecilite();
		System.out.println(specialites.size());
		Collections.reverse(specialites);
	}

}
