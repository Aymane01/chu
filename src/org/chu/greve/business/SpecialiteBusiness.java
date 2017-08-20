package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Specialite;

public interface SpecialiteBusiness {
	public int addSpecialite(Specialite spec);
	public Specialite selectSpecialite(int id);
	public List<Specialite> selectAllSpecilite();
	public int modifySpecialite(Specialite spec);
	public int deleteSpecialite(int id);
}
