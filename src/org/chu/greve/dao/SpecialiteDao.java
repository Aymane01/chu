package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.models.Specialite;

public interface SpecialiteDao {
	public Specialite select(int id);
	public Specialite select(String intituleFr);
	public List<Specialite> selectAll();
	public int insert(Specialite spec);
	public void delete(int id);
	public void modify(Specialite spec);
}
