package DAO;
import Metier.Client;

import java.util.ArrayList;


public interface ClientDAO extends DAO<Client> {
    public ArrayList<Client> getByNomPrenom(String nom, String prenom);
}
