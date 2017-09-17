package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.models.Compte;


public interface CompteDao {
	public int insertCompte(Compte compte);

	public int deleteCompte(int id);

	public int updateCompte(Compte compte);

	public List<Compte> listCompte();
}
