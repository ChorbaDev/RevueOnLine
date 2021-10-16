package controlleur;

import dao.Persistance;
import daofactory.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.metier.Periodicite;

import java.net.URL;
import java.util.ResourceBundle;

public class CtrlAjoutPeriodicite implements Initializable {
    @FXML
    private Button btnCreer;

    @FXML
    private TextField edtPeriodicite;
    Periodicite periodicite;

    /**
     * @param title
     * @param header
     * @param content
     * @param type
     * @return alert créé a l'aide de ces paramètres
     */
    private Alert makeAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    @FXML
    void clickCreer(ActionEvent event) {
        String aRemplacer = "";
        //à changer une fois la gestion de solution de persistance faite
        DaoFactory daof = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
        Alert alert;
        try {
            periodicite.setLibelle(edtPeriodicite.getText());
            daof.getPeriodiciteDAO().create(periodicite);
            aRemplacer = periodicite.toString();
            initChamps();
            alert = makeAlert
                    ("Ajout avec succès",
                            "Cette periodicite a été ajouté avec succès",
                            aRemplacer,
                            Alert.AlertType.INFORMATION);
            periodicite = new Periodicite();
        } catch (Exception e) {
            if ((e instanceof RuntimeException) || (e instanceof ArithmeticException))
                aRemplacer = e.getMessage();
            alert = makeAlert
                    ("Erreur lors de la saisie",
                            "Un ou plusieurs champs sont mal remplis.",
                            aRemplacer,
                            Alert.AlertType.ERROR);
            System.out.println(e.toString());
        }
        alert.showAndWait();
    }

    private void initChamps() {
        edtPeriodicite.setText("");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChamps();
        //à revoir avec la solution de persistance
        DaoFactory dao = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
        periodicite = new Periodicite();

    }
}
