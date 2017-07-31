package org.chu.greve.presentation;

import java.util.List;

import org.chu.greve.dao.CadreDao;
import org.chu.greve.dao.CadreDaoImpl;
import org.chu.greve.dao.NationaliteDao;
import org.chu.greve.dao.NationaliteDaoImp;
import org.chu.greve.models.Cadre;
import org.chu.greve.models.Nationalite;
import org.chu.greve.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class TestMalak {

	public TestMalak() {

		test02();
	}

	private void test02() {
		// Test of NationaliteDao Functions
		NationaliteDao nationaliteDao = new NationaliteDaoImp(HibernateUtil.getSessionFactory());
		Nationalite n = new Nationalite("ffffff");
		nationaliteDao.insertNationalite(n);
		// nationaliteDao.insertNationalite(n);
		// nationaliteDao.insertNationalite(n);
		//nationaliteDao.deleteNationalite(1);
		// nationaliteDao.deleteNationalite(2);
		// nationaliteDao.deleteNationalite(3);
		// nationaliteDao.deleteNationalite(4);
		// nationaliteDao.deleteNationalite(5);

		// List<Nationalite> list = nationaliteDao.listNationalite();
		// for (Nationalite nationalite : list) {
		// System.out.println(nationalite.getIntitule());
		// }

	}

	public static void main(String[] args) {
		new TestMalak();
	}

}
