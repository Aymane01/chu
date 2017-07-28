package org.chu.greve.business;

import java.util.List;

import org.chu.greve.dao.CorpsDao;
import org.chu.greve.models.Corps;

public class CorpsBusinessImpl implements Corpsbusiness {
	private CorpsDao corpsDao;

	public CorpsBusinessImpl(CorpsDao corpsDao) {
		this.corpsDao = corpsDao;
	}

	public int createCorps(Corps corps) {
		return corpsDao.insertCorps(corps);
	}

	public int deleteCorps(int id) {
		return corpsDao.deleteCorps(id);
	}

	public int updateCorps(Corps corps) {
		return corpsDao.updateCorps(corps);
	}

	public List<Corps> listCorps() {
		return corpsDao.listCorps();
	}

}
