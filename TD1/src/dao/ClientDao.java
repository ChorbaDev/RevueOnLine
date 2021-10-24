package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.metier.Client;

public interface ClientDao extends Dao<Client> {
	public ArrayList<Client> getByNomPrenom(String nom, String prenom) throws SQLException, ClassNotFoundException;

	public ArrayList<Client> findAll() throws SQLException, ClassNotFoundException;
}
