package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.metier.Revue;

public interface RevueDao extends Dao<Revue> {
    /**
     * @param titre titre de la revue recherché
     * @return un arraylist contenant la revue avec titre identique à celui recherché
     */
    public ArrayList<Revue> getByTitre(String titre) throws SQLException, IOException;

    /**
     * @return arraylist Revue contenant l'ensemble des Revues de la solution de persistance
     */
    public ArrayList<Revue> findAll() throws SQLException, IOException;
}
