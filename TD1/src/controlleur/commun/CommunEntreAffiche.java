package controlleur.commun;

import dao.Persistance;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;

public interface CommunEntreAffiche<T> extends CommunEntreTout {
    void clickAjouter(ActionEvent event) throws IOException, SQLException;

    void clickModifier(ActionEvent event) throws SQLException, IOException;

    void clickSupprimer(ActionEvent event) throws SQLException, IOException;

    void getInfos(Persistance persistance) throws SQLException, IOException;

    void refreshListe() throws SQLException, IOException;

    void retourAccueil(ActionEvent event) throws IOException;

    void setColonnes();

    void filter();
}

