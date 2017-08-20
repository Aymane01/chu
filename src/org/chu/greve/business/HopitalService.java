package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Hopital;
import org.chu.greve.models.Service;

public interface HopitalService {
	public int addHopital(Hopital hopital);

	public int addService(Service service);

	public int updateHopital(Hopital hopital);

	public int updateService(Service service);

	public List<Service> listService(Hopital hopital);

	public List<Hopital> listHopital();

	public int deleteHopital(Hopital hopital);

	public int deleteService(Service service);
}
