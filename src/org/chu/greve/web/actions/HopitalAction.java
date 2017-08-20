package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.chu.greve.business.HopitalService;
import org.chu.greve.business.HopitalServiceImpl;
import org.chu.greve.dao.HopitalDaoImpl;
import org.chu.greve.models.Hopital;
import org.chu.greve.models.Service;
import org.chu.greve.util.HibernateUtil;

public class HopitalAction {
	private HopitalService hopitalService;
	private Hopital hopital;
	private List<Hopital> listHopital;
	private List<Service> listService;
	private Service serviceUpdate;

	public List<Service> getListService() {
		return listService;
	}

	public void setListService(List<Service> listService) {
		this.listService = listService;
	}

	@PostConstruct
	public void init() {
		hopitalService = new HopitalServiceImpl(new HopitalDaoImpl(HibernateUtil.getSessionFactory()));
		serviceUpdate = new Service();
		refreshListHopital();

	}

	public Service getServiceUpdate() {
		return serviceUpdate;
	}

	public void setServiceUpdate(Service service) {
		serviceUpdate = service;
	}

	public Hopital getHopital() {
		return hopital;
	}

	public void setHopital(Hopital hopital) {
		this.hopital = hopital;
	}

	public List<Hopital> getListHopital() {
		return listHopital;
	}

	public void setListHopital(List<Hopital> listHopital) {
		this.listHopital = listHopital;
	}

	public void addService(Service service) {
		boolean flag = false;
		if (service.getHopital().getIdH() == 0) {
			service.getHopital().setIdH(getHopital().getIdH());
			flag = true;
		}
		int r = hopitalService.addService(service);
		if (r == 1) {
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Service ajouté avec succès");
			if (flag)
				refreshListService();

		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de l'enregistrement");

		}
	}

	public void addHopital(Hopital hopital) {
		int r = hopitalService.addHopital(hopital);
		if (r == 1) {
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Hopital ajouté avec succès");
			refreshListHopital();
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de l'enregistrement");

		}
	}

	public void deleteHopital(Hopital hopital) {
		int r = hopitalService.deleteHopital(hopital);
		if (r == 1) {

			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Hopital supprimé avec succès");
			refreshListHopital();
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la suppression");

		}
	}

	public void deleteService(Service service) {
		int r = hopitalService.deleteService(service);
		if (r == 1) {
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Service supprimé avec succès");
			refreshListService();
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la suppression");

		}

	}

	public void updateService(Service service) {

		int r = hopitalService.updateService(service);
		if (r == 1) {
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Service modifié avec succès");
			refreshListService();
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la modification");

		}
	}

	public void updateHopital(Hopital hopital) {
		int r = hopitalService.updateHopital(hopital);
		if (r == 1) {
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Hôpital modifié avec succès");
			refreshListHopital();
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la modification");

		}
	}

	public String serviceNavigation(Hopital hopital) {
		setHopital(hopital);
		refreshListService();
		return "service";
	}

	private void refreshListHopital() {

		listHopital = hopitalService.listHopital();
		Collections.reverse(listHopital);
	}

	private void refreshListService() {
		listService = hopitalService.listService(hopital);
		Collections.reverse(listService);
	}

	private void addMessage(FacesMessage.Severity severity, String label, String message) {
		FacesMessage msg = new FacesMessage(severity, label, message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
