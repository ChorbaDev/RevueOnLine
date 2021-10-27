package controlleur.periodicite;

import controlleur.commun.CommunStaticMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import controlleur.commun.CommunEntreMAJ;
import daofactory.DaoFactory;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import modele.metier.Periodicite;
import vue.dialogFiles.DialogMAJ;

import java.io.IOException;
import java.sql.SQLException;

public class CtrlModifPeriodicite implements CommunEntreMAJ {

    @FXML
    private TextField edtPeriodicite;

    private DaoFactory dao;
    private Periodicite periodicite;
    private String aRemplacer;
    private AnchorPane anchor;
    private DialogMAJ<CtrlAjoutPeriodicite> vue;
    private TableView<Periodicite> tab;

    @Override
    public void clickMAJ() throws SQLException, IOException, ClassNotFoundException {
        Alert alert;
        aRemplacer = "";
        if (aRemplacer.isEmpty()) {
            dao.getPeriodiciteDAO().create(periodicite);
            aRemplacer = periodicite.toString();
            alert = CommunStaticMethods.makeAlert
                    ("Ajout avec succès",
                            "Cette periodicite a été ajouté avec succès",
                            aRemplacer,
                            Alert.AlertType.INFORMATION);
        } else {
            alert = CommunStaticMethods.makeAlert
                    ("Erreur lors de la saisie",
                            "Un ou plusieurs champs sont mal remplis.",
                            aRemplacer,
                            Alert.AlertType.ERROR);
        }
        alert.showAndWait();

    }

    @Override
    public void setObjectForMetier() throws SQLException, IOException, ClassNotFoundException {
        String libelle;
        libelle=edtPeriodicite.getText().trim();
        if (CommunStaticMethods.isStringOnlyAlphabet(libelle))
            periodicite.setLibelle(libelle);
        else{
            if (libelle.isEmpty()) aRemplacer+="Le libellé est obligatoire \n";
            else aRemplacer+="Le libelle contient des caractères non alphabétiques\n";
        }

    }

    @Override
    public void fermeDialog() throws SQLException, ClassNotFoundException, IOException {
        CommunStaticMethods.blurStage(anchor,0,0,0);
        this.tab.getItems().clear();
        if(tab!=null && dao!=null)
            this.tab.getItems().addAll(dao.getPeriodiciteDAO().findAll());
        this.vue.close();
    }

    @Override
    public void setVue(DialogMAJ vue, AnchorPane anchor, DaoFactory dao, TableView tab) throws SQLException, IOException, ClassNotFoundException {
        this.vue=vue;
        this.anchor=anchor;
        this.dao=dao;
        this.periodicite=(Periodicite) tab.getSelectionModel().getSelectedItem();
        this.tab=tab;
        initChamps();
    }

    @Override
    public void initChamps() {
        if (periodicite!=null){
            edtPeriodicite.setText(periodicite.getLibelle());
        }

    }
}
