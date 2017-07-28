package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Cadre;

public interface Cadrebusiness {

	public int createCadre(Cadre cadre);

	public int deleteCadre(int id);

	public int updateCadre(Cadre cadre);

	public List<Cadre> listCadre();
}
