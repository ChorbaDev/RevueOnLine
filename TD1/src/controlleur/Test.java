package controlleur;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Test extends Stage {
    public Test() throws IOException {
        final URL fxmlURL=getClass().getResource("../vue/fxmlfiles/test.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        final Parent node = (Parent)fxmlLoader.load();
        Scene scene = new Scene(node);
        this.initModality(Modality.APPLICATION_MODAL);
        CtrlTest controleur = fxmlLoader.getController();
        controleur.setVue(this);
        this.setScene(scene);
        this.show();
    }
}
