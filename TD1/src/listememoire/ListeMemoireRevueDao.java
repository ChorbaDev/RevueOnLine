package listememoire;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RevueDao;
import javafx.scene.image.Image;
import modele.metier.Revue;

public class ListeMemoireRevueDao implements RevueDao {
	private static ListeMemoireRevueDao instance;
	private List<Revue> donnees;

	private ListeMemoireRevueDao() {
		this.donnees = new ArrayList<>();
		File file=new File("TD1/src/vue/images/empty.jpg");
		this.donnees.add(new Revue(1, "titre", "descp", 1.2,new Image(file.getAbsolutePath()), 1));
	}

	public static ListeMemoireRevueDao getInstance() {
		if (instance == null)
			instance = new ListeMemoireRevueDao();
		return instance;
	}

	@Override
	public boolean create(Revue objet) throws SQLException {
		objet.setId(2);
		while (this.donnees.contains(objet)) {
			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		return ok;
		/*while (existanceID(objet) >= 0) {
			objet.setId(objet.getId() + 1);
		}
		return this.donnees.add(objet);*/
	}

	private int existanceID(Revue objet) {
		for (Revue revue : donnees) {
			if (objet.equals(revue))
				return donnees.indexOf(objet);
		}
		return -1;
	}

	@Override
	public boolean update(Revue objet) throws SQLException {
		int index = existanceID(objet);
		if (index > -1) {
			this.donnees.set(index, objet);
			return true;
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public boolean delete(Revue objet) throws SQLException {
		int index = existanceID(objet);
		if (index > -1) {
			return this.donnees.remove(objet);
		}
		throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
	}

	@Override
	public Revue getById(int id) throws SQLException {
		Revue objet = new Revue(id, "test", "test", 1.0, 1);
		int index = existanceID(objet);
		if (index > -1) {
			return this.donnees.get(index);
		}
		throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");

	}

	@Override
	public ArrayList<Revue> getByTitre(String titre) throws SQLException {
		ArrayList<Revue> liste = new ArrayList<>();
		for (Revue revue : donnees) {
			if (revue.getTitre() == titre)
				liste.add(revue);
		}
		return liste;
	}

	@Override
	public ArrayList<Revue> findAll() throws SQLException {
		return (ArrayList<Revue>) this.donnees;
	}

}
