package controlleur.client;

import controlleur.commun.CommunEntreMAJ;
import controlleur.commun.CommunStaticMethods;
import daofactory.DaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import modele.metier.Adresse;
import modele.metier.Client;
import process.ProcessAdresse;
import vue.dialogFiles.DialogMAJ;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CtrlModifClient implements CommunEntreMAJ {
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
    private DialogMAJ<CtrlModifClient> vue;
    private TableView<Client> tab;
    private DaoFactory dao;
    private AnchorPane anchor;
    private Client client;
    private Adresse adresse;
    private String aRemplacer;

    public void initChamps() {
        if (client != null) {
            edtCodeP.setText(client.getAdresse().getCode_postal());
            edtNom.setText(client.getNom());
            edtNoRue.setText(client.getAdresse().getNo_rue());
            edtPays.setText(client.getAdresse().getPays());
            edtPrenom.setText(client.getPrenom());
            edtVille.setText(client.getAdresse().getVille());
            edtVoie.setText(client.getAdresse().getVoie());
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
    public void clickMAJ() throws SQLException, IOException {
        Alert alert;
        aRemplacer = "";
        setObjectForMetier();
        if (aRemplacer.isEmpty())
        {
            ProcessAdresse pa = new ProcessAdresse();
            pa.normalizeAdresse(adresse);
            client.setAdresse(adresse);
            if(nonDoublons())
            {
                dao.getClientDAO().update(client);
                aRemplacer = client.toString();
                alert = CommunStaticMethods.makeAlert
                        ("Modification avec succ??s",
                                "Cette client a ??t?? modifi?? avec succ??s",
                                aRemplacer,
                                Alert.AlertType.INFORMATION);
            }
            else
            {
                aRemplacer = "Client existe d??ja";
                alert = CommunStaticMethods.makeAlert
                        ("Attention!",
                                "Probl??me de modification",
                                aRemplacer,
                                Alert.AlertType.WARNING);
            }
        }
        else
        {
            alert = CommunStaticMethods.makeAlert
                    ("Erreur lors de la saisie",
                            "Un ou plusieurs champs sont mal remplis.",
                            aRemplacer,
                            Alert.AlertType.ERROR);
        }
        alert.showAndWait();
    }
    public Client set() {
        String nom, prenom, pay, rue, voie, code, ville;
        nom = edtNom.getText().trim();
        prenom = edtPrenom.getText().trim();
        pay = edtPays.getText().trim();
        rue = edtNoRue.getText().trim();
        voie = edtVoie.getText().trim();
        code = edtCodeP.getText().trim();
        ville = edtVille.getText().trim();
        Client c=new Client(client.getCle());
        if (CommunStaticMethods.isStringOnlyAlphabet(nom))
            c.setNom(nom);
        else {
            if (nom.isEmpty()) aRemplacer += "Le nom est obligatoire\n";
            else aRemplacer += "Le nom ne contient pas des caract??res non alphab??tiques\n";
        }
        if (CommunStaticMethods.isStringOnlyAlphabet(prenom))
            c.setPrenom(prenom);
        else {
            if (prenom.isEmpty()) aRemplacer += "Le Pr??nom est obligatoire\n";
            else aRemplacer += "Le prenom ne contient pas des caract??res non alphab??tiques\n";
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
            else aRemplacer += "Le pay ne contient pas des caract??res non alphab??tiques\n";
        }
        return c;
    }
    public void setObjectForMetier() {
        client=set();
    }

    public void setVue(DialogMAJ vue, AnchorPane anchor, DaoFactory dao, TableView tab) {
        this.vue = vue;
        this.anchor = anchor;
        this.dao = dao;
        this.client = (Client) tab.getSelectionModel().getSelectedItem();
        this.adresse = this.client.getAdresse();
        this.tab = tab;
        initChamps();
    }

    @FXML
    public void fermeDialog() throws SQLException {
        CommunStaticMethods.blurStage(anchor, 0, 0, 0);
        //this.tab.getItems().clear();
        if (tab != null && dao != null){
            this.tab.getItems().clear();
            this.tab.getItems().addAll(dao.getClientDAO().findAll());
        }

        this.vue.close();
    }
}
