package org.chu.greve.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Greve;
import org.chu.greve.models.Greviste;

public interface GreveBusiness {
	public int addGreve(Greve greve);

	public int updateGreve(Greve greve);

	public int deleteGreve(Greve greve);

	public List<Greve> listGreve();

	public int addGreviste(Greviste greviste);

	public int deleteGreviste(Greviste greviste);

	public List<Greviste> listGreviste(Greve greve);

	public List<Fonctionnaire> listFonctionnaire();

	public int deleteJours(Greviste greviste);

	public List<String> listJour(Greviste greviste);

	public int deletJour(Greviste greviste, String jour);

	public int updateJour(Greviste greviste, String jour, String nouveauJour);

	public int retenuSalaire(Greviste g);

}
