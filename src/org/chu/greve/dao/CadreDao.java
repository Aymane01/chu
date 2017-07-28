package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.models.Cadre;

public interface CadreDao {


	public int insertCadre(Cadre cadre);

	public int deleteCadre(int id);

	public int updateCadre(Cadre cadre);

	public List<Cadre> listCadre();
}
