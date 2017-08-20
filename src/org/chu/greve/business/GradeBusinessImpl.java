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
	public int modifyGrade(Grade g) {
		try {
			daoG.modify(g);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
		
	}

	@Override
	public int deleteGrade(int id) {
		try {
			daoG.delete(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}
}
