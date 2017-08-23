package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.models.Employe;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Interne;

public interface EmployeDao {

	public Employe select(String CIN);
	public List<Employe> selectbyName(String nomFr);
	public List<Employe> selectAll();
	public int insert(Employe f);
	public int delete(String CIN);
	public int modify(Employe f);
}
