package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.models.Nationalite;



public interface NationaliteDao {
	
	public int insertNationalite(Nationalite nationalite);

	public int deleteNationalite(int id);

	public int updateNationalite(Nationalite nationalite);

	public List<Nationalite> listNationalite();
}
