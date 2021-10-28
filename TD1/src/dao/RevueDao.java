package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.metier.Revue;

public interface RevueDao extends Dao<Revue> {
    ArrayList<Revue> getByTitre(String titre) throws SQLException, IOException, ClassNotFoundException;

    ArrayList<Revue> findAll() throws SQLException, IOException, ClassNotFoundException;
}
