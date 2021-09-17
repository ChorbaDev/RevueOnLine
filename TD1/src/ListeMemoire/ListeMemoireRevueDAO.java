package ListeMemoire;

import DAO.RevueDAO;
import Metier.*;
import java.sql.*;
import java.util.*;

public class ListeMemoireRevueDAO implements RevueDAO{
private ListeMemoireRevueDAO instance;
private List<Revue> donnees;
private ListeMemoireRevueDAO() {
	this.donnees=new ArrayList<>();
}
	@Override
	public Revue getById(int id) throws SQLException {
		int idx = this.donnees.indexOf(new Revue(id,"test","test",1.0,"test",1));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public boolean create(Revue objet) throws SQLException {
		objet.setId(1);
		while (this.donnees.contains(objet)) {
			objet.setId(objet.getId() + 1);
		}
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
		for(int i=1;i<donnees.size();i++) {
			Revue r=donnees.get(i);
			if(r.getTitre()==titre)
				liste.add(r);
			}
		return liste;
	}
	
}
