package ListeMemoire;

import DAO.RevueDAO;
import Metier.*;
import MySQL.MySQLRevueDAO;

import java.sql.*;
import java.util.*;

public class ListeMemoireRevueDAO implements RevueDAO{
private static ListeMemoireRevueDAO instance;
private List<Revue> donnees;
private ListeMemoireRevueDAO() {
	this.donnees=new ArrayList<>();
}
public static ListeMemoireRevueDAO getInstance() {
	if(instance==null)
		instance=new ListeMemoireRevueDAO();
	return instance;
}
	@Override
	public Revue getById(int id) throws SQLException {
		for(Revue r: donnees) {
			if(r.getId()==id)
				return r;
		}
		throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");
	}

	@Override
	public boolean create(Revue objet) throws SQLException {
		objet.setId(donnees.size()+1);
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	@Override
	public boolean update(Revue objet) throws SQLException {
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(Revue objet) throws SQLException {
		Revue supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public ArrayList<Revue> getByTitre(String titre) throws SQLException {
		ArrayList<Revue> liste=new ArrayList<>();
		for(Revue r: donnees) {
			if(r.getTitre()==titre)
				liste.add(r);
			}
		return liste;
	}
	
}
