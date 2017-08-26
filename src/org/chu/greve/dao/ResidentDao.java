package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.models.Resident;

public interface ResidentDao {
	public int addResident(Resident resident);

	public int updateResident(Resident resident);

	public int deleteResident(Resident resident);

	public List<Resident> listResident();

}
