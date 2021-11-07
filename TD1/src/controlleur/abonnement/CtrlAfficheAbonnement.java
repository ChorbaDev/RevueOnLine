package controlleur.abonnement;


import controlleur.commun.CommunEntreAffiche;
import controlleur.commun.CommunStaticMethods;
import dao.Persistance;
import daofactory.DaoFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modele.metier.Abonnement;
import vue.dialogFiles.DialogMAJ;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CtrlAfficheAbonnement implements Initializable, ChangeListener<Abonnement>, CommunEntreAffiche<Abonnement> {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TableColumn<Abonnement, LocalDate> colDateDeb;

    @FXML
    private TableColumn<Abonnement, LocalDate> colDateFin;

    @FXML
    private TableColumn<Abonnement, Integer> colID;

    @FXML
    private TableColumn<Abonnement, Integer> colIDRevue;

    @FXML
    private TableColumn<Abonnement, Integer> colIdCl;

    @FXML
    private TableView<Abonnement> listeAbonnement;

    @FXML
    private TextField recherche;

    private DaoFactory dao;
    private Parent root;
    private String path;


    @FXML
    public void clickAjouter(ActionEvent event) throws SQLException, IOException {
        URL fxmlURL = getClass().getResource("../../vue/fxmlfiles/abonnement/vueAjoutAbonnement.fxml");
        DialogMAJ<CtrlAjoutAbonnement> ajoutAbonnement = new DialogMAJ(anchor, dao, listeAbonnement, fxmlURL);
    }

    @FXML
    public void clickModifier(ActionEvent event) throws SQLException, IOException {
        URL fxmlURL = getClass().getResource("../../vue/fxmlfiles/abonnement/vueModifAbonnement.fxml");
        DialogMAJ<CtrlAjoutAbonnement> ajoutAbonnement = new DialogMAJ(anchor, dao, listeAbonnement, fxmlURL);
    }

    @FXML
    public void clickSupprimer(ActionEvent event) throws SQLException, IOException {
        Alert alert = CommunStaticMethods.makeAlert("Confirmation", "Êtes-vous sûr de vouloir supprimer cet abonnement?", "", Alert.AlertType.CONFIRMATION);
        if (alert.showAndWait().get() == ButtonType.OK) {
            dao.getAbonnementDAO().delete(listeAbonnement.getSelectionModel().getSelectedItem());
            refreshListe();
        }

    }

    @Override
    public void getInfos(Persistance persistance) throws SQLException, IOException {
        try {
            dao = DaoFactory.getDAOFactory(persistance);
            refreshListe();
        } catch (SQLException | IOException | RuntimeException e) {
            dao = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
            refreshListe();
        }
    }

    @Override
    public void refreshListe() throws SQLException, IOException {
        if (listeAbonnement != null) {
            this.listeAbonnement.getItems().clear();
            listeAbonnement.getItems().addAll(dao.getAbonnementDAO().findAll());
        }

    }


    @FXML
    public void retourAccueil(ActionEvent event) throws IOException {
        path = "../../vue/fxmlfiles/Accueil.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        root = loader.getRoot();
        CommunStaticMethods.basculeScene(event, root);

    }

    @Override
    public void setColonnes() {
        colID.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id"));
        colIdCl.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_client"));
        colIDRevue.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_revue"));
        colDateDeb.setCellValueFactory(new PropertyValueFactory<Abonnement, LocalDate>("date_debut"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<Abonnement, LocalDate>("date_fin"));
        listeAbonnement.getColumns().setAll(colID, colIdCl, colIDRevue, colDateDeb, colDateFin);

    }

    @Override
    public void filter() {
        FilteredList<Abonnement> filteredList = new FilteredList<>(listeAbonnement.getItems(), b -> true);
        recherche.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredList.setPredicate(abo -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(abo.getId_client()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(abo.getId_revue()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (abo.getDate_debut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (abo.getDate_fin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
            SortedList<Abonnement> sortedList = new SortedList<>(filteredList);

            sortedList.comparatorProperty().bind(listeAbonnement.comparatorProperty());

            try{
                refreshListe();
            }catch(Exception e){
                e.getMessage();
            }
            listeAbonnement.getItems().setAll(sortedList);
        }));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColonnes();
        initChamps();
        this.listeAbonnement.getSelectionModel().selectedItemProperty().addListener(this);
        filter();

    }

    @Override
    public void changed(ObservableValue<? extends Abonnement> observableValue, Abonnement oldValue, Abonnement newValue) {
        this.btnSupprimer.setDisable(newValue == null);
        this.btnModifier.setDisable(newValue == null);
    }

    @Override
    public void initChamps() {
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);

    }
}