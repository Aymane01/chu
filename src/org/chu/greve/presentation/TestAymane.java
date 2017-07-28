package org.chu.greve.presentation;

import java.util.List;

import org.chu.greve.dao.CadreDao;
import org.chu.greve.dao.CadreDaoImpl;
import org.chu.greve.models.Cadre;
import org.chu.greve.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestAymane {

	public TestAymane() {

		test01();
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
