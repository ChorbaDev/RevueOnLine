package controlleur.commun;

import daofactory.DaoFactory;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import vue.dialogFiles.DialogMAJ;

import java.io.IOException;
import java.sql.SQLException;

public interface CommunEntreMAJ extends CommunEntreTout {
    void clickMAJ() throws SQLException, IOException;

    void setObjectForMetier() throws SQLException, IOException;

    void fermeDialog() throws SQLException, IOException;

    void setVue(DialogMAJ vue, AnchorPane anchor, DaoFactory dao, TableView tab) throws SQLException, IOException;

    boolean nonDoublons() throws SQLException, IOException;
}
