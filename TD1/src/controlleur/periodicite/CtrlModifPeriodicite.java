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
import modele.metier.Client;
import modele.metier.Periodicite;
import vue.dialogFiles.DialogMAJ;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CtrlModifPeriodicite implements CommunEntreMAJ {

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
    @Override
    public void clickMAJ() throws SQLException, IOException {
        Alert alert;
        aRemplacer = "";
        setObjectForMetier();
        if (aRemplacer.isEmpty()) {
            if(nonDoublons()){
                dao.getPeriodiciteDAO().update(periodicite);
                aRemplacer = periodicite.toString();
                alert = CommunStaticMethods.makeAlert
                        ("Ajout avec succès",
                                "Cette periodicite a été ajouté avec succès",
                                aRemplacer,
                                Alert.AlertType.INFORMATION);
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
    public Periodicite set()  {
        String libelle;
        Periodicite p=new Periodicite(periodicite.getCle());
        libelle=edtPeriodicite.getText().trim();
        if (CommunStaticMethods.isStringOnlyAlphabet(libelle))
            p.setLibelle(libelle);
        else{
            if (libelle.isEmpty()) aRemplacer+="Le libellé est obligatoire \n";
            else aRemplacer+="Le libelle contient des caractères non alphabétiques\n";
        }
        return p;
    }
    @Override
    public void setObjectForMetier()  {
        periodicite=set();
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
    public void setVue(DialogMAJ vue, AnchorPane anchor, DaoFactory dao, TableView tab) throws SQLException, IOException {
        this.vue = vue;
        this.anchor = anchor;
        this.dao = dao;
        this.periodicite = (Periodicite) tab.getSelectionModel().getSelectedItem();
        this.tab = tab;
        initChamps();
    }

    @Override
    public void initChamps() {
        if (periodicite != null) {
            edtPeriodicite.setText(periodicite.getLibelle());
        }

    }
}
