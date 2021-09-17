package ListeMemoire;

import DAO.ClientDAO;
import Metier.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListeMemoireClientDAO implements ClientDAO {
    @Override
    public ArrayList<Client> getByNomPrenom(String nom, String prenom) throws SQLException {
        return null;
    }

    @Override
    public Client getById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean create(Client object) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Client object) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Client object) throws SQLException {
        return false;
    }
}
