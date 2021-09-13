import java.sql.*;

public class Abonnement {
    private int id;
    private java.sql.Date date_debut;
    private java.sql.Date date_fin;
    int id_client;
    int id_revue;

    private Connexion maConnexion;
    private Connection connect;

    public Abonnement(String deb, String fin, int id_c, int id_r) {
        java.util.Date dateD = new java.util.Date(deb);
        java.util.Date dateF = new java.util.Date(fin);
        date_debut = new java.sql.Date(dateD.getTime());
        date_fin = new java.sql.Date(dateF.getTime());
        id_client = id_c;
        id_revue = id_r;

        maConnexion = new Connexion();
        connect = maConnexion.creeConnexion();
    }

    public void addAbonnement() throws SQLException {
        String sql = "insert into Abonnement(date_debut,date_fin,id_client,id_revue) values (?,?,?,?)";
        PreparedStatement requete = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        requete.setDate(1, date_debut);
        requete.setDate(2, date_fin);
        requete.setInt(3, id_client);
        requete.setInt(4, id_revue);
        int nbLignes = requete.executeUpdate();
        ResultSet res = requete.getGeneratedKeys();
        if (res.next()) {
            id = res.getInt(1);
        }
    }

    public void deleteAbonnement() throws SQLException {
        if (id != 0) {
            String sql = "delete from Abonnement where id_abonnement=?";
            PreparedStatement requete = connect.prepareStatement(sql);
            requete.setInt(1, id);
            int nbLignes = requete.executeUpdate();
        } else {
            System.out.println("Cette abonnement n'exite pas dans la base");
        }

    }

    public void updateAbonnement(String deb, String fin, int id_c, int id_r) throws SQLException {
        if (id != 0) {
            String sql = "update Abonnement set date_debut=?, date_fin=?, id_client=?, id_revue=? where id_abonnement=?";
            PreparedStatement requete = connect.prepareStatement(sql);
            java.util.Date dateD = new java.util.Date(deb);
            java.util.Date dateF = new java.util.Date(fin);
            //
            java.sql.Date date_d = new java.sql.Date(dateD.getTime());
            java.sql.Date date_f = new java.sql.Date(dateF.getTime());
            //
            requete.setDate(1, date_d);
            requete.setDate(2, date_f);
            requete.setInt(3, id_client);
            requete.setInt(4, id_revue);
            requete.setInt(5, id);
            int nbLignes = requete.executeUpdate();
        } else {
            System.out.println("Cette abonnement n'exite pas dans la base");
        }


    }
}
