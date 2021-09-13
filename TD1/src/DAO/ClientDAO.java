package DAO;
import Metier.Client;

public interface ClientDAO extends DAO<Client> {
    public ArrayList<Client> getByNomPrenom(String nom, String prenom);
}
