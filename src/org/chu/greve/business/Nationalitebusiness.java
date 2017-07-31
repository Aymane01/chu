package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Nationalite;


public interface Nationalitebusiness {
	
	public int createNationalite(Nationalite nat);

	public int deleteNationalite(int id);

	public int updateNationalite(Nationalite nat);

	public List<Nationalite> listNationaliste();

}
