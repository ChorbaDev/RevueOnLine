package DAO.MySQL;

import DAO.ClientDAO;
import Metier.Client;
import Metier.Connexion;

import java.sql.*;
import java.util.ArrayList;

public class MySQLClientDAO implements ClientDAO {
    @Override
    public ArrayList<Client> getByNomPrenom(String nom, String prenom) throws SQLException {
        ArrayList<Client> lClient= new ArrayList<>();
        Connexion maConnexion = new Connexion();
        Connection connect = maConnexion.creeConnexion();
        PreparedStatement req = connect.prepareStatement("select * from Client where nom=? and prenom=?");
        req.setString(1, nom);
        req.setString(2, prenom);

        return null;
    }

    @Override
    public Client getById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean create(Client object) throws SQLException {
        Connexion maConnexion = new Connexion();
        Connection connect = maConnexion.creeConnexion();
        PreparedStatement req = connect.prepareStatement("insert into Client values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        req.setString(1, object.getNom());
        req.setString(2, object.getPrenom());
        req.setString(3, object.getNo_rue());
        req.setString(4, object.getVoie());
        req.setString(5, object.getCode_postal());
        req.setString(6, object.getVille());
        req.setString(7, object.getPays());
        int nbLignes = req.executeUpdate();
        ResultSet res = req.getGeneratedKeys();
        if (res.next()) {
            object.setCle(res.getInt(1));
        }

        if (res != null)
            res.close();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();

        return nbLignes == 1;
    }

    @Override
    public boolean update(Client object) throws SQLException {
        Connexion maConnexion = new Connexion();
        Connection connect = maConnexion.creeConnexion();
        PreparedStatement req = connect.prepareStatement("update Metier.Client set nom=?, prenom=?,no_rue=?,voie=?,code_postal=?,ville=?,pays=? where id_client=?");
        req.setString(1, object.getNom());
        req.setString(2, object.getPrenom());
        req.setString(3, object.getNo_rue());
        req.setString(4, object.getVoie());
        req.setString(5, object.getCode_postal());
        req.setString(6, object.getVille());
        req.setString(7, object.getPays());
        req.setInt(8, object.getCle());
        int nbLignes = req.executeUpdate();
        return nbLignes == 1;
    }

    @Override
    public boolean delete(Client object) throws SQLException {
        Connexion maConnexion = new Connexion();
        Connection connect = maConnexion.creeConnexion();
        PreparedStatement req = connect.prepareStatement("delete from Metier.Client where id_client=?");
        req.setInt(1, object.getCle());
        int nbLignes = req.executeUpdate();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();

        return nbLignes == 1;
    }
}
