package listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.AbonnementDAO;
import metier.Abonnement;

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
	public boolean create(Abonnement objet) {
		while (existanceID(objet) >= 0) {
			objet.setId(objet.getId() + 1);
		}
		return this.donnees.add(objet);
	}

	private int existanceID(Abonnement objet) {
		for (Abonnement ab : donnees) {
			if (objet.getId() == ab.getId())
				return donnees.indexOf(objet);
		}
		return -1;
	}

	@Override
	public boolean update(Abonnement objet) {
		int index = existanceID(objet);
		if (index > -1) {
			this.donnees.set(index, objet);
			return true;
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public boolean delete(Abonnement objet) {
		int index = existanceID(objet);
		if (index > -1) {
			return this.donnees.remove(objet);
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public Abonnement getById(int id) {
		Abonnement objet = new Abonnement(id, "01/01/2001", "01/01/2002", 1, 1);
		int index = existanceID(objet);
		if (index > -1) {
			return this.donnees.get(index);
		}
		throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");

	}

	@Override
	public ArrayList<Abonnement> getByClient(int cl) {
		ArrayList<Abonnement> liste = new ArrayList<>();
		for (Abonnement ab : donnees) {
			if (ab.getId_client() == cl)
				liste.add(ab);
		}
		return liste;
	}

	@Override
	public ArrayList<Abonnement> findAll() {
		return (ArrayList<Abonnement>) this.donnees;
	}
}