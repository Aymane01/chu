package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.GradeDao;
import org.chu.greve.models.Grade;

public class GradeBusinessImpl implements GradeBusiness {
	private GradeDao daoG;
	
	public GradeBusinessImpl(GradeDao daoG) {
		this.daoG =daoG;
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
}
