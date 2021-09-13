package DAO.MySQL;

import DAO.ClientDAO;
import Metier.Client;

import java.util.ArrayList;

public class MySQLClientDAO implements ClientDAO {
    @Override
    public ArrayList<Client> getByNomPrenom(String nom, String prenom) {
        return null;
    }

    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public boolean create(Client object) {
        return false;
    }

    @Override
    public boolean update(Client object) {
        return false;
    }

    @Override
    public boolean delete(Client object) {
        return false;
    }
}
