package ListeMemoire;

import DAO.PeriodiciteDAO;
import Metier.Periodicite;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListeMemoirePeriodiciteDAO implements PeriodiciteDAO {
    @Override
    public Periodicite getById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean create(Periodicite object) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Periodicite object) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Periodicite object) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Periodicite> getByLibelle(String libelle) throws SQLException {
        return null;
    }
}
