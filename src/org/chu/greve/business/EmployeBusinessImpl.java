package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.EmployeDao;
import org.chu.greve.dao.InterneDao;
import org.chu.greve.models.Employe;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Interne;

public class EmployeBusinessImpl implements EmployeBusiness {

	private EmployeDao daoF;
	
	public EmployeBusinessImpl(EmployeDao daoF) {
		this.daoF = daoF;
	}
	
	@Override
	public int addEmploye(Employe f) {
		// TODO Auto-generated method stub
		return daoF.insert(f);
	}

	@Override
	public Employe selectEmploye(String CIN) {
		// TODO Auto-generated method stub
		return daoF.select(CIN);
	}

	@Override
	public List<Employe> selectAllEmploye() {
		// TODO Auto-generated method stub
		return daoF.selectAll();
	}

	@Override
	public int modifyEmploye(Employe f) {
		// TODO Auto-generated method stub
		return daoF.modify(f);
	}

	@Override
	public int deleteEmploye(String CIN) {
		// TODO Auto-generated method stub
		return daoF.delete(CIN);
	}

}
