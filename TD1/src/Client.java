import java.sql.*;
import java.util.Scanner;

public class Client {
    private int cle = 0;
    private String nom;
    private String prenom;
    private String no_rue;
    private String voie;
    private String code_postal;
    private String ville;
    private String pays;
    private Connexion maConnexion;
    private Connection Connect;

    public Client(String nom, String prenom, String no_rue, String voie, String code_postal, String ville, String pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.no_rue = no_rue;
        this.voie = voie;
        this.code_postal = code_postal;
        this.ville = ville;
        this.pays = pays;
        maConnexion = new Connexion();
        Connect = maConnexion.creeConnexion();
    }

    public void addClient() throws SQLException {
        PreparedStatement req = Connect.prepareStatement("insert into Client(nom,prenom,no_rue,voie,code_postal,ville,pays) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        req.setString(1, this.nom);
        req.setString(2, this.prenom);
        req.setString(3, this.no_rue);
        req.setString(4, this.voie);
        req.setString(5, this.code_postal);
        req.setString(6, this.ville);
        req.setString(7, this.pays);
        int nbLignes = req.executeUpdate();
        ResultSet res = req.getGeneratedKeys();
        if (res.next()) {
            cle = res.getInt(1);
        }

    }

    public void deleteClient() throws SQLException {
        if (cle != 0) {
            PreparedStatement req = Connect.prepareStatement("delete from Client where id_client=?");
            req.setInt(1, cle);
            int nbLignes = req.executeUpdate();

        } else
            System.out.println("il n'existe aucun client avec une telle clé");

    }

    public void updateClient(String nom, String prenom, String no_rue, String voie, String code_postal, String ville, String pays) throws SQLException {
        if (cle != 0) {
            PreparedStatement req = Connect.prepareStatement("update Client set nom=?, prenom=?,no_rue=?,voie=?,code_postal=?,ville=?,pays=? where id_client=?");
            req.setString(1, nom);
            req.setString(2, prenom);
            req.setString(3, no_rue);
            req.setString(4, voie);
            req.setString(5, code_postal);
            req.setString(6, ville);
            req.setString(7, pays);
            req.setInt(8, cle);
            int nbLignes = req.executeUpdate();
        } else
            System.out.println("il n'existe aucun client avec une telle clé");

    }


}
