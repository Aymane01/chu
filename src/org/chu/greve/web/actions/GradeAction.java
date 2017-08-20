package org.chu.greve.web.actions;

import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.chu.greve.business.GradeBusiness;
import org.chu.greve.business.GradeBusinessImpl;
import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.business.SpecialiteBusinessImpl;
import org.chu.greve.dao.GradeDaoHibernate;
import org.chu.greve.dao.SpecialiteDaoHibernate;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Specialite;
import org.chu.greve.util.HibernateUtil;

public class GradeAction {
	private GradeBusiness gradeService;
	private List<Grade> grades;
	private Grade gradeUpdate;

	public GradeBusiness getGradeService() {
		return gradeService;
	}

	public void setGradeService(GradeBusiness gradeService) {
		this.gradeService = gradeService;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}


	public Grade getGradeUpdate() {
		return gradeUpdate;
	}

	public void setGradeUpdate(Grade gradeUpdate) {
		this.gradeUpdate = gradeUpdate;
	}

	@PostConstruct
	public void init() {
		gradeService = new GradeBusinessImpl(new GradeDaoHibernate(HibernateUtil.getSessionFactory()));
		System.out.println("HERE");
		refreshList();
	}

	public void addGrade(Grade grade) {

		int r = gradeService.addGrade(grade);
		refreshList();
		if (r == 1) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Grade enregistré avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors d'enregistrement."));
		}
	}

	public void removeGrade(Grade grade) {

		int r = gradeService.deleteGrade(grade.getIdG());
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Specialite supprimée avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la suppression."));
		}

	}

	public void updateSpecialite(Grade grade) {
		int r = gradeService.modifyGrade(grade);;
		refreshList();
		if (r == 1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Grade modifiée avec succès."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
					"Une erreur s'est produit lors de la modifcation."));
		}
	}

	public void refreshList() {
		grades = gradeService.selectAllGrade();
		System.out.println(grades.size());
		Collections.reverse(grades);
	}

}
