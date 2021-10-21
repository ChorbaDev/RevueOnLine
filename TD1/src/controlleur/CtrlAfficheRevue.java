package controlleur;

import dao.Persistance;
import daofactory.DaoFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.metier.Revue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlAfficheRevue implements Initializable, ChangeListener<Revue> {
    @FXML private TableView<Revue> listeRevue;
    @FXML private TableColumn<Revue, String> colDescp;
    @FXML private TableColumn<Revue, Integer> colID;
    @FXML private TableColumn<Revue, Integer> colIDP;
    @FXML private TableColumn<Revue, Double> colTarif;
    @FXML private TableColumn<Revue, String> colTitre;
    @FXML private ImageView imgVisuel;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    private DaoFactory dao;
    @FXML
    void clickAjouter(ActionEvent event) {

    }

    @FXML
    void clickModifier(ActionEvent event) {

    }

    @FXML
    void clickSupprimer(ActionEvent event) throws SQLException, IOException {
        dao.getRevueDAO().delete(listeRevue.getSelectionModel().getSelectedItem());
        this.listeRevue.getItems().clear();
        this.listeRevue.getItems().addAll(dao.getRevueDAO().findAll());
    }
    private void setColonnes() {
        colID.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Revue, String>("titre"));
        colDescp.setCellValueFactory(new PropertyValueFactory<Revue, String>("description"));
        colTarif.setCellValueFactory(new PropertyValueFactory<Revue, Double>("tarif_numero"));
        colIDP.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id_p"));
        listeRevue.getColumns().setAll(colID,colTitre,colDescp,colTarif,colIDP);
    }

    private void initChamps() {
        initImg();
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
    }

    private void initImg() {
        File file = new File("TD1/src/vue/images/empty.jpg");
        Image image = new Image(file.toURI().toString());
        imgVisuel.setImage(image);
    }
    private void setVisuel() throws IOException {
        Revue revue=listeRevue.getSelectionModel().getSelectedItem();
        if(revue.getVisuelImg()!=null)
        imgVisuel.setImage(revue.getVisuelImg());
        else initImg();
    }
    @Override
    public void changed(ObservableValue<? extends Revue> observableValue, Revue oldValue, Revue newValue) {
        this.btnSupprimer.setDisable(newValue == null);
        this.btnModifier.setDisable(newValue == null);
        if(newValue!=null) {
            try {
                setVisuel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColonnes();
        initChamps();
        this.listeRevue.getSelectionModel().selectedItemProperty().addListener(this);
    }

    public void getInfos(Persistance persistance) {
        dao = DaoFactory.getDAOFactory(persistance);
        try {
            this.listeRevue.getItems().addAll(dao.getRevueDAO().findAll());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
