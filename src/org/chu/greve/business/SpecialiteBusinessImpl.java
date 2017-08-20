package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.SpecialiteDao;
import org.chu.greve.models.Specialite;

public class SpecialiteBusinessImpl implements SpecialiteBusiness{
	

	private SpecialiteDao daoS;
	
	public SpecialiteBusinessImpl(SpecialiteDao daoS) {
		this.daoS =daoS;
	}
	@Override
	public int addSpecialite(Specialite spec) {
		return daoS.insert(spec);
	}

	@Override
	public Specialite selectSpecialite(int id) {
		return daoS.select(id);
	}

	@Override
	public List<Specialite> selectAllSpecilite() {
		return daoS.selectAll();
	}

	@Override
	public void modifySpecialite(Specialite spec) {
		daoS.modify(spec);
	}

	@Override
	public void deleteSpecialite(int id) {
		daoS.delete(id);
	}

}
