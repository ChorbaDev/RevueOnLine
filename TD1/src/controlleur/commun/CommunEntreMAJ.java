package controlleur.commun;

import daofactory.DaoFactory;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import modele.metier.Client;
import vue.dialogFiles.DialogMAJ;

import java.io.IOException;
import java.sql.SQLException;

public interface CommunEntreMAJ extends CommunEntreTout {
    void clickMAJ() throws SQLException, IOException, ClassNotFoundException;
    void setObjectForMetier() throws SQLException, IOException, ClassNotFoundException;
    void fermeDialog() throws SQLException, ClassNotFoundException, IOException;
    void setVue(DialogMAJ vue, AnchorPane anchor, DaoFactory dao, TableView tab) throws SQLException, IOException, ClassNotFoundException;
}
