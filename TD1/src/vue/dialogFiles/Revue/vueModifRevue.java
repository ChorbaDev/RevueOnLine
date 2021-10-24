package vue.dialogFiles.Revue;

import controlleur.Revue.CtrlModifRevue;
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
import modele.metier.Revue;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class vueModifRevue extends Stage {
    public vueModifRevue(AnchorPane anchor, DaoFactory dao, TableView<Revue> tab) throws IOException, SQLException, ClassNotFoundException {
        final URL fxmlURL=getClass().getResource("../../fxmlfiles/Revue/modifRevue.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        final Parent node = (Parent)fxmlLoader.load();
        Scene scene = new Scene(node);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        CtrlModifRevue controleur = fxmlLoader.getController();
        controleur.setVue(this,anchor,dao,tab);
        BoxBlur blur=new BoxBlur(5,5,5);
        anchor.setEffect(blur);
        this.setScene(scene);
        this.show();
    }
}
