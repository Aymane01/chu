package org.chu.greve.business;

import java.util.List;


import org.chu.greve.dao.NationaliteDao;

import org.chu.greve.models.Nationalite;

public class NationaliteBusinessImpl  implements Nationalitebusiness{

	
	private NationaliteDao nationalitedao;

	public NationaliteBusinessImpl(NationaliteDao nationalitedao) {
		this.nationalitedao = nationalitedao;
	}

	public int createNationalite(Nationalite nationalite) {
		return nationalitedao.insertNationalite(nationalite);
	}

	public int deleteNationalite(int id) {
		return nationalitedao.deleteNationalite(id);
	}

	public int updateNationalite(Nationalite nationalite) {
		return nationalitedao.updateNationalite(nationalite);
	}

	public List<Nationalite> listNationalite() {

		return nationalitedao.listNationalite();
	}

	@Override
	public List<Nationalite> listNationaliste() {
		// TODO Auto-generated method stub
		return null;
	}

}

