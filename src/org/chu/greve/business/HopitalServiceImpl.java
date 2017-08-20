package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.HopitalDao;
import org.chu.greve.models.Hopital;
import org.chu.greve.models.Service;

public class HopitalServiceImpl implements HopitalService {
	private HopitalDao hopitalDao;

	public HopitalServiceImpl() {

	}

	public HopitalServiceImpl(HopitalDao hopitalDao) {
		this.hopitalDao = hopitalDao;
	}

	public HopitalDao getHopitalDao() {
		return hopitalDao;
	}

	public void setHopitalDao(HopitalDao hopitalDao) {
		this.hopitalDao = hopitalDao;
	}

	public int addHopital(Hopital hopital) {
		return hopitalDao.insertHopital(hopital);
	}

	public int addService(Service service) {
		return hopitalDao.insertService(service);
	}

	public List<Service> listService(Hopital hopital) {
		return hopitalDao.listService(hopital);
	}

	public List<Hopital> listHopital() {
		return hopitalDao.listHopital();
	}

	public int deleteHopital(Hopital hopital) {
		return hopitalDao.deletHopital(hopital);
	}

	public int deleteService(Service service) {
		return hopitalDao.deleteService(service);
	}

	public int updateHopital(Hopital hopital) {
		return hopitalDao.updateHopital(hopital);
	}

	public int updateService(Service service) {
		return hopitalDao.updateService(service);
	}

}
