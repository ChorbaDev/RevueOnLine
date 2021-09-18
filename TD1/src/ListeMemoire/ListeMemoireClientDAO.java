package ListeMemoire;

import DAO.ClientDAO;
import Metier.Client;
import Metier.Periodicite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<Client> getByNomPrenom(String nom, String prenom) throws SQLException {
        ArrayList<Client> listeClient = new ArrayList<>();
        for (Client c : donnees) {
            if (c.getNom() == nom && c.getPrenom() == prenom)
                listeClient.add(c);
        }
        return listeClient;
    }

    @Override
    public Client getById(int id) throws SQLException {
        for (Client c : donnees) {
            if (c.getCle() == id)
                return c;
        }
        throw new IllegalArgumentException("Aucun objet de ce type ne possède cet identifiant");
    }

    @Override
    public boolean create(Client object) throws SQLException {
        object.setCle(donnees.size() + 1);
        boolean validAjout = this.donnees.add(object);
        return validAjout;
    }

    @Override
    public boolean update(Client object) throws SQLException {
        int idx = this.donnees.indexOf(object);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");

        } else {
            this.donnees.set(idx, object);
        }
        return true;
    }

    @Override
    public boolean delete(Client object) throws SQLException {
        Client supprime;
        int idx = this.donnees.indexOf(object);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }
        return object.equals(supprime);
    }
}
