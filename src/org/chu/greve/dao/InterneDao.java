package org.chu.greve.dao;

import java.util.List;

import org.chu.greve.models.Interne;


public interface InterneDao {
	public Interne select(String CIN);
	public List<Interne> selectbyName(String nomFr);
	public List<Interne> selectAll();
	public int insert(Interne interne);
	public void delete(String CIN);
	public void modify(Interne interne);
}
