package MySQL;


import java.util.ArrayList;

import DAO.RevueDAO;
import Metier.*;
import java.sql.*;

public class MySQLRevueDAO implements RevueDAO{
private MySQLRevueDAO instance;
private MySQLRevueDAO() {}
public MySQLRevueDAO getInstance() {
	if(instance==null)
		instance=new MySQLRevueDAO();
	return instance;
}

	@Override
	public Revue getById(int id) {
		Connexion maConnexion=new Connexion();
		Connection connect=maConnexion.creeConnexion();
		String sql="select titre,description,tarif_numero,visuel,id_periodicite from Revue where id_revue=?";
		PreparedStatement req=connect.prepareStatement(sql);
		return null;
	}

	@Override
	public boolean create(Revue object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Revue object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Revue object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Revue> getByTitre(String titre) {
		// TODO Auto-generated method stub
		return null;
	}

}
