package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            URL fxmlURL = getClass().getResource("../vue/fxmlfiles/Accueil.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            File file = new File("TD1/src/vue/images/logo.png");
            Node root = fxmlLoader.load();
            Scene scene = new Scene((Parent) root);
            primaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("../vue/cssfiles/guiAccueil.css").toExternalForm());
            primaryStage.getIcons().add(new Image(file.toURI().toString()));
            primaryStage.setTitle("RevuesOnLine");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
