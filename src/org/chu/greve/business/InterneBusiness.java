package org.chu.greve.business;

import java.util.List;

import org.chu.greve.models.Interne;
import org.chu.greve.models.Specialite;

public interface InterneBusiness {
	public int addInterne(Interne interne);
	public Interne selectInterne(String CNE);
	public List<Interne> selectAllInterne();
	public int modifyInterne(Interne interne);
	public int deleteInterne(String CNE);
}
