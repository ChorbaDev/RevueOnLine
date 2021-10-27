package controlleur.abonnement;

import controlleur.commun.CommunEntreMAJ;
import controlleur.commun.CommunStaticMethods;
import daofactory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import modele.metier.Abonnement;
import modele.metier.Client;
import modele.metier.Revue;
import vue.dialogFiles.DialogMAJ;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CtrlAjoutAbonnement implements Initializable, CommunEntreMAJ {

    @FXML
    private ComboBox<Client> cbxIdClient;

    @FXML
    private ComboBox<Revue> cbxIdRevue;

    @FXML
    private DatePicker datePickerDeb;

    @FXML
    private DatePicker datePickerFin;


    private DaoFactory dao;
    private Abonnement abonnement;
    private String aRemplacer;
    private AnchorPane anchor;
    private DialogMAJ<CtrlAjoutAbonnement> vue;
    private TableView<Abonnement> tab;

    @FXML
    public void clickMAJ() throws SQLException, IOException, ClassNotFoundException {
        Alert alert;
        aRemplacer="";
        if (aRemplacer.isEmpty()){
            dao.getAbonnementDAO().create(abonnement);
            aRemplacer=abonnement.toString();
            alert=CommunStaticMethods.makeAlert(
                    "Ajout avec succès",
                    "Cet Abonnement a été ajouté avec succès",
                    aRemplacer,
                    Alert.AlertType.INFORMATION);
            abonnement=new Abonnement();
            initChamps();
        }else{
            alert=CommunStaticMethods.makeAlert(
                    "Erreur lors de la saisie",
                    "Un ou plusieurs champs sont mal remplis.",
                    aRemplacer,
                    Alert.AlertType.ERROR);
        }
        alert.showAndWait();

    }

    @FXML
    void fermeDialog(ActionEvent event) throws SQLException, ClassNotFoundException {
        CommunStaticMethods.blurStage(anchor,0,0,0);
        this.tab.getItems().clear();
        if (tab!=null && dao!=null)
            this.tab.getItems().addAll(dao.getAbonnementDAO().findAll());
        this.vue.close();

    }



    @Override
    public void setObjectForMetier() throws SQLException, IOException, ClassNotFoundException {
        int idcl,idrev;
        LocalDate dateDeb,dateFin;
        idcl=cbxIdClient.getSelectionModel().getSelectedItem().getCle();
        idrev=cbxIdRevue.getSelectionModel().getSelectedItem().getId();
        dateDeb=datePickerDeb.getValue();
        dateFin=datePickerFin.getValue();
        if (dao!=null){
            abonnement.setId_revue(dao.getRevueDAO().getByTitre(cbxIdRevue.getValue().getTitre()).get(0).getId());
            abonnement.setId_client(dao.getClientDAO().getByNomPrenom(cbxIdClient.getValue().getNom(),cbxIdClient.getValue().getPrenom()).get(0).getCle());
        }

        abonnement.setId_client(idcl);
        abonnement.setId_revue(idrev);
        abonnement.setDate_debut(dateDeb);
        abonnement.setDate_fin(dateFin);


    }

    @Override
    public void fermeDialog() throws SQLException, ClassNotFoundException, IOException {
        CommunStaticMethods.blurStage(anchor,0,0,0);
        this.tab.getItems().clear();
        if (tab!=null&&dao!=null)
            this.tab.getItems().addAll(dao.getAbonnementDAO().findAll());
        this.vue.close();

    }

    @Override
    public void setVue(DialogMAJ vueAjoutAbonnement, AnchorPane anchor, DaoFactory dao, TableView tab) throws SQLException, IOException, ClassNotFoundException {
        this.vue=vueAjoutAbonnement;
        this.anchor=anchor;
        this.dao=dao;
        this.tab=tab;
        if (dao!=null) {
            this.cbxIdRevue.setItems(FXCollections.observableArrayList(dao.getRevueDAO().findAll()));
            this.cbxIdClient.setItems(FXCollections.observableArrayList(dao.getClientDAO().findAll()));
        }
    }

    @Override
    public void initChamps() {
        cbxIdClient.getSelectionModel().clearSelection();
        cbxIdRevue.getSelectionModel().clearSelection();
        datePickerDeb.setValue(LocalDate.now());
        datePickerFin.setValue(LocalDate.now());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChamps();
        abonnement=new Abonnement();

    }
}