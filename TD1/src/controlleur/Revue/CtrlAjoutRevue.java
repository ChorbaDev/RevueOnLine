package controlleur.Revue;

//import com.jfoenix.controls.*;
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
import vue.dialogFiles.Revue.vueAjoutRevue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlAjoutRevue implements Initializable {
    @FXML private ComboBox<Periodicite> comboPeriodicite;
    @FXML private TextArea edtDescription;
    @FXML private TextField edtTarif;
    @FXML private TextField edtTitre;
    @FXML private Label visuelPath;
    @FXML private Label nbCaracteres;
    final int MAX_CHARS = 400 ;
    private AnchorPane anchor;
    private vueAjoutRevue vue;
    private Image visuel;
    private Revue revue;
    private TableView<Revue> tab;
    private DaoFactory dao;

    private void unblurStage(){
        BoxBlur blur=new BoxBlur(0,0,0);
        anchor.setEffect(blur);
    }
    public void fermeDialogue() throws SQLException, IOException, ClassNotFoundException {
        unblurStage();
        this.tab.getItems().clear();
        if(tab!=null && dao!=null)
        this.tab.getItems().addAll(dao.getRevueDAO().findAll());
        this.vue.close();
    }
    public void setVue(vueAjoutRevue vue, AnchorPane anchor,DaoFactory dao,TableView<Revue> tab) throws SQLException, IOException, ClassNotFoundException {
        this.vue=vue;
        this.anchor=anchor;
        this.dao=dao;
        this.tab=tab;
        if(dao!=null)
            this.comboPeriodicite.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
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
    private void setRevue() throws SQLException, IOException, ClassNotFoundException {
        revue.setTitre(edtTitre.getText().trim());
        revue.setDescription(edtDescription.getText().trim());
        revue.setTarif_numero(Double.parseDouble(edtTarif.getText()));
        revue.setVisuel(visuel);
        if(dao!=null){
            revue.setId_p(dao.getPeriodiciteDAO().getByLibelle(comboPeriodicite.getValue().getLibelle()).get(0).getCle());
            dao.getRevueDAO().create(revue);
        }
    }
    @FXML
    public void clickCreer(ActionEvent event) {
        String aRemplacer="";
        Alert alert;
        try{
            setRevue();
            aRemplacer=revue.toString();
            initChamps();
            alert=makeAlert
                    ("Ajout avec succès",
                            "Cette revue a été ajouté avec succès",
                            aRemplacer,
                            Alert.AlertType.INFORMATION);
            revue=new Revue();
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

    private void initChamps() {
        initImg();
        edtTarif.setText("0");
        edtDescription.setText("");
        edtTitre.setText("");
        comboPeriodicite.getSelectionModel().clearSelection();
    }

    /**
     * selectionner une image PNG ou JPG depuis votre pc
     * @param event
     */
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
        initChamps();
        revue=new Revue();
        incNbCaracteres();
    }

    private void incNbCaracteres() {
        edtDescription.setTextFormatter(new TextFormatter<String>(change ->
        change.getControlNewText().length() <= MAX_CHARS ? change : null)
        );
        edtDescription.textProperty().addListener((observable, oldValue, newValue) ->{
            nbCaracteres.setText(Integer.toString(newValue.length()));
        });
    }

    /**
     * initialiser l'image par le placeholder
     */
    private void initImg() {
        File file=new File("TD1/src/vue/images/empty.jpg");
        visuel=new Image(file.getAbsolutePath());
        visuelPath.setText("");
    }

}
