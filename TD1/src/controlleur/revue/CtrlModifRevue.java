package controlleur.revue;

import controlleur.commun.CommunEntreMAJ;
import controlleur.commun.CommunStaticMethods;
import daofactory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import modele.metier.Client;
import modele.metier.Periodicite;
import modele.metier.Revue;
import vue.dialogFiles.DialogMAJ;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CtrlModifRevue implements Initializable, CommunEntreMAJ {
    @FXML
    private ComboBox<Periodicite> comboPeriodicite;
    @FXML
    private TextArea edtDescription;
    @FXML
    private TextField edtTarif;
    @FXML
    private TextField edtTitre;
    @FXML
    private Label visuelPath;
    @FXML
    private Label nbCaracteres;
    final int MAX_CHARS = 400;
    private DialogMAJ<CtrlModifRevue> vue;
    private Image visuel;
    private AnchorPane anchor;
    private DaoFactory dao;
    private TableView<Revue> tab;
    private Revue revue;
    private String aRemplacer;
    public void fermeDialog() throws SQLException, IOException {
        CommunStaticMethods.blurStage(anchor,0,0,0);
        this.tab.getItems().clear();
        if (tab != null && dao != null)
            this.tab.getItems().addAll(dao.getRevueDAO().findAll());
        this.vue.close();
    }

    public void setVue(DialogMAJ vue, AnchorPane anchor, DaoFactory dao, TableView tab) throws SQLException, IOException {
        this.vue = vue;
        this.anchor = anchor;
        this.dao = dao;
        this.tab = tab;
        this.revue = (Revue) tab.getSelectionModel().getSelectedItem();
        this.visuel = revue.getVisuelImg();
        if (dao != null)
            this.comboPeriodicite.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
        initChamps();
    }

    private Revue set() throws SQLException {
        String titre,description;
        double tarif;
        Revue r=new Revue(revue.getId());
        titre=edtTitre.getText().trim();
        description=edtDescription.getText().trim();
        if(titre.isEmpty()) aRemplacer+="Le titre ne doit pas etre vide\n";
        else r.setTitre(titre);
        if(description.isEmpty()) aRemplacer+="La description ne doit pas etre vide\n";
        else r.setDescription(description);
        if(!CommunStaticMethods.isNumeric(edtTarif.getText())) aRemplacer+="Vérifier la format du tarif\n";
        else{
            tarif=Double.parseDouble(edtTarif.getText());
            if(tarif==0) aRemplacer+="Le tarif est strictement positif\n";
            else r.setTarif_numero(tarif);
        }
        r.setVisuel(visuel);
        if(comboPeriodicite.getValue()==null) aRemplacer+="Choissisez une périodicité\n";
        else r.setId_p(dao.getPeriodiciteDAO().getByLibelle(comboPeriodicite.getValue().getLibelle()).get(0).getCle());
        return r;
    }
    public void setObjectForMetier() throws SQLException {
        revue=set();
    }

    @FXML
    public void clickMAJ() throws SQLException, IOException {
        Alert alert;
        aRemplacer="";
        setObjectForMetier();
        if(aRemplacer.isEmpty()){
            if(nonDoublons()){
                aRemplacer=revue.toString();
                dao.getRevueDAO().update(revue);
                alert=CommunStaticMethods.makeAlert
                        ("Modifiation avec succès",
                                "Cette revue a été modifié avec succès",
                                aRemplacer,
                                Alert.AlertType.INFORMATION);
                initChamps();
            }
            else{
                aRemplacer="Revue existe déja";
                alert=CommunStaticMethods.makeAlert
                        ("Attention!",
                                "Probléme de modification",
                                aRemplacer,
                                Alert.AlertType.WARNING);
            }
        }else{
            alert = CommunStaticMethods.makeAlert
                    ("Erreur lors de la modification",
                            "Un ou plusieurs champs sont mal remplis.",
                            aRemplacer,
                            Alert.AlertType.ERROR);
        }
        alert.showAndWait();
    }

    public void initChamps() {
        if (revue != null) {
            initImg();
            edtTarif.setText(Double.toString(revue.getTarif_numero()));
            edtDescription.setText(revue.getDescription());
            edtTitre.setText(revue.getTitre());
            comboPeriodicite.getSelectionModel().select(revue.getId_p() - 1);
            nbCaracteres.setText(Integer.toString(edtDescription.getText().length()));
        }
    }

    @Override
    public boolean nonDoublons() throws SQLException, IOException {
        ArrayList<Revue> list = dao.getRevueDAO().findAll();
        for (Revue cl : list) {
            if (cl.equalsTout(revue))
                return false;
        }
        return true;
    }

    @FXML
    public void choisirUneImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(ext1, ext2);
        File fichierSelect = fc.showOpenDialog(null);
        if (fichierSelect != null) {
            visuel = new Image(fichierSelect.toURI().toString());
            visuelPath.setText(fichierSelect.getPath());
        }
    }

    private void initImg() {
        visuel = revue.getVisuelImg();
        visuelPath.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        incNbCaracteres();
    }

    private void incNbCaracteres() {
        edtDescription.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= MAX_CHARS ? change : null)
        );
        edtDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            nbCaracteres.setText(Integer.toString(newValue.length()));
        });
    }
}
