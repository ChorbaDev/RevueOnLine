package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.metier.Periodicite;

public interface PeriodiciteDao extends Dao<Periodicite> {
    /**
     * @param libelle libellé qu'on cherche à trouver dans la persistance
     * @return Arraylist avec la périodicité ayant le même libellé
     */
    public ArrayList<Periodicite> getByLibelle(String libelle) throws SQLException;

    /**
     * @return arraylist périodicité contenant l'ensemble des périodicités de la solution de persistance
     */
    public ArrayList<Periodicite> findAll() throws SQLException;
}
