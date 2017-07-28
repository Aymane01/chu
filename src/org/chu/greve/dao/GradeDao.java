package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.models.Grade;

public interface GradeDao {
	public int insert(Grade grade);
	public void delete(int id);
	public Grade select(int id);
	public List<Grade> selectAll();
	public void modify(Grade g);
}
