package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.models.Hopital;
import org.chu.greve.models.Service;

public interface HopitalDao extends SessionDao {
	public int insertHopital(Hopital hopital);

	public int insertService(Service service);

	public int updateHopital(Hopital hopital);

	public int updateService(Service service);

	public List<Service> listService(Hopital hopital);

	public List<Hopital> listHopital();

	public int deletHopital(Hopital hopital);

	public int deleteService(Service service);

	public Service selectService(String id);

	public Hopital selectHopital(String id);
}
