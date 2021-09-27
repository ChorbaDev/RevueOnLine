package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import metier.Periodicite;

public interface PeriodiciteDAO extends DAO<Periodicite> {
	public ArrayList<Periodicite> getByLibelle(String libelle) throws SQLException;

	public ArrayList<Periodicite> findAll() throws SQLException;
}