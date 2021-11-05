package controlleur.periodicite;

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

import modele.metier.Periodicite;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import vue.dialogFiles.DialogMAJ;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;


public class CtrlAffichePeriodicite implements Initializable, ChangeListener<Periodicite>, CommunEntreAffiche<Periodicite> {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TableColumn<Periodicite, Integer> colID;

    @FXML
    private TableColumn<Periodicite, String> colLibelle;

    @FXML
    private TableView<Periodicite> listePeriodicite;

    @FXML
    private TextField recherche;

    private DaoFactory dao;
    private Parent root;
    private String path;

    @FXML
    public void clickAjouter(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        URL fxmlURL=getClass().getResource("../../vue/fxmlfiles/periodicite/vueAjoutPeriodicite.fxml");
        DialogMAJ<CtrlAjoutPeriodicite> ajoutPeriodicite= new DialogMAJ(anchor,dao,listePeriodicite,fxmlURL);

    }

    @FXML
    public void clickModifier(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        URL fxmlURL=getClass().getResource("../../vue/fxmlfiles/periodicite/vueModifPeriodicite.fxml");
        DialogMAJ<CtrlAjoutPeriodicite> ajoutPeriodicite= new DialogMAJ(anchor,dao,listePeriodicite,fxmlURL);

    }

    @FXML
    public void clickSupprimer(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        Alert alert = CommunStaticMethods.makeAlert("Confirmation", "Êtes-vous sûr de vouloir supprimer cette periodicité?", "", Alert.AlertType.CONFIRMATION);
        if (alert.showAndWait().get() == ButtonType.OK) {
            dao.getPeriodiciteDAO().delete(listePeriodicite.getSelectionModel().getSelectedItem());
            refreshListe();
        }


    }


    @Override
    public void getInfos(Persistance persistance) throws SQLException, IOException, ClassNotFoundException {
        try {
            dao = DaoFactory.getDAOFactory(persistance);
            refreshListe();
        } catch (SQLException | IOException | RuntimeException e) {
            dao = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
            refreshListe();
        }
    }

    @Override
    public void refreshListe() throws SQLException, IOException, ClassNotFoundException {
        if (listePeriodicite != null) {
            this.listePeriodicite.getItems().clear();
            this.listePeriodicite.getItems().addAll(dao.getPeriodiciteDAO().findAll());
        }

    }

    @Override
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
        colID.setCellValueFactory(new PropertyValueFactory<Periodicite, Integer>("cle"));
        colLibelle.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("libelle"));
        listePeriodicite.getColumns().setAll(colID, colLibelle);

    }

    @Override
    public void filter() {
        FilteredList<Periodicite> filteredList = new FilteredList<>(listePeriodicite.getItems(), b -> true);
        recherche.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredList.setPredicate(per -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (per.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
            SortedList<Periodicite> sortedList = new SortedList<>(filteredList);

            sortedList.comparatorProperty().bind(listePeriodicite.comparatorProperty());
            try{
                refreshListe();
            }catch(Exception e){
                e.getMessage();
            }
            listePeriodicite.getItems().setAll(sortedList);
        }));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColonnes();
        initChamps();
        this.listePeriodicite.getSelectionModel().selectedItemProperty().addListener(this);
        filter();

    }

    @Override
    public void initChamps() {
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);

    }

    @Override
    public void changed(ObservableValue<? extends Periodicite> observableValue, Periodicite oldValue, Periodicite newValue) {
        this.btnSupprimer.setDisable(newValue == null);
        this.btnModifier.setDisable(newValue == null);

    }
}
