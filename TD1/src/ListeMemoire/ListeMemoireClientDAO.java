package ListeMemoire;

import java.util.ArrayList;
import java.util.List;

import DAO.ClientDAO;
import Metier.Client;

public class ListeMemoireClientDAO implements ClientDAO {
	private static ListeMemoireClientDAO instance;
	private List<Client> donnees;

	private ListeMemoireClientDAO() {
		this.donnees = new ArrayList<>();
	}

	public static ListeMemoireClientDAO getInstance() {
		if (instance == null)
			instance = new ListeMemoireClientDAO();
		return instance;
	}

	private int existanceID(Client objet) {
		for (Client cl : donnees) {
			if (objet.getCle() == cl.getCle())
				return donnees.indexOf(objet);
		}
		return -1;
	}

	@Override
	public boolean create(Client objet) {
		while (existanceID(objet) >= 0) {
			objet.setCle(objet.getCle() + 1);
		}
		return this.donnees.add(objet);
	}

	@Override
	public boolean update(Client objet) {
		int index = existanceID(objet);
		if (index > -1) {
			this.donnees.set(index, objet);
			return true;
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public boolean delete(Client objet) {
		int index = existanceID(objet);
		if (index > -1) {
			return this.donnees.remove(objet);
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public Client getById(int id) {
		Client objet = new Client(id, "test", "test", "test", "test", "test", "test", "test");
		int index = existanceID(objet);
		if (index > -1) {
			return this.donnees.get(index);
		}
		throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");

	}

	@Override
	public ArrayList<Client> getByNomPrenom(String nom, String prenom) {
		ArrayList<Client> listeClient = new ArrayList<>();
		for (Client c : donnees) {
			if (c.getNom() == nom && c.getPrenom() == prenom)
				listeClient.add(c);
		}
		return listeClient;
	}

	@Override
	public ArrayList<Client> findAll() {
		return (ArrayList<Client>) this.donnees;
	}
}
