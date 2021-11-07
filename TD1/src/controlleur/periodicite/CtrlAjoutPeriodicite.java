package controlleur.periodicite;

import controlleur.commun.CommunEntreMAJ;
import controlleur.commun.CommunStaticMethods;
import daofactory.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modele.metier.Periodicite;
import vue.dialogFiles.DialogMAJ;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CtrlAjoutPeriodicite implements Initializable, CommunEntreMAJ {

    @FXML
    private TextField edtPeriodicite;

    private DaoFactory dao;
    private Periodicite periodicite;
    private String aRemplacer;
    private AnchorPane anchor;
    private DialogMAJ<CtrlAjoutPeriodicite> vue;
    private TableView<Periodicite> tab;
    public boolean nonDoublons() throws SQLException {
        ArrayList<Periodicite> list = dao.getPeriodiciteDAO().findAll();
        for (Periodicite pr : list) {
            if (pr.getLibelle().equals(periodicite.getLibelle()))
                return false;
        }
        return true;
    }
    @FXML
    public void clickMAJ() throws SQLException, IOException {
        Alert alert;
        aRemplacer = "";
        setObjectForMetier();
        if (aRemplacer.isEmpty()) {
            if(nonDoublons()){
                dao.getPeriodiciteDAO().create(periodicite);
                aRemplacer = periodicite.toString();
                alert = CommunStaticMethods.makeAlert
                        ("Ajout avec succès",
                                "Cette periodicite a été ajouté avec succès",
                                aRemplacer,
                                Alert.AlertType.INFORMATION);
                periodicite = new Periodicite();
                initChamps();
            }else{
                aRemplacer = "Périodicité existe déja";
                alert = CommunStaticMethods.makeAlert
                        ("Attention!",
                                "Probléme de modification",
                                aRemplacer,
                                Alert.AlertType.WARNING);
            }

        } else {
            alert = CommunStaticMethods.makeAlert
                    ("Erreur lors de la saisie",
                            "Un ou plusieurs champs sont mal remplis.",
                            aRemplacer,
                            Alert.AlertType.ERROR);
        }
        alert.showAndWait();
    }

    @FXML
    void fermeDialog(ActionEvent event) throws SQLException {
        CommunStaticMethods.blurStage(anchor, 0, 0, 0);
        this.tab.getItems().clear();
        if (tab != null && dao != null)
            this.tab.getItems().addAll(dao.getPeriodiciteDAO().findAll());
        this.vue.close();

    }

    public void initChamps() {
        edtPeriodicite.setText("");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChamps();
        periodicite = new Periodicite();

    }


    @Override
    public void setObjectForMetier() throws SQLException, IOException {
        String libelle;
        libelle = edtPeriodicite.getText().trim();
        if (CommunStaticMethods.isStringOnlyAlphabet(libelle))
            periodicite.setLibelle(libelle);
        else {
            if (libelle.isEmpty()) aRemplacer += "Le libellé est obligatoire \n";
            else aRemplacer += "Le libelle contient des caractères non alphabétiques\n";
        }

    }

    @Override
    public void fermeDialog() throws SQLException, IOException {
        CommunStaticMethods.blurStage(anchor, 0, 0, 0);
        this.tab.getItems().clear();
        if (tab != null && dao != null)
            this.tab.getItems().addAll(dao.getPeriodiciteDAO().findAll());
        this.vue.close();

    }

    @Override
    public void setVue(DialogMAJ vueAjoutPeriodicite, AnchorPane anchor, DaoFactory dao, TableView tab) throws SQLException, IOException {
        this.vue = vueAjoutPeriodicite;
        this.anchor = anchor;
        this.dao = dao;
        this.tab = tab;

    }
}
