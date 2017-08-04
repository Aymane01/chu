package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.GradeDao;
import org.chu.greve.dao.SpecialiteDao;
import org.chu.greve.models.Grade;
import org.chu.greve.models.Specialite;

public class DefaultApplicationBusiness implements ApplicationBusiness {
	private GradeDao daoG;
	private SpecialiteDao daoS;
	
	
	public DefaultApplicationBusiness(GradeDao daoG, SpecialiteDao daoS) {
		super();
		this.daoG = daoG;
		this.daoS = daoS;
	}

	@Override
	public int addGrade(Grade g) {
		return daoG.insert(g);
	}

	@Override
	public Grade selectGrade(int id) {
		return daoG.select(id);
	}

	@Override
	public List<Grade> selectAllGrade() {
		return daoG.selectAll();
	}

	@Override
	public void modifyGrade(Grade g) {
		daoG.modify(g);
		
	}

	@Override
	public void deleteGrade(int id) {
		daoG.delete(id);
		
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
