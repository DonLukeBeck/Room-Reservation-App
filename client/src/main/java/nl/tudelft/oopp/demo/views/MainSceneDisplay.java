package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainSceneDisplay extends Application {

    /** Application launch.
     * @param args string parameter
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** FXML load start.
     * @param primaryStage first stage of application
     * @throws IOException Exception if can't find main scene
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Room Reservation App");
        primaryStage.getIcons().add(new Image("images/favicon.png"));
        primaryStage.show();

    }
}
