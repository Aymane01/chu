package org.chu.greve.presentation;

import java.util.List;

import org.chu.greve.dao.CadreDao;
import org.chu.greve.dao.CadreDaoImpl;
import org.chu.greve.dao.HopitalDao;
import org.chu.greve.dao.HopitalDaoImpl;
import org.chu.greve.models.Cadre;
import org.chu.greve.models.Hopital;
import org.chu.greve.models.Service;
import org.chu.greve.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestAymane {

	public TestAymane() {

		test02();
	}
	public void test02() {
		// Test hopital and service
				HopitalDao dao = new HopitalDaoImpl(HibernateUtil.getSessionFactory());
				Hopital hopital = new Hopital("Test Hopital", "Test Hopital");
				Service service = new Service("Test Service", "Test Service");
				Hopital h = new Hopital();

				 dao.insertHopital(hopital);
				// service.setHopital(hopital);
				// dao.insertService(service);
				// List<Service> ss = dao.listService(hopital);
				//
				// for (int i = 0; i < ss.size(); i++) {
				// System.out.println(ss.get(i).getIdSe());
				// }
				// h.setIdH(29);
				// dao.deletHopital(h);
//				service.setIdSe(4);
				// dao.deleteService(service);
		// dao.updateService(service);
	}

	private void test01() {
		// Test of CadreDao Functions
		CadreDao cadreDao = new CadreDaoImpl(HibernateUtil.getSessionFactory());
		Cadre c = new Cadre(1,"inge", "النالي مريم");
		cadreDao.insertCadre(c);
		cadreDao.insertCadre(c);
		cadreDao.insertCadre(c);
		// cadreDao.deleteCadre(1);
		// cadreDao.deleteCadre(2);
		// cadreDao.deleteCadre(3);
		// cadreDao.deleteCadre(4);
		// cadreDao.updateCadre(c);

		// List<Cadre> list = cadreDao.listCadre();
		// for (Cadre cadre : list) {
		// System.out.println(cadre.getIntituleAr());
		// }

	}

	

	public static void main(String[] args) {
		new TestAymane();
	}

}
