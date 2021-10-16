package controlleur;

//import com.jfoenix.controls.*;
import dao.Persistance;
import daofactory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import modele.metier.Periodicite;
import modele.metier.Revue;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlRevue implements Initializable {
    @FXML private Button btnCreer;
    @FXML private ComboBox<Periodicite> comboPeriodicite;
    @FXML private TextArea edtDescription;
    @FXML private TextField edtTarif;
    @FXML private TextField edtTitre;
    Revue revue;

    /**
     *
     * @param title
     * @param header
     * @param content
     * @param type
     * @return alert créé a l'aide de ces paramétre
     */
    private Alert makeAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    @FXML
    public void clickCreer(ActionEvent event) {
        String aRemplacer="";
        DaoFactory daof=DaoFactory.getDAOFactory(Persistance.ListeMemoire);
        Alert alert;
        try{
            revue.setTarif_numero(Double.parseDouble(edtTarif.getText()));
            revue.setTitre(edtTitre.getText());
            revue.setDescription(edtDescription.getText());
            revue.setId_p(daof.getPeriodiciteDAO().getByLibelle(comboPeriodicite.getValue().getLibelle()).get(0).getCle());
            daof.getRevueDAO().create(revue);
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
        edtTarif.setText("0");
        edtDescription.setText("");
        edtTitre.setText("");
        comboPeriodicite.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChamps();
        DaoFactory dao = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
        try {
            this.comboPeriodicite.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
            edtTarif.setText("0");
            revue=new Revue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
