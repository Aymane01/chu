package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.chu.greve.business.CadreBusinessImpl;
import org.chu.greve.business.Cadrebusiness;
import org.chu.greve.business.CompteBusiness;
import org.chu.greve.business.CompteBusinessImpl;
import org.chu.greve.dao.CadreDaoImpl;
import org.chu.greve.dao.CompteDaoImpl;
import org.chu.greve.models.Cadre;
import org.chu.greve.models.Compte;
import org.chu.greve.util.HibernateUtil;

public class CompteAction {
	private CompteBusiness compteService;
	private List<Compte> comptes;
	private Compte compteUpdate;

	@PostConstruct
	public void init() {
		compteService = new CompteBusinessImpl(new CompteDaoImpl(HibernateUtil.getSessionFactory()));
		refreshList();
		compteUpdate = new Compte();
	}

	

	public CompteBusiness getCompteService() {
		return compteService;
	}



	public void setCompteService(CompteBusiness compteService) {
		this.compteService = compteService;
	}



	public List<Compte> getComptes() {
		return comptes;
	}



	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}



	public Compte getCompteUpdate() {
		return compteUpdate;
	}



	public void setCompteUpdate(Compte compteUpdate) {
		this.compteUpdate = compteUpdate;
	}



	public void addCompte(Compte compte) {
		int r = compteService.createCompte(compte);
		refreshList();
		if (r == 1) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Compte enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void removeCompte(Compte compte) {

		int r = compteService.deleteCompte(compte.getId());
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Compte supprimé avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la suppression."));
		}

	}

	public void updateCompte(Compte compte) {
		int r = compteService.updateCompte(compte);
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadre modifié avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la modifcation."));
		}
	}

	public void refreshList() {
		comptes = compteService.listCompte();
		Collections.reverse(comptes);
	}

}
