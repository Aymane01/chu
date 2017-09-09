package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.ProfesseurDao;
import org.chu.greve.models.Professeur;

public class ProfesseurBusinessImpl implements ProfesseurBusiness {
	private ProfesseurDao professeurDao;

	public ProfesseurBusinessImpl(ProfesseurDao professeurDao) {
		this.professeurDao = professeurDao;
	}

	public ProfesseurDao getProfesseurDao() {
		return professeurDao;
	}

	public void setProfesseurDao(ProfesseurDao professeurDao) {
		this.professeurDao = professeurDao;
	}

	public int addProfesseur(Professeur professeur) {
		return professeurDao.addProfesseur(professeur);
	}

	public int updateProfesseur(Professeur professeur) {
		return professeurDao.updateProfesseur(professeur);
	}

	public int deleteProfesseur(Professeur professeur) {
		return professeurDao.deleteProfesseur(professeur);
	}

	public List<Professeur> listProfesseur() {
		return professeurDao.listProfesseur();
	}

}
