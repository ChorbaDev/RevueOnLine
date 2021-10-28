package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.metier.Periodicite;

public interface PeriodiciteDao extends Dao<Periodicite> {
    public ArrayList<Periodicite> getByLibelle(String libelle) throws SQLException, ClassNotFoundException;

    public ArrayList<Periodicite> findAll() throws SQLException, ClassNotFoundException;
}
