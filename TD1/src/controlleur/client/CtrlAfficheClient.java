package controlleur.client;

import controlleur.commun.CommunEntreAffiche;
import controlleur.commun.CommunStaticMethods;
import dao.Persistance;
import daofactory.DaoFactory;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.metier.Adresse;
import modele.metier.Client;
import process.ProcessAdresse;
import vue.dialogFiles.DialogMAJ;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CtrlAfficheClient implements Initializable, ChangeListener<Client>, CommunEntreAffiche<Client> {
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableColumn<Client, Adresse> colAdresse;
    @FXML
    private TableColumn<Client, Integer> colID;
    @FXML
    private TableColumn<Client, String> colNom;
    @FXML
    private TableColumn<Client, String> colPrenom;
    //
    @FXML
    private TableView<Client> listeClient;
    @FXML
    private TextField recherche;
    private DaoFactory dao;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String path;

    @FXML
    public void clickAjouter(ActionEvent event) throws IOException, SQLException {
        URL fxmlURL = getClass().getResource("../../vue/fxmlfiles/Client/createClient.fxml");
        DialogMAJ<CtrlAjoutClient> ajoutClient = new DialogMAJ(anchor, dao, listeClient, fxmlURL);
    }

    @FXML
    public void clickModifier(ActionEvent event) throws IOException, SQLException {
        URL fxmlURL = getClass().getResource("../../vue/fxmlfiles/Client/modifClient.fxml");
        DialogMAJ<CtrlModifClient> ajoutClient = new DialogMAJ(anchor, dao, listeClient, fxmlURL);
    }

    @FXML
    public void clickSupprimer(ActionEvent event) throws SQLException, IOException {
        Alert alert = makeAlert
                ("Confirmation",
                        "Est-ce-que vous ete sur de supprimer cette client?",
                        "",
                        Alert.AlertType.CONFIRMATION);
        if (alert.showAndWait().get() == ButtonType.OK) {
            dao.getClientDAO().delete(listeClient.getSelectionModel().getSelectedItem());
            refreshListe();
        }
    }

    @FXML
    void importeCSV(ActionEvent event) throws IOException, SQLException {
        FileChooser fc = new FileChooser();
        String err = "";
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("CSV files(*.csv)", "*.CSV");
        fc.getExtensionFilters().addAll(ext1);
        File fichierSelect = fc.showOpenDialog(null);
        BufferedReader reader = null;
        if (fichierSelect != null) {
            String line = "";
            reader = new BufferedReader(new FileReader(fichierSelect));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                if (row.length == 7) {
                    Adresse ad = formatAdresse(row[2], row[3], row[4], row[5], row[6]);
                    Client cl = new Client(row[0], row[1], ad);
                    if (nonDoublons(cl))
                        dao.getClientDAO().create(cl);
                    else {
                        err += cl.getNom() + " existe déja\n";
                    }
                }
            }
            if (!err.isEmpty()) {
                Alert alert = CommunStaticMethods.makeAlert
                        ("Attention!",
                                "Clients existent déja",
                                err,
                                Alert.AlertType.WARNING);
                alert.showAndWait();
            }
            reader.close();
            refreshListe();
        }
    }

    private Adresse formatAdresse(String numRue, String voie, String code, String ville, String pays) {
        Adresse adresse = new Adresse(numRue, voie, code, ville, pays);
        ProcessAdresse pa = new ProcessAdresse();
        pa.normalizeAdresse(adresse);
        return adresse;
    }

    private boolean nonDoublons(Client client) throws SQLException {
        ArrayList<Client> list = dao.getClientDAO().findAll();
        for (Client cl : list) {
            if (cl.equalsTout(client))
                return false;
        }
        return true;
    }


    @FXML
    public void retourAccueil(ActionEvent event) throws IOException {
        path = "../../vue/fxmlfiles/Accueil.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        root = loader.getRoot();
        basculeScene(event);
    }

    private Alert makeAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    public void basculeScene(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColonnes();
        initChamps();
        this.listeClient.getSelectionModel().selectedItemProperty().addListener(this);
        filter();
    }

    public void filter() {
        FilteredList<Client> filteredData;
        try {
            filteredData = new FilteredList<>((ObservableList<Client>) dao.getClientDAO().findAll(), b -> true);
        } catch (Exception e) {
            filteredData = new FilteredList<Client>(listeClient.getItems(), b -> true);
            e.getMessage();
        }
        FilteredList<Client> finalFilteredData = filteredData;
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            finalFilteredData.setPredicate(cl -> {
                // If filter text is empty, display all Clients.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every Client with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (cl.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (cl.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (cl.getAdresse().toString().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Client> sortedData = new SortedList<>(finalFilteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(listeClient.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            try {
                refreshListe();
            } catch (Exception e) {
                e.getMessage();
            }
            listeClient.getItems().setAll(sortedData);

        });
    }

    public void initChamps() {
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
    }

    public void setColonnes() {
        colID.setCellValueFactory(new PropertyValueFactory<Client, Integer>("cle"));
        colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Client, Adresse>("adresse"));
        listeClient.getColumns().setAll(colID, colNom, colPrenom, colAdresse);
    }

    @Override
    public void changed(ObservableValue<? extends Client> observableValue, Client oldValue, Client newValue) {
        this.btnSupprimer.setDisable(newValue == null);
        this.btnModifier.setDisable(newValue == null);
    }

    public void getInfos(Persistance persistance) throws SQLException, IOException {
        try {
            dao = DaoFactory.getDAOFactory(persistance);
            refreshListe();
        } catch (SQLException | IOException | RuntimeException e) {
            dao = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
            refreshListe();
        }
    }

    public void refreshListe() throws SQLException, IOException {
        if (listeClient != null) {
            this.listeClient.getItems().clear();
            listeClient.getItems().addAll(dao.getClientDAO().findAll());
        }
    }

    public void exportCSV() throws IOException {
        Writer writer = null;
        try {
            String directoryPath="C:\\RevueOnlineClients";
            File f=new File(directoryPath);
            f.mkdir();
            File file = new File("C:\\RevueOnlineClients\\Clients.csv.");
            writer = new BufferedWriter(new FileWriter(file));
            for (Client client : listeClient.getItems()) {
                String text = client.getNom() + ";" + client.getPrenom() + ";" + client.getAdresse().getNo_rue() +";" +client.getAdresse().getVoie() +";" +client.getAdresse().getCode_postal() +";"+client.getAdresse().getVille()+";"+client.getAdresse().getPays() +"\n";
                writer.write(text);
            }
            Alert alert = CommunStaticMethods.makeAlert
                    ("Création avec succès",
                            "Clients sont stockées sont une ficher CSV",
                            "Fichier nommer Clients.csv dans votre c:",
                            Alert.AlertType.INFORMATION);
            alert.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            writer.flush();
            writer.close();
        }
    }
}
