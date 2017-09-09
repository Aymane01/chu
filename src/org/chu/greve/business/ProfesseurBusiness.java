package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Professeur;

public interface ProfesseurBusiness {
	public int addProfesseur(Professeur professeur);

	public int updateProfesseur(Professeur professeur);

	public int deleteProfesseur(Professeur professeur);

	public List<Professeur> listProfesseur();
}
