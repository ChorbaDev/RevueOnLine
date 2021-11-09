package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ClientDao;
import modele.metier.Adresse;
import modele.metier.Client;
import modele.metier.Connexion;

public class MySqlClientDao implements ClientDao {
    private static MySqlClientDao instance;
    private Connection connect;

    public MySqlClientDao() {
        Connexion maConnexion = Connexion.getInstance();
        connect = maConnexion.creeConnexion();
    }

    public static MySqlClientDao getInstance() {
        if (instance == null)
            instance = new MySqlClientDao();
        return instance;
    }

    @Override
    public ArrayList<Client> getByNomPrenom(String nom, String prenom) throws SQLException {
        ArrayList<Client> lClient = new ArrayList<>();
        PreparedStatement req = connect.prepareStatement("select * from Client where nom=? and prenom=?");
        req.setString(1, nom);
        req.setString(2, prenom);
        ResultSet res = req.executeQuery();

        while (res.next()) {
            Adresse adresse = new Adresse(res.getString(4), res.getString(5), res.getString(6), res.getString(7),
                    res.getString(8));
            lClient.add(new Client(res.getInt(1), res.getString(2), res.getString(3), adresse));
        }
        if (res != null)
            res.close();
        if (req != null)
            req.close();

        return lClient;
    }

    @Override
    public Client getById(int id) throws SQLException {
        PreparedStatement req = connect.prepareStatement("select * from Client where id_client=?");
        req.setInt(1, id);
        ResultSet res = req.executeQuery();
        Client client = null;
        if (res.next()) {
            Adresse adresse = new Adresse(res.getString(4), res.getString(5), res.getString(6), res.getString(7),
                    res.getString(8));
            client = new Client(res.getInt(1), res.getString(2), res.getString(3), adresse);
        }

        if (res != null)
            res.close();
        if (req != null)
            req.close();

        return client;
    }

    @Override
    public boolean create(Client object) throws SQLException {
        PreparedStatement req = connect.prepareStatement(
                "insert into Client(nom,prenom,no_rue,voie,code_postal,ville,pays) values (?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

        req.setString(1, object.getNom());
        req.setString(2, object.getPrenom());
        req.setString(3, object.getAdresse().getNo_rue());
        req.setString(4, object.getAdresse().getVoie());
        req.setString(5, object.getAdresse().getCode_postal());
        req.setString(6, object.getAdresse().getVille());
        req.setString(7, object.getAdresse().getPays());
        int nbLignes = req.executeUpdate();
        ResultSet res = req.getGeneratedKeys();
        if (res.next()) {
            object.setCle(res.getInt(1));
        }

        if (res != null)
            res.close();
        if (req != null)
            req.close();

        return nbLignes == 1;
    }

    @Override
    public boolean update(Client object) throws SQLException {
        PreparedStatement req = connect.prepareStatement(
                "update Client set nom=?, prenom=?,no_rue=?,voie=?,code_postal=?,ville=?,pays=? where id_client=?");
        req.setString(1, object.getNom());
        req.setString(2, object.getPrenom());
        req.setString(3, object.getAdresse().getNo_rue());
        req.setString(4, object.getAdresse().getVoie());
        req.setString(5, object.getAdresse().getCode_postal());
        req.setString(6, object.getAdresse().getVille());
        req.setString(7, object.getAdresse().getPays());
        req.setInt(8, object.getCle());
        int nbLignes = req.executeUpdate();
        return nbLignes == 1;
    }

    @Override
    public boolean delete(Client object) throws SQLException {
        PreparedStatement req = connect.prepareStatement("delete from Client where id_client=?");
        req.setInt(1, object.getCle());
        int nbLignes = req.executeUpdate();
        if (req != null)
            req.close();

        return nbLignes == 1;
    }

    @Override
    public ArrayList<Client> findAll() throws SQLException {
        ArrayList<Client> lClient = new ArrayList<>();
        PreparedStatement req = connect.prepareStatement("select * from Client");

        ResultSet res = req.executeQuery();

        while (res.next()) {
            Adresse adresse = new Adresse(res.getString(4), res.getString(5), res.getString(6), res.getString(7),
                    res.getString(8));
            lClient.add(new Client(res.getInt(1), res.getString(2), res.getString(3), adresse));
        }
        if (res != null)
            res.close();
        if (req != null)
            req.close();

        return lClient;
    }
}
