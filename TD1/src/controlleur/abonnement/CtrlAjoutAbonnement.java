package controlleur.abonnement;

import controlleur.commun.CommunEntreMAJ;
import controlleur.commun.CommunStaticMethods;
import daofactory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CtrlAjoutAbonnement implements Initializable, CommunEntreMAJ {

    @FXML
    private ComboBox<String> cbxIdClient; //ID + Nom + Prenom

    @FXML
    private ComboBox<String> cbxIdRevue; //ID + Titre

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
    public void clickMAJ() throws SQLException, IOException {
        Alert alert;
        aRemplacer = "";
        setObjectForMetier();
        if (aRemplacer.isEmpty()) {
            dao.getAbonnementDAO().create(abonnement);
            aRemplacer = abonnement.toString();
            alert = CommunStaticMethods.makeAlert(
                    "Ajout avec succès",
                    "Cet Abonnement a été ajouté avec succès",
                    aRemplacer,
                    Alert.AlertType.INFORMATION);
            abonnement = new Abonnement();
            initChamps();
        } else {
            alert = CommunStaticMethods.makeAlert(
                    "Erreur lors de la saisie",
                    "Un ou plusieurs champs sont mal remplis.",
                    aRemplacer,
                    Alert.AlertType.ERROR);
        }
        alert.showAndWait();

    }

    @FXML
    public void fermeDialog() throws SQLException, IOException {
        CommunStaticMethods.blurStage(anchor, 0, 0, 0);
        this.tab.getItems().clear();
        if (tab != null && dao != null)
            this.tab.getItems().addAll(dao.getAbonnementDAO().findAll());
        this.vue.close();

    }


    @Override
    public void setObjectForMetier() throws SQLException, IOException {
        int idcl = 0, idrev = 0;
        LocalDate dateDeb, dateFin;
        String[] infoClient, infoRevue;
        if (!cbxIdClient.getSelectionModel().isEmpty()) {
            infoClient = cbxIdClient.getValue().split(" ");
            idcl = Integer.parseInt(infoClient[0]);
        }
        if (!cbxIdRevue.getSelectionModel().isEmpty()) {
            infoRevue = cbxIdRevue.getValue().split(" ");
            idrev = Integer.parseInt(infoRevue[0]);
        }
        dateDeb = datePickerDeb.getValue();
        dateFin = datePickerFin.getValue();

        if (dao != null) {
            if (idcl > 0) {
                abonnement.setId_revue(idrev);
            } else {
                aRemplacer += "L'ID revue passé est incorrecte. \n";
            }
            if (idrev > 0) {
                abonnement.setId_client(idcl);
            } else {
                aRemplacer += "L'ID Client passé est incorrecte. \n";
            }
        }
        if (dateDeb.isEqual(LocalDate.now()) || dateDeb.isAfter(LocalDate.now())) {
            abonnement.setDate_debut(dateDeb);
        } else {
            if (dateDeb.isBefore(LocalDate.now())) {
                aRemplacer += "La date de début entrée est inferieure à la date du jour \n";
            }
        }
        if (dateFin.isEqual(dateDeb) || dateFin.isAfter(dateDeb)) {
            abonnement.setDate_fin(dateFin);
        } else {
            if (dateFin.isBefore(LocalDate.now())) {
                aRemplacer += "La date de fin entrée est inferieure à la date du jour \n";
            } else aRemplacer += "La date de fin entrée est inferieur à la date de début \n";
        }


    }

    @Override
    public void setVue(DialogMAJ vueAjoutAbonnement, AnchorPane anchor, DaoFactory dao, TableView tab) throws SQLException, IOException {

        this.vue = vueAjoutAbonnement;
        this.anchor = anchor;
        this.dao = dao;
        this.tab = tab;
        if (dao != null) {
            ArrayList<Client> listCl = dao.getClientDAO().findAll();
            ObservableList<String> obsCl = FXCollections.observableArrayList();
            ArrayList<Revue> listRev = dao.getRevueDAO().findAll();
            ObservableList<String> obsRev = FXCollections.observableArrayList();
            for (Client c : listCl) {
                obsCl.add(c.getCle() + " " + c.getNom() + " " + c.getPrenom());
            }
            this.cbxIdClient.setItems(obsCl);
            for (Revue r : listRev) {
                obsRev.add(r.getId() + " " + r.getTitre());
            }
            this.cbxIdRevue.setItems(obsRev);
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
        abonnement = new Abonnement();

    }
}