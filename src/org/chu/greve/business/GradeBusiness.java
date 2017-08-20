package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Grade;

public interface GradeBusiness {
	public int addGrade(Grade g);
	public Grade selectGrade(int id);
	public List<Grade> selectAllGrade();
	public int modifyGrade(Grade g);
	public int deleteGrade(int id);
}
