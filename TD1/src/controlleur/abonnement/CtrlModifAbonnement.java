package controlleur.abonnement;

import controlleur.commun.CommunEntreMAJ;
import controlleur.commun.CommunStaticMethods;
import daofactory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import modele.metier.Abonnement;
import modele.metier.Client;
import modele.metier.Revue;
import vue.dialogFiles.DialogMAJ;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CtrlModifAbonnement implements CommunEntreMAJ {

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
    public void clickMAJ() throws SQLException, IOException, ClassNotFoundException {
        Alert alert;
        aRemplacer = "";
        setObjectForMetier();
        if (aRemplacer.isEmpty()) {
            if(nonDoublons()){
                dao.getAbonnementDAO().update(abonnement);
                aRemplacer = abonnement.toString();
                alert = CommunStaticMethods.makeAlert(
                        "Modification avec succès",
                        "Cet Abonnement a été modifié avec succès",
                        aRemplacer,
                        Alert.AlertType.INFORMATION);
            }else{
                aRemplacer = "Cette abonnement existe déja";
                alert = CommunStaticMethods.makeAlert(
                        "Attention!",
                        "Probléme de modification",
                        aRemplacer,
                        Alert.AlertType.WARNING);
            }
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
    public void fermeDialog() throws SQLException, ClassNotFoundException, IOException {
        CommunStaticMethods.blurStage(anchor, 0, 0, 0);
        this.tab.getItems().clear();
        if (tab != null && dao != null)
            this.tab.getItems().addAll(dao.getAbonnementDAO().findAll());
        this.vue.close();
    }

    private Abonnement set() {
        int idcl = 0, idrev = 0;
        LocalDate dateDeb, dateFin;
        String[] infoClient, infoRevue;
        Abonnement ab=new Abonnement(abonnement.getId());
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
                ab.setId_revue(idrev);
            } else {
                aRemplacer += "L'ID revue passé est incorrecte. \n";
            }
            if (idrev > 0) {
                ab.setId_client(idcl);
            } else {
                aRemplacer += "L'ID Client passé est incorrecte. \n";
            }
        }
        if (dateDeb.isEqual(LocalDate.now()) || dateDeb.isAfter(LocalDate.now())) {
            ab.setDate_debut(dateDeb);
        } else {
            if (dateDeb.isBefore(LocalDate.now())) {
                aRemplacer += "La date de début entrée est inferieure à la date du jour \n";
            }
        }
        if (dateFin.isEqual(dateDeb) || dateFin.isAfter(dateDeb)) {
            ab.setDate_fin(dateFin);
        } else {
            if (dateFin.isBefore(LocalDate.now())) {
                aRemplacer += "La date de fin entrée est inferieure à la date du jour \n";
            } else aRemplacer += "La date de fin entrée est inferieur à la date de début \n";
        }
        return ab;
    }
    @Override
    public void setObjectForMetier(){
        abonnement=set();
    }


    @Override
    public void setVue(DialogMAJ vue, AnchorPane anchor, DaoFactory dao, TableView tab) throws SQLException, IOException, ClassNotFoundException {
        this.vue = vue;
        this.anchor = anchor;
        this.dao = dao;
        this.abonnement = (Abonnement) tab.getSelectionModel().getSelectedItem();
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
        initChamps();

    }

    @Override
    public boolean nonDoublons() throws SQLException, ClassNotFoundException, IOException {
        ArrayList<Abonnement> list = dao.getAbonnementDAO().findAll();
        for (Abonnement ab : list) {
            if (ab.equalsTout(abonnement))
                return false;
        }
        return true;
    }

    @Override
    public void initChamps() {
        if (abonnement != null) {
            String infoClient = null;
            Client cl = null;
            String infoRevue = null;
            Revue rev = null;
            try {
                cl = dao.getClientDAO().getById(abonnement.getId_client());
                infoClient = abonnement.getId_client() + " " + cl.getNom() + " " + cl.getPrenom();
                rev = dao.getRevueDAO().getById(abonnement.getId_revue());
                infoRevue = abonnement.getId_revue() + " " + rev.getTitre();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            cbxIdClient.setValue(infoClient);
            cbxIdRevue.setValue(infoRevue);
            datePickerDeb.setValue(abonnement.getDate_debut());
            datePickerFin.setValue(abonnement.getDate_fin());
        }

    }

}