package ListeMemoire;

import java.util.ArrayList;
import java.util.List;

import DAO.PeriodiciteDAO;
import Metier.Periodicite;

public class ListeMemoirePeriodiciteDAO implements PeriodiciteDAO {
	private static ListeMemoirePeriodiciteDAO instance;
	private List<Periodicite> donnees;

	private ListeMemoirePeriodiciteDAO() {
		this.donnees = new ArrayList<>();
	}

	public static ListeMemoirePeriodiciteDAO getInstance() {
		if (instance == null)
			instance = new ListeMemoirePeriodiciteDAO();
		return instance;
	}

	private int existanceID(Periodicite objet) {
		for (Periodicite p : donnees) {
			if (objet.getCle() == p.getCle())
				return donnees.indexOf(objet);
		}
		return -1;
	}

	@Override
	public Periodicite getById(int id) {
		Periodicite objet = new Periodicite(id, "test");
		int index = existanceID(objet);
		if (index > -1) {
			return this.donnees.get(index);
		}
		throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");

	}

	@Override
	public boolean create(Periodicite objet) {
		while (existanceID(objet) >= 0) {
			objet.setCle(objet.getCle() + 1);
		}
		return this.donnees.add(objet);
	}

	@Override
	public boolean update(Periodicite objet) {
		int index = existanceID(objet);
		if (index > -1) {
			this.donnees.set(index, objet);
			return true;
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public boolean delete(Periodicite objet) {
		int index = existanceID(objet);
		if (index > -1) {
			return this.donnees.remove(objet);
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public ArrayList<Periodicite> getByLibelle(String libelle) {
		ArrayList<Periodicite> listePer = new ArrayList<>();
		for (Periodicite p : donnees) {
			if (p.getLibelle().equals(libelle))
				listePer.add(p);
		}
		return listePer;
	}

	@Override
	public ArrayList<Periodicite> findAll() {
		return (ArrayList<Periodicite>) this.donnees;
	}
}
