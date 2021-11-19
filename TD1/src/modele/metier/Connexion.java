package modele.metier;

import controlleur.commun.CommunStaticMethods;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connexion à la base de donnée
 * La base distante utilisée est celle d'Omar Elloumi
 */
public class Connexion {
    private static Connexion instance;
    private final String URL="jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/";
    private final String LOGIN="";
    private final String PWD="";
    private Connection maConnexion;

    /**
     * Constructeur connexion privé
     * Utilisation du singleton pattern
     * @see java.awt.Desktop#getDesktop() for refrence
     */
    private Connexion()  {
        this.creeConnexion();
    }

    /**
     * @return une instance unique de la connexion
     */
    public static Connexion getInstance()  {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    /**
     * @return maConnexion un objet de type Connection
     * @see Connection
     */
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
