package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.CadreDao;
import org.chu.greve.dao.CompteDao;
import org.chu.greve.models.Compte;

public class CompteBusinessImpl implements CompteBusiness {
	private CompteDao compteDao;

	public CompteBusinessImpl(CompteDao compteDao) {
		this.compteDao = compteDao;
	}

	@Override
	public int createCompte(Compte c) {
		// TODO Auto-generated method stub
		return compteDao.insertCompte(c);
	}

	@Override
	public int deleteCompte(int id) {
		// TODO Auto-generated method stub
		return compteDao.deleteCompte(id);
	}

	@Override
	public int updateCompte(Compte c) {
		// TODO Auto-generated method stub
		return compteDao.updateCompte(c);
	}

	@Override
	public List<Compte> listCompte() {
		// TODO Auto-generated method stub
		return compteDao.listCompte();
	}

	@Override
	public boolean login(String username, String password) {
		List<Compte> comptes = listCompte();
		for (Compte compte : comptes) {
			if(compte.getUsername().equals(username) && compte.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

}
