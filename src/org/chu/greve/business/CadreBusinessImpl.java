package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.CadreDao;
import org.chu.greve.models.Cadre;

public class CadreBusinessImpl implements Cadrebusiness {
	private CadreDao cadreDao;

	public CadreBusinessImpl(CadreDao cadreDao) {
		this.cadreDao = cadreDao;
	}

	public int createCadre(Cadre cadre) {

		return cadreDao.insertCadre(cadre);
	}

	public int deleteCadre(int id) {
		return cadreDao.deleteCadre(id);
	}

	public int updateCadre(Cadre cadre) {
		return cadreDao.updateCadre(cadre);
	}

	public List<Cadre> listCadre() {

		return cadreDao.listCadre();
	}

	@Override
	public Cadre selectCadre(String id) {
		return cadreDao.selectCadre(id);

	}

}
