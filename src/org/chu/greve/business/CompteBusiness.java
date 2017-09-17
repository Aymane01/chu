package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Compte;

public interface CompteBusiness {
	public int createCompte(Compte c);

	public int deleteCompte(int id);

	public int updateCompte(Compte c);

	public List<Compte> listCompte();
	
	public boolean login(String username,String password);
}
