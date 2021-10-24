package controlleur.Revue;

import dao.Persistance;
import daofactory.DaoFactory;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.beans.value.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import vue.dialogFiles.Revue.*;
import java.io.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.metier.Revue;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CtrlAfficheRevue implements Initializable, ChangeListener<Revue> {
    @FXML private TableView<Revue> listeRevue;
    @FXML private TableColumn<Revue, String> colDescp;
    @FXML private TableColumn<Revue, Integer> colID;
    @FXML private TableColumn<Revue, Integer> colIDP;
    @FXML private TableColumn<Revue, Double> colTarif;
    @FXML private TableColumn<Revue, String> colTitre;
    @FXML private ImageView imgVisuel;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private AnchorPane anchor;
    @FXML private  TextField recherche;
    private DaoFactory dao;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String path;
    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void clickAjouter(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        vueAjoutRevue ajoutRevue= new vueAjoutRevue(anchor,dao,listeRevue);
    }

    @FXML
    void clickModifier(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        vueModifRevue modifRevue= new vueModifRevue(anchor,dao,listeRevue);
    }
    @FXML
    void retourAccueil(ActionEvent event) throws IOException {
        path="../../vue/fxmlfiles/Accueil.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        root = loader.getRoot();
        basculeScene(event);
    }
    @FXML
    void clickSupprimer(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        Alert alert=makeAlert
                ("Confirmation",
                        "Est-ce-que vous ete sur de supprimer cette revue?",
                        "",
                        Alert.AlertType.CONFIRMATION);
        if(alert.showAndWait().get()==ButtonType.OK){
            dao.getRevueDAO().delete(listeRevue.getSelectionModel().getSelectedItem());
            refreshListe();
            initImg();
        }
    }

    /**
     * @param title
     * @param header
     * @param content
     * @param type
     * @return une alerte personnaliser à l'aide des paramétres
     */
    private Alert makeAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
    private void setColonnes() {
        colID.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Revue, String>("titre"));
        colDescp.setCellValueFactory(new PropertyValueFactory<Revue, String>("description"));
        colTarif.setCellValueFactory(new PropertyValueFactory<Revue, Double>("tarif_numero"));
        colIDP.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id_p"));
        listeRevue.getColumns().setAll(colID,colTitre,colDescp,colTarif,colIDP);
    }

    /**
     * nous permetre de basculer entre les scenes
     * @param e
     */
    public void basculeScene(ActionEvent e)
    {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * initialiser les champs a l'ouverture du page ou aprés une opération
     */
    private void initChamps() {
        initImg();
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
    }

    /**
     * initialiser l'image view par un placeholder (empty.jpg)
     */
    private void initImg() {
        File file = new File("TD1/src/vue/images/empty.jpg");
        Image image = new Image(file.toURI().toString());
        imgVisuel.setImage(image);
    }

    /**
     * modifier le l'image view si on séléctionne un revue
     * si le revue admet une image -> on ajoute l'image
     * sinon on met un placeholder
     * @throws IOException
     */
    private void setVisuel() throws IOException {
        Revue revue=listeRevue.getSelectionModel().getSelectedItem();
        if(revue.getVisuelImg()!=null)
            imgVisuel.setImage(revue.getVisuelImg());
        else initImg();
    }
    @Override
    public void changed(ObservableValue<? extends Revue> observableValue, Revue oldValue, Revue newValue) {
        this.btnSupprimer.setDisable(newValue == null);
        this.btnModifier.setDisable(newValue == null);
        if(newValue!=null) {
            try {
                setVisuel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColonnes();
        initChamps();
        this.listeRevue.getSelectionModel().selectedItemProperty().addListener(this);
        filter();
    }

    /**
     * filter
     */
    private void filter() {
        FilteredList<Revue> filteredData = new FilteredList<>(listeRevue.getItems(), b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rev -> {
                // If filter text is empty, display all Revues.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every Revue with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (rev.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (rev.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (String.valueOf(rev.getTarif_numero()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Revue> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(listeRevue.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            listeRevue.setItems(sortedData);

        });
    }

    /**
     * permet de récupérer la persistance choisi dans la page d'accueil
     * @param persistance
     * @throws SQLException
     * @throws IOException
     */
    public void getInfos(Persistance persistance) throws SQLException, IOException, ClassNotFoundException {
        try {
            dao = DaoFactory.getDAOFactory(persistance);
            refreshListe();
        } catch (SQLException | IOException | RuntimeException e) {
            dao = DaoFactory.getDAOFactory(Persistance.ListeMemoire);
            refreshListe();
        }
    }

    /**
     * Permet de remplir la liste des revues apres une supprission de données
     * @throws SQLException
     * @throws IOException
     */
    public void refreshListe() throws SQLException, IOException, ClassNotFoundException {
        if(listeRevue!=null){
            this.listeRevue.getItems().clear();
            this.listeRevue.getItems().addAll(dao.getRevueDAO().findAll());
        }
    }
}
