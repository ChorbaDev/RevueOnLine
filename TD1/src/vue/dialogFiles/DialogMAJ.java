package vue.dialogFiles;

import controlleur.client.CtrlAjoutClient;
import controlleur.client.CtrlModifClient;
import controlleur.abonnement.CtrlAjoutAbonnement;
import controlleur.abonnement.CtrlModifAbonnement;
import controlleur.commun.CommunStaticMethods;
import controlleur.Revue.CtrlAjoutRevue;
import controlleur.Revue.CtrlModifRevue;
import controlleur.periodicite.CtrlAjoutPeriodicite;
import controlleur.periodicite.CtrlModifPeriodicite;
import daofactory.DaoFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class DialogMAJ<C> extends Stage {
    public DialogMAJ(AnchorPane anchor, DaoFactory dao, TableView tab,URL fxmlURL) throws IOException, SQLException, ClassNotFoundException {
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        final Parent node = (Parent)fxmlLoader.load();
        Scene scene = new Scene(node);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        C cast = fxmlLoader.getController();
        if(cast instanceof CtrlAjoutClient){
            CtrlAjoutClient controleur=(CtrlAjoutClient) cast;
            controleur.setVue(this,anchor,dao,tab);
        }
        else if(cast instanceof CtrlModifClient){
            CtrlModifClient controleur=(CtrlModifClient) cast;
            controleur.setVue(this,anchor,dao,tab);
        }
        else if(cast instanceof CtrlModifRevue){
            CtrlModifRevue controleur=(CtrlModifRevue) cast;
            controleur.setVue(this,anchor,dao,tab);
        }
        else if (cast instanceof CtrlAjoutRevue){
            CtrlAjoutRevue controleur=(CtrlAjoutRevue) cast;
            controleur.setVue(this,anchor,dao,tab);
        }else if(cast instanceof CtrlAjoutPeriodicite){
            CtrlAjoutPeriodicite controleur=(CtrlAjoutPeriodicite) cast;
            controleur.setVue(this,anchor,dao,tab);
        }else if(cast instanceof CtrlModifPeriodicite){
            CtrlModifPeriodicite controleur=(CtrlModifPeriodicite) cast;
            controleur.setVue(this,anchor,dao,tab);
        }
        else if(cast instanceof CtrlAjoutAbonnement){
            CtrlAjoutAbonnement contoleur=(CtrlAjoutAbonnement) cast;
            contoleur.setVue(this,anchor,dao,tab);
        }else{
            CtrlModifAbonnement contoleur=(CtrlModifAbonnement) cast;
            contoleur.setVue(this,anchor,dao,tab);
        }


        CommunStaticMethods.blurStage(anchor,5,5,5);
        this.setScene(scene);
        this.show();
    }
}
