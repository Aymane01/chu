package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Resident;

public interface ResidentBusiness {
	public int addResident(Resident resident);

	public int updateResident(Resident resident);

	public int deleteResident(Resident resident);

	public List<Resident> listResident();

}
