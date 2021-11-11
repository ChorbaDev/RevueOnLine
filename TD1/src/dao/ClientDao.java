package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.metier.Client;

public interface ClientDao extends Dao<Client> {
    /**
     * @param nom nom du client
     * @param prenom prénom du client
     * @return un arraylist contenant le client avec un nom et prénom identique à celui recherché
     */
    public ArrayList<Client> getByNomPrenom(String nom, String prenom) throws SQLException;

    /**
     * @return arraylist client contenant l'ensemble des clients de la solution de persistance
     */
    public ArrayList<Client> findAll() throws SQLException;
}
