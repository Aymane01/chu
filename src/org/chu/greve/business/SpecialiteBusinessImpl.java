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
	public int modifySpecialite(Specialite spec) {
		try {
			daoS.modify(spec);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public int deleteSpecialite(int id) {
		try {
			daoS.delete(id);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	@Override
	public Specialite selectSpecialite(String intituleFr) {
		// TODO Auto-generated method stub
		return daoS.select(intituleFr);
	}

}
