package controlleur.commun;

import dao.Persistance;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;

public interface CommunEntreAffiche<T> extends CommunEntreTout {
    void clickAjouter(ActionEvent event) throws IOException, SQLException, ClassNotFoundException;
    void clickModifier(ActionEvent event) throws SQLException, IOException, ClassNotFoundException;
    void clickSupprimer(ActionEvent event) throws SQLException, IOException, ClassNotFoundException;
    void getInfos(Persistance persistance) throws SQLException, IOException, ClassNotFoundException;
    void refreshListe() throws SQLException, IOException, ClassNotFoundException;
    void retourAccueil(ActionEvent event) throws IOException;
    void setColonnes();
    void filter();
}

