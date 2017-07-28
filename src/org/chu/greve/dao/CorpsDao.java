package org.chu.greve.dao;

import java.util.List;

 import org.chu.greve.models.Corps;

public interface CorpsDao {

	public int insertCorps(Corps corps);

	public int deleteCorps(int id);

	public int updateCorps(Corps corps);

	public List<Corps> listCorps();
}
