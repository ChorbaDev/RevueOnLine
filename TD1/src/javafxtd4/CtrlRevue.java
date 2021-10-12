package javafxtd4;

import dao.Persistance;
import daofactory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import metier.Periodicite;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlRevue implements Initializable {
    @FXML private Button btnCreer;
    @FXML private ComboBox<Periodicite> comboPeriodicite;
    @FXML private TextArea edtDescription;
    @FXML private TextField edtTarif;
    @FXML private TextField edtTitre;
    @FXML private Label lblExp;

    @FXML
    public void clickCreer(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DaoFactory dao = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
        try {
            this.comboPeriodicite.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
