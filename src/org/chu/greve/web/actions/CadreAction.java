package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.chu.greve.business.CadreBusinessImpl;
import org.chu.greve.business.Cadrebusiness;
import org.chu.greve.dao.CadreDaoImpl;
import org.chu.greve.models.Cadre;
import org.chu.greve.util.HibernateUtil;

public class CadreAction {
	private Cadrebusiness cadreService;
	private List<Cadre> cadres;
	private Cadre cadreUpdate;

	@PostConstruct
	public void init() {
		cadreService = new CadreBusinessImpl(new CadreDaoImpl(HibernateUtil.getSessionFactory()));
		refreshList();
		cadreUpdate = new Cadre();
	}

	public Cadrebusiness getCadreService() {
		return cadreService;
	}

	public void setCadreService(Cadrebusiness cadreService) {
		this.cadreService = cadreService;
	}

	public Cadre getCadreUpdate() {
		return cadreUpdate;
	}

	public void setCadreUpdate(Cadre cadreUpdate) {
		this.cadreUpdate = cadreUpdate;
	}

	public List<Cadre> getCadres() {
		return cadres;
	}

	public void setCadres(List<Cadre> cadres) {
		this.cadres = cadres;
	}

	public void addCadre(Cadre cadre) {
		int r = cadreService.createCadre(cadre);
		refreshList();
		if (r == 1) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadre enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void removeCadre(Cadre cadre) {

		int r = cadreService.deleteCadre(cadre.getId());
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadre supprimé avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la suppression."));
		}

	}

	public void updateCadre(Cadre cadre) {
		int r = cadreService.updateCadre(cadre);
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
		cadres = cadreService.listCadre();
		Collections.reverse(cadres);
	}

}
