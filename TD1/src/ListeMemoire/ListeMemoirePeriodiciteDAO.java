package ListeMemoire;

import DAO.PeriodiciteDAO;
import Metier.Periodicite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Periodicite getById(int id) throws SQLException {
        for (Periodicite p : donnees) {
            if (p.getCle() == id)
                return p;
        }
        throw new IllegalArgumentException("Aucun objet de ce type ne possède cet identifiant");
    }

    @Override
    public boolean create(Periodicite object) throws SQLException {
        object.setCle(donnees.size() + 1);
        boolean validAjout = this.donnees.add(object);
        return validAjout;
    }

    @Override
    public boolean update(Periodicite object) throws SQLException {
        int idx = this.donnees.indexOf(object);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");

        } else {
            this.donnees.set(idx, object);
        }
        return true;
    }

    @Override
    public boolean delete(Periodicite object) throws SQLException {
        Periodicite supprime;
        int idx = this.donnees.indexOf(object);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }
        return object.equals(supprime);
    }

    @Override
    public ArrayList<Periodicite> getByLibelle(String libelle) throws SQLException {
        ArrayList<Periodicite> listePer = new ArrayList<>();
        for (Periodicite p : donnees) {
            if (p.getLibelle() == libelle)
                listePer.add(p);
        }
        return listePer;
    }
}
