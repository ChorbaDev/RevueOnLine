package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.metier.Abonnement;

public interface AbonnementDao extends Dao<Abonnement> {
    ArrayList<Abonnement> getByClient(int id_c) throws SQLException, ClassNotFoundException;

    ArrayList<Abonnement> findAll() throws SQLException, ClassNotFoundException;
}
