package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.ResidentDao;
import org.chu.greve.models.Resident;

public class ResidentBusinessImpl implements ResidentBusiness {
	ResidentDao residentDao;

	public ResidentBusinessImpl() {
	}

	public ResidentBusinessImpl(ResidentDao residentDao) {
		this.residentDao = residentDao;
	}

	public ResidentDao getResidentDao() {
		return residentDao;
	}

	public void setResidentDao(ResidentDao residentDao) {
		this.residentDao = residentDao;
	}

	public int addResident(Resident resident) {
		return residentDao.addResident(resident);
	}

	public int updateResident(Resident resident) {
		return residentDao.updateResident(resident);
	}

	public int deleteResident(Resident resident) {
		return residentDao.deleteResident(resident);
	}

	public List<Resident> listResident() {
		return residentDao.listResident();
	}

}
