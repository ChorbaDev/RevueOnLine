package modele.metier;

import controlleur.commun.CommunStaticMethods;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connexion instance;
    private String url, login, pwd;
    private Connection maConnexion;

    private Connexion()  {
        this.creeConnexion();
    }

    public static Connexion getInstance()  {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection creeConnexion()  {
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
                DriverManager.setLoginTimeout(2);
                maConnexion = DriverManager.getConnection(url, login, pwd);
            }
        } catch (SQLException sqle) {
            Alert alert= CommunStaticMethods.makeAlert("Attention!","Votre connexion a échoué","les données que vous saisirez seront enregistrées temporairement", Alert.AlertType.WARNING);
            alert.showAndWait();
            System.out.println("Erreur connexion :" + sqle.getMessage());
            try {
                maConnexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return maConnexion;
    }
}
