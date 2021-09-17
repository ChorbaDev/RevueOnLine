package ListeMemoire;

import DAO.AbonnementDAO;
import Metier.*;
import java.sql.*;
import java.util.*;
import java.time.*;

public class ListeMemoireAbonnementDAO implements AbonnementDAO{
	private static ListeMemoireAbonnementDAO instance;
	private List<Abonnement> donnees;
	private ListeMemoireAbonnementDAO() {
		donnees=new ArrayList<>();
	}
	public static ListeMemoireAbonnementDAO getInstance() {
		if(instance==null)
			instance=new ListeMemoireAbonnementDAO();
			return instance;
	}
	@Override
	public boolean create(Abonnement objet) throws SQLException {
		objet.setId(1);
		while (this.donnees.contains(objet)) {
			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}
	@Override
	public boolean update(Abonnement objet) throws SQLException {
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}
	@Override
	public boolean delete(Abonnement objet) throws SQLException {
		Abonnement supprime;
		
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}
	@Override
	public Abonnement getById(int id) throws SQLException {
		for(Abonnement ab: donnees) {
			if(ab.getId()==id)
				return ab;
		}
		throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");
	}
	@Override
	public ArrayList<Abonnement> getByClient(Client cl) throws SQLException {
		ArrayList<Abonnement> liste=new ArrayList<>();
		for(Abonnement ab: donnees) {
			if(ab.getId_client()==cl.getCle())
				liste.add(ab);
			}
		return liste;
	}
}
