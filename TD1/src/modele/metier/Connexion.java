package modele.metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connexion instance;
    private String url, login, pwd;
    private Connection maConnexion;
    private Connexion() throws ClassNotFoundException {
        this.creeConnexion();
    }
    public static Connexion getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }
    public Connection creeConnexion() throws ClassNotFoundException {
        url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/elloumi2u_base";
		login = "elloumi2u_appli";
		pwd = "32024561";
/*        String url =
                "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/ghoniem1u_bdRvOnl";
        String
                login = "ghoniem1u_appli";
        String pwd = "bonjour";*/
        maConnexion = null;
        try {
            if (maConnexion == null || maConnexion.isClosed()){
                maConnexion = DriverManager.getConnection(url, login, pwd);
            }

        } catch (SQLException sqle) {
            System.out.println("Erreur connexion :" + sqle.getMessage());
        }
        return maConnexion;
    }
}
