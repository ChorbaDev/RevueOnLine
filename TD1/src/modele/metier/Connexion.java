package modele.metier;

import controlleur.commun.CommunStaticMethods;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connexion instance;
    private final String URL="jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/elloumi2u_base";
    private final String LOGIN="elloumi2u_appli";
    private final String PWD="32024561";
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

        try {
            if (maConnexion == null || maConnexion.isClosed()){
                DriverManager.setLoginTimeout(2);
                maConnexion = DriverManager.getConnection(this.URL, this.LOGIN, this.PWD);
            }
        } catch (SQLException sqle) {
            Alert alert= CommunStaticMethods.makeAlert("Attention!","Votre connexion a échoué","les données que vous saisirez seront enregistrées temporairement", Alert.AlertType.WARNING);
            alert.showAndWait();
            System.out.println("Erreur connexion :" + sqle.getMessage());
            /*try {
                maConnexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }
        return maConnexion;
    }
}
