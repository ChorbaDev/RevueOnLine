package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.metier.Abonnement;

public interface AbonnementDao extends Dao<Abonnement> {
    /**
     * @param id_c id client à rechercher
     * @return Arraylist abonnement dont l'id client est égal à celui recherché
     */
    public ArrayList<Abonnement> getByClient(int id_c) throws SQLException;

    /**
     * @return ArraylistAbonnement contenant l'ensemble des abonnement de la persistance
     */
    public ArrayList<Abonnement> findAll() throws SQLException;
}
