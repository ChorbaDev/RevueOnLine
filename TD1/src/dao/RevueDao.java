package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.metier.Revue;

public interface RevueDao extends Dao<Revue> {
    public ArrayList<Revue> getByTitre(String titre) throws SQLException, IOException;

    public ArrayList<Revue> findAll() throws SQLException, IOException;
}
