package mysql;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.RevueDao;
import javafx.scene.image.Image;
import modele.metier.Connexion;
import modele.metier.Revue;

public class MySqlRevueDao implements RevueDao {
    private static MySqlRevueDao instance;

    private MySqlRevueDao() {
    }

    public static MySqlRevueDao getInstance() {
        if (instance == null)
            instance = new MySqlRevueDao();
        return instance;
    }

    public Image setImg(InputStream index) throws SQLException, IOException {
        InputStream is = index;
        OutputStream os = new FileOutputStream(new File("photo.jpg"));
        byte[] content = new byte[1024];

        int size = 0;
        while ((size = is.read(content)) != -1) {
            os.write(content, 0, size);
        }
        os.close();
        is.close();
        Image img = new Image("file:photo.jpg", 400, 300, true, true);
        return img;
    }

    @Override
    public Revue getById(int id) throws SQLException, IOException, ClassNotFoundException {
        Connexion maConnexion = Connexion.getInstance();
        Connection connect = maConnexion.creeConnexion();
        String sql = "select titre,description,tarif_numero,visuel,id_periodicite from Revue where id_revue=?";
        PreparedStatement req = connect.prepareStatement(sql);
        req.setInt(1, id);
        ResultSet res = req.executeQuery();
        Revue revue = null;
        if (res.next()) {
            Image img = setImg(res.getBinaryStream(4));
            revue = new Revue(id, res.getString(1), res.getString(2), res.getDouble(3), img,
                    res.getInt(5));
        }


        if (res != null)
            res.close();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();

        return revue;

    }

    @Override
    public boolean create(Revue r) throws SQLException, IOException, ClassNotFoundException {
        Connexion maConnexion = Connexion.getInstance();
        Connection connect = maConnexion.creeConnexion();

        String sql = "insert into Revue(titre,description,tarif_numero,visuel,id_periodicite) values (?,?,?,?,?)";
        PreparedStatement req = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        req.setString(1, r.getTitre());
        req.setString(2, r.getDescription());
        req.setDouble(3, r.getTarif_numero());
        req.setBlob(4, r.getVisuel());
        req.setInt(5, r.getId_p());

        int nbLignes = req.executeUpdate();
        ResultSet res = req.getGeneratedKeys();

        if (res.next())
            r.setId(res.getInt(1));
        if (res != null)
            res.close();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();

        return nbLignes == 1;
    }

    @Override
    public boolean update(Revue r) throws SQLException, IOException, ClassNotFoundException {
        Connexion maConnexion = Connexion.getInstance();
        Connection connect = maConnexion.creeConnexion();
        String sql = "update Revue set titre=? , description=? , tarif_numero=? , visuel=? , id_periodicite=? where id_revue=?";
        PreparedStatement req = connect.prepareStatement(sql);
        req.setString(1, r.getTitre());
        req.setString(2, r.getDescription());
        req.setDouble(3, r.getTarif_numero());
        req.setBinaryStream(4, r.getVisuel());
        req.setInt(5, r.getId_p());
        req.setInt(6, r.getId());
        int nbLignes = req.executeUpdate();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();

        return nbLignes == 1;
    }

    @Override
    public boolean delete(Revue r) throws SQLException, ClassNotFoundException {
        Connexion maConnexion = Connexion.getInstance();
        Connection connect = maConnexion.creeConnexion();
        String sql = "delete from Revue where id_revue=?";
        PreparedStatement req = connect.prepareStatement(sql);
        req.setInt(1, r.getId());
        int nbLignes = req.executeUpdate();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();

        return nbLignes == 1;
    }

    @Override
    public ArrayList<Revue> getByTitre(String titre) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Revue> list = new ArrayList<>();

        Connexion maConnexion = Connexion.getInstance();
        Connection connect = maConnexion.creeConnexion();
        String sql = "select * from Revue where titre=?";
        PreparedStatement req = connect.prepareStatement(sql);
        req.setString(1, titre);
        ResultSet res = req.executeQuery();

        while (res.next()) {
            Image img = setImg(res.getBinaryStream(5));
            list.add(new Revue(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), img,
                    res.getInt(6)));
        }

        if (res != null)
            res.close();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();

        return list;
    }

    @Override
    public ArrayList<Revue> findAll() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Revue> list = new ArrayList<>();

        Connexion maConnexion = Connexion.getInstance();
        Connection connect = maConnexion.creeConnexion();
        String sql = "select * from Revue";
        PreparedStatement req = connect.prepareStatement(sql);
        ResultSet res = req.executeQuery();

        while (res.next()) {
            Image img = setImg(res.getBinaryStream(5));
            list.add(new Revue(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), img,
                    res.getInt(6)));
        }

        if (res != null)
            res.close();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();

        return list;
    }

}
