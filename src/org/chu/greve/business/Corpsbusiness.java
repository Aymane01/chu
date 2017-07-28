package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Corps;

public interface Corpsbusiness {
	public int createCorps(Corps corps);

	public int deleteCorps(int id);

	public int updateCorps(Corps corps);

	public List<Corps> listCorps();
}
