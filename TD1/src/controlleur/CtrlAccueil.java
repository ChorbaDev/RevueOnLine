package controlleur;

import controlleur.Revue.CtrlAfficheRevue;
import dao.Persistance;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlAccueil implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String path;
    @FXML
    private ComboBox<Persistance> comboType;

    @FXML
    void clickAbonnement(ActionEvent event) {

    }

    @FXML
    void clickClient(ActionEvent event) {

    }

    @FXML
    void clickPeriodicite(ActionEvent event) {

    }

    @FXML
    public void clickRevue(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
        path="../vue/fxmlfiles/Revue/afficheRevue.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        CtrlAfficheRevue controller=loader.getController();
        controller.getInfos(this.comboType.getSelectionModel().getSelectedItem());
        root = loader.getRoot();
        basculeScene(e);
    }
    public void basculeScene(ActionEvent e)
    {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       Persistance[] possibleValues = Persistance.values();
       this.comboType.setItems(FXCollections.observableArrayList(possibleValues));
       this.comboType.getSelectionModel().select(1);
    }
}
