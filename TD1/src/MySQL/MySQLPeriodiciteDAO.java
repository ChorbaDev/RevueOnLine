package MySQL;

import DAO.PeriodiciteDAO;
import Metier.*;


import java.sql.*;
import java.util.ArrayList;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO {
    @Override
    public Periodicite getById(int id) throws SQLException {
        Connexion maConnexion = new Connexion();
        Connection connect = maConnexion.creeConnexion();
        PreparedStatement req = connect.prepareStatement("select * from Periodicite where id_periodicite=?");
        req.setInt(1, id);
        ResultSet res=req.executeQuery();
        Periodicite periodicite=null;
        if(res.next())
            periodicite=new Periodicite(id, res.getString(2));
        if (res != null)
            res.close();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();
        return periodicite;
    }

    @Override
    public boolean create(Periodicite object) throws SQLException {
        Connexion maConnexion = new Connexion();
        Connection connect = maConnexion.creeConnexion();
        String sql = "insert into Periodicite(libelle) values (?)";
        PreparedStatement req = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        req.setString(1, object.getLibelle());
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
    public boolean update(Periodicite object) throws SQLException {
        Connexion maConnexion = new Connexion();
        Connection connect = maConnexion.creeConnexion();
        String sql = "update Periodicite set libelle=? where libelle=?";
        PreparedStatement req = connect.prepareStatement(sql);
        req.setString(1, object.getLibelle());
        req.setInt(2, object.getCle());
        int nbLignes = req.executeUpdate();
        return nbLignes == 1;
    }

    @Override
    public boolean delete(Periodicite object) throws SQLException {
        Connexion maConnexion = new Connexion();
        Connection connect = maConnexion.creeConnexion();
        PreparedStatement req = connect.prepareStatement("delete from Periodicite where id_periodicite=?");
        req.setInt(1, object.getCle());
        int nbLignes = req.executeUpdate();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();

        return nbLignes == 1;
    }

    @Override
    public ArrayList<Periodicite> getByLibelle(String libelle) throws SQLException {
        ArrayList<Periodicite> lPeriodicite= new ArrayList<>();
        Connexion maConnexion = new Connexion();
        Connection connect = maConnexion.creeConnexion();
        PreparedStatement req = connect.prepareStatement("select * from Periodicite where libelle=?");
        req.setString(1, libelle);
        ResultSet res=req.executeQuery();

        while(res.next()){
            lPeriodicite.add(new Periodicite(res.getInt(1),res.getString(2)));
        }
        if (res != null)
            res.close();
        if (req != null)
            req.close();
        if (connect != null)
            connect.close();
        return lPeriodicite;
    }
}