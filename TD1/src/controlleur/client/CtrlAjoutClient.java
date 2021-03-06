package controlleur.client;

import controlleur.commun.CommunEntreMAJ;
import controlleur.commun.CommunStaticMethods;
import daofactory.DaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import modele.metier.Adresse;
import modele.metier.Client;
import process.ProcessAdresse;
import vue.dialogFiles.DialogMAJ;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CtrlAjoutClient implements Initializable, CommunEntreMAJ {
    @FXML
    private TextField edtCodeP;
    @FXML
    private TextField edtNoRue;
    @FXML
    private TextField edtNom;
    @FXML
    private TextField edtPays;
    @FXML
    private TextField edtPrenom;
    @FXML
    private TextField edtVille;
    @FXML
    private TextField edtVoie;
    private DialogMAJ<CtrlAjoutClient> vue;
    private TableView<Client> tab;
    private DaoFactory dao;
    private AnchorPane anchor;
    private Client client;
    private Adresse adresse;
    private String aRemplacer;

    @FXML
    public void clickMAJ() throws SQLException, IOException {
        Alert alert;
        aRemplacer = "";
        setObjectForMetier();
        if (aRemplacer.isEmpty()) {
            ProcessAdresse pa = new ProcessAdresse();
            pa.normalizeAdresse(adresse);
            client.setAdresse(adresse);
            if (nonDoublons()) {
                dao.getClientDAO().create(client);
                aRemplacer = client.toString();
                alert = CommunStaticMethods.makeAlert
                        ("Ajout avec succès",
                                "Cette client a été ajouté avec succès",
                                aRemplacer,
                                Alert.AlertType.INFORMATION);
                client = new Client();
                adresse = new Adresse();
                initChamps();
            } else {
                aRemplacer = "Cette client existe déja";
                alert = CommunStaticMethods.makeAlert
                        ("Attention!",
                                "Probléme d'insertion",
                                aRemplacer,
                                Alert.AlertType.WARNING);
            }
        }
         else{
                alert = CommunStaticMethods.makeAlert
                        ("Erreur lors de la saisie",
                                "Un ou plusieurs champs sont mal remplis.",
                                aRemplacer,
                                Alert.AlertType.ERROR);
            }
        alert.showAndWait();
    }

    public void setObjectForMetier() {
        String nom, prenom, pay, rue, voie, code, ville;
        nom = edtNom.getText().trim();
        prenom = edtPrenom.getText().trim();
        pay = edtPays.getText().trim();
        rue = edtNoRue.getText().trim();
        voie = edtVoie.getText().trim();
        code = edtCodeP.getText().trim();
        ville = edtVille.getText().trim();

        if (CommunStaticMethods.isStringOnlyAlphabet(nom))
            client.setNom(nom);
        else {
            if (nom.isEmpty()) aRemplacer += "Le nom est obligatoire\n";
            else aRemplacer += "Le nom ne contient pas des caractéres non alphabétiques\n";
        }
        if (CommunStaticMethods.isStringOnlyAlphabet(prenom))
            client.setPrenom(prenom);
        else {
            if (prenom.isEmpty()) aRemplacer += "Le Prénom est obligatoire\n";
            else aRemplacer += "Le prenom ne contient pas des caractéres non alphabétiques\n";
        }
        if (!rue.isEmpty()) {
            adresse.setNo_rue(rue);
        } else {
            aRemplacer += "Le Rue est obligatoire\n";
        }
        if (!voie.isEmpty()) {
            adresse.setVoie(voie);
        } else {
            aRemplacer += "Le Voie est obligatoire\n";
        }
        if (!code.isEmpty()) {
            adresse.setCode_postal(code);
        } else {
            aRemplacer += "Le Code postal est obligatoire\n";
        }
        if (!ville.isEmpty()) {
            adresse.setVille(ville);
        } else {
            aRemplacer += "La Ville est obligatoire\n";
        }
        if (CommunStaticMethods.isStringOnlyAlphabet(pay))
            adresse.setPays(pay);
        else {
            if (pay.isEmpty()) aRemplacer += "Le pay est obligatoire\n";
            else aRemplacer += "Le pay ne contient pas des caractéres non alphabétiques\n";
        }
    }
    public boolean nonDoublons() throws SQLException {
        ArrayList<Client> list = dao.getClientDAO().findAll();
        for (Client cl : list) {
            if (cl.equalsTout(client))
                return false;
        }
        return true;
    }
    @FXML
    public void fermeDialog() throws SQLException {
        CommunStaticMethods.blurStage(anchor, 0, 0, 0);
        this.tab.getItems().clear();
        if (tab != null && dao != null)
            this.tab.getItems().addAll(dao.getClientDAO().findAll());
        this.vue.close();
    }

    public void setVue(DialogMAJ vueAjoutClient, AnchorPane anchor, DaoFactory dao, TableView tab) {
        this.vue = vueAjoutClient;
        this.anchor = anchor;
        this.dao = dao;
        this.tab = tab;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChamps();
        client = new Client();
        adresse = new Adresse();
    }

    public void initChamps() {
        edtCodeP.setText("");
        edtNom.setText("");
        edtNoRue.setText("");
        edtPays.setText("");
        edtPrenom.setText("");
        edtVille.setText("");
        edtVoie.setText("");
    }
}
