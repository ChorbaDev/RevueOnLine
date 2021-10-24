package controlleur.Revue;

import dao.Persistance;
import daofactory.DaoFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.metier.Revue;
import vue.dialogFiles.Revue.vueAjoutRevue;
import vue.dialogFiles.Revue.vueModifRevue;

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
    @FXML private AnchorPane anchor;
    @FXML private Label titre;
    private DaoFactory dao;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String path;
    @FXML
    void clickAjouter(ActionEvent event) throws IOException, SQLException {
        vueAjoutRevue ajoutRevue= new vueAjoutRevue(anchor,dao,listeRevue);
    }

    @FXML
    void clickModifier(ActionEvent event) throws SQLException, IOException {
        vueModifRevue modifRevue= new vueModifRevue(anchor,dao,listeRevue);
    }
    @FXML
    void retourAccueil(ActionEvent event) throws IOException {
        path="../../vue/fxmlfiles/Accueil.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        root = loader.getRoot();
        basculeScene(event);
    }
    @FXML
    void clickSupprimer(ActionEvent event) throws SQLException, IOException {
        Alert alert=makeAlert
                ("Confirmation",
                        "Est-ce-que vous ete sur de supprimer cette revue?",
                        "",
                        Alert.AlertType.CONFIRMATION);
        if(alert.showAndWait().get()==ButtonType.OK){
            dao.getRevueDAO().delete(listeRevue.getSelectionModel().getSelectedItem());
            this.listeRevue.getItems().clear();
            this.listeRevue.getItems().addAll(dao.getRevueDAO().findAll());
            initImg();
        }
    }
    private Alert makeAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
    private void setColonnes() {
        colID.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Revue, String>("titre"));
        colDescp.setCellValueFactory(new PropertyValueFactory<Revue, String>("description"));
        colTarif.setCellValueFactory(new PropertyValueFactory<Revue, Double>("tarif_numero"));
        colIDP.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id_p"));
        listeRevue.getColumns().setAll(colID,colTitre,colDescp,colTarif,colIDP);
    }
    public void basculeScene(ActionEvent e)
    {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    public void getInfos(Persistance persistance) throws SQLException, IOException {
        try {
            dao = DaoFactory.getDAOFactory(persistance);
            refreshListe();
        } catch (SQLException | IOException | RuntimeException e) {
            dao = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
            refreshListe();
        }
    }
    public void refreshListe() throws SQLException, IOException {
        if(listeRevue!=null)
        this.listeRevue.getItems().addAll(dao.getRevueDAO().findAll());
    }
}
