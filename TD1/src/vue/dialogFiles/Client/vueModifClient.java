package vue.dialogFiles.Client;

import controlleur.Client.CtrlAjoutClient;
import controlleur.Client.CtrlModifClient;
import daofactory.DaoFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modele.metier.Client;

import java.io.IOException;
import java.net.URL;

public class vueModifClient extends Stage {
    public vueModifClient(AnchorPane anchor, DaoFactory dao, TableView<Client> tab) throws IOException {
        final URL fxmlURL=getClass().getResource("../../fxmlfiles/Client/modifClient.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        final Parent node = (Parent)fxmlLoader.load();
        Scene scene = new Scene(node);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        CtrlModifClient controleur = fxmlLoader.getController();
        controleur.setVue(this,anchor,dao,tab);
        BoxBlur blur=new BoxBlur(5,5,5);
        anchor.setEffect(blur);
        this.setScene(scene);
        this.show();
    }
}
