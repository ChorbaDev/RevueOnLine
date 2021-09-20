package ListeMemoire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.AbonnementDAO;
import Metier.Abonnement;

public class ListeMemoireAbonnementDAO implements AbonnementDAO {
	private static ListeMemoireAbonnementDAO instance;
	private List<Abonnement> donnees;

	private ListeMemoireAbonnementDAO() {
		donnees = new ArrayList<>();
	}

	public static ListeMemoireAbonnementDAO getInstance() {
		if (instance == null)
			instance = new ListeMemoireAbonnementDAO();
		return instance;
	}

	@Override
	public boolean create(Abonnement objet) throws SQLException {
		objet.setId(donnees.size() + 1);
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	@Override
	public boolean update(Abonnement objet) throws SQLException {
		for (int i = 0; i < donnees.size(); i++) {
			if (objet.getId() == donnees.get(i).getId()) {
				this.donnees.set(i, objet);
				return true;
			}
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public boolean delete(Abonnement objet) throws SQLException {
		for (Abonnement ab : donnees) {
			if (objet.getId() == ab.getId())
				return this.donnees.remove(ab);
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public Abonnement getById(int id) throws SQLException {

		for (Abonnement ab : donnees) {
			if (ab.getId() == id)
				return ab;
		}
		throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");

	}

	@Override
	public ArrayList<Abonnement> getByClient(int cl) throws SQLException {
		ArrayList<Abonnement> liste = new ArrayList<>();
		for (Abonnement ab : donnees) {
			if (ab.getId_client() == cl)
				liste.add(ab);
		}
		return liste;
	}

	@Override
	public ArrayList<Abonnement> findAll() throws SQLException {
		return (ArrayList<Abonnement>) this.donnees;
	}
}
