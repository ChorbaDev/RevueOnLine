package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import metier.Revue;

public interface RevueDao extends Dao<Revue> {
	public ArrayList<Revue> getByTitre(String titre) throws SQLException;

	public ArrayList<Revue> findAll() throws SQLException;
}
