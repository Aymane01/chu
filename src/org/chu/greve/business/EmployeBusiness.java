package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Employe;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Interne;

public interface EmployeBusiness {
	public int addEmploye(Employe f);
	public Employe selectEmploye(String CNE);
	public List<Employe> selectAllEmploye();
	public int modifyEmploye(Employe f);
	public int deleteEmploye(String CNE);
}
