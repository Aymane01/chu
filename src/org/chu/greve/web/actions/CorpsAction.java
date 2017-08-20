package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.chu.greve.business.CadreBusinessImpl;
import org.chu.greve.business.Cadrebusiness;
import org.chu.greve.business.CorpsBusinessImpl;
import org.chu.greve.business.Corpsbusiness;
import org.chu.greve.dao.CadreDaoImpl;
import org.chu.greve.dao.CorpsDaoImpl;
import org.chu.greve.models.Cadre;
import org.chu.greve.models.Corps;
import org.chu.greve.util.HibernateUtil;

public class CorpsAction {
	private Corpsbusiness corpsService;
	private List<Corps> corps;
	private Corps corpsUpdate;

	@PostConstruct
	public void init() {
		corpsService = new CorpsBusinessImpl(new CorpsDaoImpl(HibernateUtil.getSessionFactory()));
		refreshList();
		corpsUpdate = new Corps();
	}

	

	public Corpsbusiness getCorpsService() {
		return corpsService;
	}



	public void setCorpsService(Corpsbusiness corpsService) {
		this.corpsService = corpsService;
	}



	public List<Corps> getCorps() {
		return corps;
	}



	public void setCorps(List<Corps> corps) {
		this.corps = corps;
	}



	public Corps getCorpsUpdate() {
		return corpsUpdate;
	}



	public void setCorpsUpdate(Corps corpsUpdate) {
		this.corpsUpdate = corpsUpdate;
	}



	public void addCorps(Corps corps) {
		int r = corpsService.createCorps(corps);
		refreshList();
		if (r == 1) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Corps enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void removeCorps(Corps corps) {

		int r = corpsService.deleteCorps(corps.getId());
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Corps supprimé avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la suppression."));
		}

	}

	public void updateCorps(Corps corps) {
		int r = corpsService.updateCorps(corps);
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Corps modifié avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la modifcation."));
		}
	}

	public void refreshList() {
		corps = corpsService.listCorps();
		Collections.reverse(corps);
	}

}
