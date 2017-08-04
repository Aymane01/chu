package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Specialite;

public interface SpecialiteBusiness {
	public int addSpecialite(Specialite spec);
	public Specialite selectSpecialite(int id);
	public List<Specialite> selectAllSpecilite();
	public void modifySpecialite(Specialite spec);
	public void deleteSpecialite(int id);
}
