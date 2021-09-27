package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import metier.Client;

public interface ClientDAO extends DAO<Client> {
	public ArrayList<Client> getByNomPrenom(String nom, String prenom) throws SQLException;

	public ArrayList<Client> findAll() throws SQLException;
}
