package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Grade;
import org.chu.greve.models.Specialite;

public interface ApplicationBusiness {
	public int addGrade(Grade g);
	public Grade selectGrade(int id);
	public List<Grade> selectAllGrade();
	public void modifyGrade(Grade g);
	public void deleteGrade(int id);
	
	
	public int addSpecialite(Specialite spec);
	public Specialite selectSpecialite(int id);
	public List<Specialite> selectAllSpecilite();
	public void modifySpecialite(Specialite spec);
	public void deleteSpecialite(int id);
}
