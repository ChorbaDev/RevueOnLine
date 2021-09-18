package DAO;

import Metier.Revue;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RevueDAO extends DAO<Revue> {
    public ArrayList<Revue> getByTitre(String titre) throws SQLException;
}
