package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.InterneDao;
import org.chu.greve.models.Interne;

public class InterneBusinessImp implements InterneBusiness {
	
	private InterneDao daoI;
	
	public InterneBusinessImp(InterneDao daoI) {
		this.daoI = daoI;
	}
	
	@Override
	public int addInterne(Interne interne) {
		return daoI.insert(interne);
	}

	@Override
	public Interne selectInterne(String CIN) {
		return daoI.select(CIN);
	}

	@Override
	public List<Interne> selectAllInterne() {
		return daoI.selectAll();
	}

	@Override
	public int modifyInterne(Interne interne) {
		// TODO Auto-generated method stub
		try {
			daoI.modify(interne);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int deleteInterne(String CIN) {
		// TODO Auto-generated method stub
		try {
			daoI.delete(CIN);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	

}
