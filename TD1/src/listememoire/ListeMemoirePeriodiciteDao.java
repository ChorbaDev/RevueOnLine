package listememoire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PeriodiciteDao;
import modele.metier.Periodicite;

public class ListeMemoirePeriodiciteDao implements PeriodiciteDao {
    private static ListeMemoirePeriodiciteDao instance;
    private List<Periodicite> donnees;

    private ListeMemoirePeriodiciteDao() {
        this.donnees = new ArrayList<>();
        this.donnees.add(new Periodicite(1, "Mensuel"));
        this.donnees.add(new Periodicite(2, "Quotidien"));
    }

    public static ListeMemoirePeriodiciteDao getInstance() {
        if (instance == null)
            instance = new ListeMemoirePeriodiciteDao();
        return instance;
    }

    private int existanceID(Periodicite objet) {
        for (Periodicite p : donnees) {
            if (objet.equals(p))
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
        objet.setCle(1);
        while (this.donnees.contains(objet)) {
            objet.setCle(objet.getCle() + 1);
        }
        boolean ok = this.donnees.add(objet);
        return ok;
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
    public boolean delete(Periodicite objet) throws SQLException {
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
