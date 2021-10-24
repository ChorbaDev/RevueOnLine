package controlleur.Revue;

import daofactory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import modele.metier.Periodicite;
import modele.metier.Revue;
import vue.dialogFiles.Revue.vueModifRevue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlModifRevue implements Initializable {
    @FXML private Button btnCreer;
    @FXML private ComboBox<Periodicite> comboPeriodicite;
    @FXML private TextArea edtDescription;
    @FXML private TextField edtTarif;
    @FXML private TextField edtTitre;
    @FXML private Label visuelPath;
    private vueModifRevue vue;
    private Image visuel;
    private AnchorPane anchor;
    private DaoFactory dao;
    private TableView<Revue> tab;
    private Revue revue;
    private void unblurStage(){
        BoxBlur blur=new BoxBlur(0,0,0);
        anchor.setEffect(blur);
    }
    public void fermeDialogue() throws SQLException, IOException {
        unblurStage();
        this.tab.getItems().clear();
        if(tab!=null && dao!=null)
            this.tab.getItems().addAll(dao.getRevueDAO().findAll());
        this.vue.close();
    }
    public void setVue(vueModifRevue vue, AnchorPane anchor, DaoFactory dao, TableView<Revue> tab) throws SQLException, IOException {
        this.vue=vue;
        this.anchor=anchor;
        this.dao=dao;
        this.tab=tab;
        this.revue=tab.getSelectionModel().getSelectedItem();
        this.visuel=revue.getVisuelImg();
        if(dao!=null)
            this.comboPeriodicite.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
        initChamps();
    }


    /**
     * @param title
     * @param header
     * @param content
     * @param type
     * @return alert créé a l'aide de ces paramètres
     */
    private Alert makeAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
    @FXML
    public void clickModif(ActionEvent event) {
        String aRemplacer="";
        Alert alert;
        try{
            revue.setTitre(edtTitre.getText().trim());
            revue.setDescription(edtDescription.getText().trim());
            revue.setTarif_numero(Double.parseDouble(edtTarif.getText()));
            revue.setVisuel(visuel);
            if(dao!=null){
                revue.setId_p(dao.getPeriodiciteDAO().getByLibelle(comboPeriodicite.getValue().getLibelle()).get(0).getCle());
                dao.getRevueDAO().update(revue);
            }
            aRemplacer=revue.toString();
            initChamps();
            alert=makeAlert
                    ("Modifiation avec succès",
                            "Cette revue a été modifié avec succès",
                            aRemplacer,
                            Alert.AlertType.INFORMATION);
            //revue=new Revue();
        }catch(Exception e){
            if((e instanceof RuntimeException) || (e instanceof ArithmeticException))
                aRemplacer=e.getMessage();
            if(e instanceof NumberFormatException)
                aRemplacer="Le tarif doit être numérique";
            if(e instanceof NullPointerException)
                aRemplacer="il faut choisir une périodicité";
            alert=makeAlert
                    ("Erreur lors de la saisie",
                            "Un ou plusieurs champs sont mal remplis.",
                            aRemplacer,
                            Alert.AlertType.ERROR);
            System.out.println(e.toString());
        }
        alert.showAndWait();
    }

    private void initChamps() throws IOException {
        if(revue!=null){
            initImg();
            edtTarif.setText(Double.toString(revue.getTarif_numero()));
            edtDescription.setText(revue.getDescription());
            edtTitre.setText(revue.getTitre());
            comboPeriodicite.getSelectionModel().select(revue.getId_p()-1);
        }
    }
    @FXML
    public void choisirUneImage(ActionEvent event){
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(ext1, ext2);
        File fichierSelect = fc.showOpenDialog(null);
        if (fichierSelect != null) {
            visuel=new Image(fichierSelect.toURI().toString());
            visuelPath.setText(fichierSelect.getPath());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void initImg(){
        visuel=revue.getVisuelImg();
        visuelPath.setText("");
    }
}
