package listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.ClientDao;
import modele.metier.Adresse;
import modele.metier.Client;

public class ListeMemoireClientDao implements ClientDao {
    private static ListeMemoireClientDao instance;
    private List<Client> donnees;

    private ListeMemoireClientDao() {
        this.donnees = new ArrayList<>();
    }

    public static ListeMemoireClientDao getInstance() {
        if (instance == null)
            instance = new ListeMemoireClientDao();
        return instance;
    }

    private int existanceID(Client objet) {
        for (Client client : donnees) {
            if (objet.equals(client))
                return donnees.indexOf(objet);
        }
        return -1;
    }

    @Override
    public boolean create(Client objet) {
        objet.setCle(1);
        while (this.donnees.contains(objet)) {
            objet.setCle(objet.getCle() + 1);
        }
        boolean ok = this.donnees.add(objet);
        return ok;
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
        Adresse adresse = new Adresse("test", "test", "test", "test", "test");
        Client objet = new Client(id, "test", "test", adresse);
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
