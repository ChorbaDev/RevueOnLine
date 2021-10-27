package controlleur;

import controlleur.Client.CtrlAfficheClient;
import controlleur.Revue.CtrlAfficheRevue;
import controlleur.abonnement.CtrlAfficheAbonnement;
import controlleur.periodicite.CtrlAffichePeriodicite;
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
    void clickAbonnement(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        path="../vue/fxmlfiles/abonnement/vueAfficheAbonnement.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        CtrlAfficheAbonnement controller=loader.getController();
        controller.getInfos(this.comboType.getSelectionModel().getSelectedItem());
        root = loader.getRoot();
        basculeScene(event);

    }

    @FXML
    void clickClient(ActionEvent e) throws SQLException, IOException, ClassNotFoundException {
        path="../vue/fxmlfiles/Client/afficheClient.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        CtrlAfficheClient controller=loader.getController();
        controller.getInfos(this.comboType.getSelectionModel().getSelectedItem());
        root = loader.getRoot();
        basculeScene(e);
    }

    @FXML
    void clickPeriodicite(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        path="../vue/fxmlfiles/periodicite/vueAffichePeriodicite.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        CtrlAffichePeriodicite controller=loader.getController();
        controller.getInfos(this.comboType.getSelectionModel().getSelectedItem());
        root = loader.getRoot();
        basculeScene(event);

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
