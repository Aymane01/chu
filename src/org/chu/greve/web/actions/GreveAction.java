package org.chu.greve.web.actions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.chu.greve.business.GreveBusiness;
import org.chu.greve.business.GreveBusinessImpl;
import org.chu.greve.dao.GreveDaoImpl;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Greve;
import org.chu.greve.models.Greviste;
import org.chu.greve.util.DocumentGeneratorGreve;
import org.chu.greve.util.HibernateUtil;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class GreveAction {
	private GreveBusiness service;
	private List<Greve> listGreve;
	private List<Greviste> listGreviste;
	private Greve greveUpdate;
	private Greviste greviste;
	private Greviste currentGreviste;
	private DocumentGeneratorGreve documentGenerator;
	private List<Fonctionnaire> listFonctionnaire;
	private int counter;
	private String jour;
	private String currentJour;
	private List<String> listJour;
	private String prefix;
	private StreamedContent file;
	private List<String> listServices;
	private List<Greviste> listeUrgences;
	private Date dateR;
	private int days;
	private String heureR;
	private Date dateRa;
	private Date dateDe;
	private Date dateRep;

	@PostConstruct
	public void init() {
		service = new GreveBusinessImpl(new GreveDaoImpl(HibernateUtil.getSessionFactory()));
		greveUpdate = new Greve();
		refreshListFontionnaire();
		refreshListGreve();
		documentGenerator = new DocumentGeneratorGreve();
		prefix = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("prefix");
		listServices = new ArrayList<>();
		listeUrgences = new ArrayList<>();
		loadServices();
	}

	public void addGreve(Greve greve) {
		int r = service.addGreve(greve);
		if (r == 1) {
			refreshListGreve();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Grève ajoutée avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de l'enregistrement");
		}
	}

	public void showDownloadMessage() {
		System.out.println("here");
		addMessage(FacesMessage.SEVERITY_INFO, "Info", "Le document est crée avec succès");

	}

	public void generateAvertissement(Greviste g, Date dateD, Date dateR) {
		try {
			String path = "resources/Avertissement.docx";
			g.setJours(service.listJour(g));

			if (documentGenerator.generateAvertissement(g, prefix, dateD, dateR) == 1) {
				InputStream stream;
				stream = new FileInputStream(prefix + path);
				file = new DefaultStreamedContent(stream, "doc/docx",
						"Avertissement_" + g.getGreviste().getCin() + ".docx");
				addMessage(FacesMessage.SEVERITY_INFO, "Info", "Le document est crée avec succès");
				service.retenuSalaire(g);
			} else {
				addMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
						"Une erreur s'est produit lors de la création du document");
				file = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void generateDemandeExplication(Greviste g, Date date, String heure) {
		try {
			String path = "resources/DemandeExplication.docx";
			g.setJours(service.listJour(g));

			if (documentGenerator.generateDemandeExplication(g, prefix, date, heure) == 1) {
				InputStream stream;
				stream = new FileInputStream(prefix + path);
				file = new DefaultStreamedContent(stream, "doc/docx",
						"Demande_Explication_" + g.getGreviste().getCin() + ".docx");
				addMessage(FacesMessage.SEVERITY_INFO, "Info", "Le document est crée avec succès");
				service.retenuSalaire(g);
			} else {
				addMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
						"Une erreur s'est produit lors de la création du document");
				file = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void generatePunition(Greviste g, Date date, int days) {
		System.out.println((date == null) + "  " + days);
		try {
			String path = "resources/Punition.docx";
			g.setJours(service.listJour(g));

			if (documentGenerator.generatePunition(g, prefix, date, days) == 1) {
				InputStream stream;
				stream = new FileInputStream(prefix + path);
				file = new DefaultStreamedContent(stream, "doc/docx", "Punition_" + g.getGreviste().getCin() + ".docx");
				addMessage(FacesMessage.SEVERITY_INFO, "Info", "Le document est crée avec succès");
				service.retenuSalaire(g);
			} else {
				addMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
						"Une erreur s'est produit lors de la création du document");
				file = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void generateArretTravail(Greviste g) {

		try {
			String path = "resources/ArretTravail.docx";
			g.setJours(service.listJour(g));

			if (documentGenerator.generateArretTravail(g, prefix) == 1) {
				InputStream stream;
				stream = new FileInputStream(prefix + path);
				file = new DefaultStreamedContent(stream, "doc/docx",
						"Arret_travail_" + g.getGreviste().getCin() + ".docx");
				addMessage(FacesMessage.SEVERITY_INFO, "Info", "Le document est crée avec succès");
				service.retenuSalaire(g);
			} else {
				addMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
						"Une erreur s'est produit lors de la création du document");
				file = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void generateRetenueDocument(Greviste g) {

		try {
			g.setJours(service.listJour(g));
			String path = "resources/RetenueSalaire.docx";
			if (documentGenerator.generateRetenueSalaire(g, prefix) == 1) {
				InputStream stream;
				stream = new FileInputStream(prefix + path);
				file = new DefaultStreamedContent(stream, "doc/docx",
						"Retenue_salaire_" + g.getGreviste().getCin() + ".docx");
				addMessage(FacesMessage.SEVERITY_INFO, "Info", "Le document est crée avec succès");
				service.retenuSalaire(g);
			} else {
				addMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
						"Une erreur s'est produit lors de la création du document");
				file = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public List<String> list(String query) {
		List<String> subList = new ArrayList<>();
		for (Fonctionnaire f : listFonctionnaire) {
			if (f.getCin().contains(query)) {
				subList.add(f.getCin());
			}
		}
		return subList;
	}

	public void addGreviste(Greviste g) {
		if (counter == 1) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Il faut ajouter au moins un jour du gréve");
			return;
		}
		g.setGreve(greveUpdate);
		int r = service.addGreviste(g);
		if (r == 1) {
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Gréviste ajouté avec succès.");
			setGreviste(new Greviste());
			refreshListGreviste();

		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de l'enregistrement");
		}
		counter = 1;
	}

	public void updateGreve(Greve greve) {
		int r = service.updateGreve(greve);
		if (r == 1) {
			refreshListGreve();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Grève modifiée avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la modificaton");
		}
	}

	public void deleteGreve(Greve greve) {
		int r = service.deleteGreve(greve);
		if (r == 1) {
			refreshListGreve();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Grève supprimée avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la suppression");
		}
	}

	public void deleteGreviste(Greviste greviste) {
		int r = service.deleteJours(greviste);
		if (r == 1)
			r = service.deleteGreviste(greviste);
		if (r == 1) {
			refreshListGreviste();
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Gréviste supprimé avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la suppression");
		}
	}

	public void deleteJour(String jour) {
		int r = service.deletJour(currentGreviste, jour);
		if (r == 1) {
			refreshJour(currentGreviste);
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Jour supprimé avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la suppression");
		}
	}

	public void updateJour(String j) {

		int r = service.updateJour(currentGreviste, currentJour, j);
		if (r == 1) {
			setJour(null);
			refreshJour(currentGreviste);
			addMessage(FacesMessage.SEVERITY_INFO, "Info", "Jour modifié avec succès.");
		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur s'est produit lors de la Modification");
		}
		refreshJour(currentGreviste);

	}

	private void refreshListGreve() {
		listGreve = service.listGreve();
		Collections.reverse(listGreve);
	}

	private void refreshListGreviste() {
		listGreviste = service.listGreviste(greveUpdate);
		Collections.reverse(listGreviste);
	}

	private void addMessage(FacesMessage.Severity severity, String label, String message) {
		FacesMessage msg = new FacesMessage(severity, label, message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String navigateToGreviste(Greve greve) {
		greviste = new Greviste();
		counter = 1;
		setGreveUpdate(greve);
		refreshListGreviste();
		refreshListFontionnaire();
		return "greviste";
	}

	public void ajouterJour(String jour) {
		greviste.ajouterJour(jour);
		setJour(null);
		addMessage(FacesMessage.SEVERITY_INFO, "Info", "Jour ajouté avec succès.");
		counter++;
	}

	public void refreshJour(Greviste greviste) {
		setCurrentGreviste(greviste);
		listJour = service.listJour(greviste);
	}

	public List<Greve> getListGreve() {
		return listGreve;
	}

	public void setListGreve(List<Greve> listGreve) {
		this.listGreve = listGreve;
	}

	public Greve getGreveUpdate() {
		return greveUpdate;
	}

	public void setGreveUpdate(Greve greveUpdate) {
		this.greveUpdate = greveUpdate;
	}

	private void refreshListFontionnaire() {
		listFonctionnaire = service.listFonctionnaire();
	}

	public List<Fonctionnaire> getListFonctionnaire() {
		return listFonctionnaire;
	}

	public void setListFonctionnaire(List<Fonctionnaire> listFonctionnaire) {
		this.listFonctionnaire = listFonctionnaire;
	}

	public Greviste getGreviste() {
		return greviste;
	}

	public void setGreviste(Greviste greviste) {
		this.greviste = greviste;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public List<Greviste> getListGreviste() {
		return listGreviste;
	}

	public void setListGreviste(List<Greviste> listGreviste) {
		this.listGreviste = listGreviste;
	}

	public List<String> getListJour() {
		return listJour;
	}

	public void setListJour(List<String> listJour) {
		this.listJour = listJour;
	}

	public Greviste getCurrentGreviste() {
		return currentGreviste;
	}

	public void setCurrentGreviste(Greviste currentGreviste) {
		this.currentGreviste = currentGreviste;
	}

	public String getCurrentJour() {
		return currentJour;
	}

	public void setCurrentJour(String j) {
		jour = j;
		currentJour = j;
		System.out.println(jour + "  " + currentJour);
	}

	public DocumentGeneratorGreve getDocumentGenerator() {
		return documentGenerator;
	}

	public void setDocumentGenerator(DocumentGeneratorGreve documentGenerator) {
		this.documentGenerator = documentGenerator;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public void loadListeUrgences() {
		listeUrgences.clear();
		for (Greviste g : listGreviste) {
			System.out.println(g.getGreviste().getService().getIntituleFr());
			if (checkService(g.getGreviste().getService().getIntituleFr())) {
				System.out.println("here " + listServices.size());
				listeUrgences.add(g);
			}

		}
	}

	private boolean checkService(String intituleService) {
		if (listServices.contains(intituleService))
			return true;
		else
			return false;
	}

	private void loadServices() {
		listServices.add("URGENCES");
		listServices.add("RÉANIMATION MÉRE ENFANT");
		listServices.add("URGENCES GYNÉCO OBSTÉTRIQUE");
		listServices.add("URGENCES PÉDIATRIQUES");
		listServices.add("RÉANIMATION");
		listServices.add("SERVICE DE LA MÉDECINE NUCLÉAIRE");
		listServices.add("SERVICE DE LA RADIOTHÉRAPIE");
		listServices.add("SERVICE DE L'ONCOLOGIE MÉDICALE");
		listServices.add("RÉANIMATION A1");
		listServices.add("RÉANIMATION A4");
		listServices.add("URGENCES ET PRÉ URGENCES");

	}

	public List<Greviste> getListeUrgences() {
		return listeUrgences;
	}

	public void setListeUrgences(List<Greviste> listeUrgences) {
		this.listeUrgences = listeUrgences;
	}

	public Date getDateR() {
		return dateR;
	}

	public void setDateR(Date dateR) {
		this.dateR = dateR;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getHeureR() {
		return heureR;
	}

	public void setHeureR(String heureR) {
		this.heureR = heureR;
	}

	public Date getDateRa() {
		return dateRa;
	}

	public void setDateRa(Date dateRa) {
		this.dateRa = dateRa;
	}

	public Date getDateDe() {
		return dateDe;
	}

	public void setDateDe(Date dateDe) {
		this.dateDe = dateDe;
	}

	public Date getDateRep() {
		return dateRep;
	}

	public void setDateRep(Date dateRep) {
		this.dateRep = dateRep;
	}
}
