package controlleur.Client;

import daofactory.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import modele.metier.Adresse;
import modele.metier.Client;
import vue.dialogFiles.Client.vueAjoutClient;
import vue.dialogFiles.Client.vueModifClient;

import java.io.IOException;
import java.sql.SQLException;

public class CtrlModifClient {
    @FXML private TextField edtCodeP;
    @FXML private TextField edtNoRue;
    @FXML private TextField edtNom;
    @FXML private TextField edtPays;
    @FXML private TextField edtPrenom;
    @FXML private TextField edtVille;
    @FXML private TextField edtVoie;
    private vueModifClient vue;
    private TableView<Client> tab;
    private DaoFactory dao;
    private AnchorPane anchor;
    private Client client;
    private Adresse adresse;
    private String aRemplacer;

    public static boolean isStringOnlyAlphabet(String str)
    {
        return ((!str.equals(""))
                && (str != null)
                && (str.matches("^[a-zA-Z ]*$")));
    }
    @FXML
    void clickModif(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        Alert alert;
        aRemplacer="";
        setClient();
        if(aRemplacer.isEmpty()){
            client.setAdresse(adresse);
            dao.getClientDAO().update(client);
            aRemplacer=client.toString();
            alert=makeAlert
                    ("Ajout avec succès",
                            "Cette client a été ajouté avec succès",
                            aRemplacer,
                            Alert.AlertType.INFORMATION);
            client=new Client();
            adresse=new Adresse();
        }
        else{
            alert=makeAlert
                    ("Erreur lors de la saisie",
                            "Un ou plusieurs champs sont mal remplis.",
                            aRemplacer,
                            Alert.AlertType.ERROR);
        }
        alert.showAndWait();
    }
    private void setClient() {
        String nom,prenom,pay,rue,voie,code,ville;
        nom=edtNom.getText().trim();
        prenom=edtPrenom.getText().trim();
        pay=edtPays.getText().trim();
        rue=edtNoRue.getText().trim();
        voie=edtVoie.getText().trim();
        code=edtCodeP.getText().trim();
        ville=edtVille.getText().trim();

        if(isStringOnlyAlphabet(nom))
            client.setNom(nom);
        else{
            if(nom.isEmpty()) aRemplacer+="Le nom est obligatoire\n";
            else aRemplacer+="Le nom ne contient pas des caractéres non alphabétiques\n";
        }
        if(isStringOnlyAlphabet(prenom))
            client.setPrenom(prenom);
        else{
            if(prenom.isEmpty()) aRemplacer+="Le Prénom est obligatoire\n";
            else aRemplacer+="Le prenom ne contient pas des caractéres non alphabétiques\n";
        }
        if(!rue.isEmpty()){
            adresse.setNo_rue(rue);
        }
        else{
            aRemplacer+="Le Rue est obligatoire\n";
        }
        if(!voie.isEmpty()){
            adresse.setVoie(voie);
        }
        else{
            aRemplacer+="Le Voie est obligatoire\n";
        }
        if(!code.isEmpty()){
            adresse.setCode_postal(code);
        }
        else{
            aRemplacer+="Le Code postal est obligatoire\n";
        }
        if(!ville.isEmpty()){
            adresse.setVille(ville);
        }
        else{
            aRemplacer+="La Ville est obligatoire\n";
        }
        if(isStringOnlyAlphabet(pay))
            adresse.setPays(pay);
        else{
            if(pay.isEmpty()) aRemplacer+="Le pay est obligatoire\n";
            else aRemplacer+="Le pay ne contient pas des caractéres non alphabétiques\n";
        }
    }
    public void setVue(vueModifClient vueModifClient, AnchorPane anchor, DaoFactory dao, TableView<Client> tab) {
        this.vue=vueModifClient;
        this.anchor=anchor;
        this.dao=dao;
        this.client=tab.getSelectionModel().getSelectedItem();
        this.adresse=this.client.getAdresse();
        this.tab=tab;
        initChamps();
    }
    private void unblurStage(){
        BoxBlur blur=new BoxBlur(0,0,0);
        anchor.setEffect(blur);
    }
    @FXML
    void fermerDialog(ActionEvent event) throws SQLException, ClassNotFoundException {
        unblurStage();
        this.tab.getItems().clear();
        if(tab!=null && dao!=null)
            this.tab.getItems().addAll(dao.getClientDAO().findAll());
        this.vue.close();
    }
    private void initChamps() {
        if(client!=null){
            edtCodeP.setText(client.getAdresse().getCode_postal());
            edtNom.setText(client.getNom());
            edtNoRue.setText(client.getAdresse().getNo_rue());
            edtPays.setText(client.getAdresse().getPays());
            edtPrenom.setText(client.getPrenom());
            edtVille.setText(client.getAdresse().getVille());
            edtVoie.setText(client.getAdresse().getVoie());
        }
    }
    private Alert makeAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
}
