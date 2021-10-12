package javafxtd4;

import com.jfoenix.controls.*;
import dao.Persistance;
import daofactory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import metier.Periodicite;
import metier.Revue;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlRevue implements Initializable {
    @FXML private JFXButton btnCreer;
    @FXML private JFXComboBox<Periodicite> comboPeriodicite;
    @FXML private TextArea edtDescription;
    @FXML private TextField edtTarif;
    @FXML private TextField edtTitre;
    @FXML private Label lblExp;
    Revue revue;
    @FXML
    public void clickCreer(ActionEvent event) {
        String aRemplacer="";
        DaoFactory daof=DaoFactory.getDAOFactory(Persistance.ListeMemoire);
        try{
            lblExp.setTextFill(Color.BLACK);
            revue.setTarif_numero(Double.parseDouble(edtTarif.getText()));
            revue.setTitre(edtTitre.getText());
            revue.setDescription(edtDescription.getText());
            revue.setId_p(daof.getPeriodiciteDAO().getByLibelle(comboPeriodicite.getValue().getLibelle()).get(0).getCle());
            aRemplacer=revue.toString();
            daof.getRevueDAO().create(revue);
        }catch(Exception e){
            lblExp.setTextFill(Color.RED);
            if((e instanceof RuntimeException) || (e instanceof ArithmeticException))
                aRemplacer=e.getMessage();
            if(e instanceof NumberFormatException)
                aRemplacer="Le tarif doit être numérique";
            if(e instanceof NullPointerException)
                aRemplacer="il faut choisir une périodicité";
            System.out.println(e.toString());
        }

        lblExp.setText(aRemplacer);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DaoFactory dao = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
        try {
            this.comboPeriodicite.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
            lblExp.setText("");
            edtTarif.setText("0");
            revue=new Revue(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
