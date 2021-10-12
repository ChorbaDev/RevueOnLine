package javafxtd4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            URL fxmlURL=getClass().getResource("vueRevue.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Scene scene = new Scene((VBox) root);
          //  scene.getStylesheets().add(getClass().getResource("guiRevue.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des revues");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
