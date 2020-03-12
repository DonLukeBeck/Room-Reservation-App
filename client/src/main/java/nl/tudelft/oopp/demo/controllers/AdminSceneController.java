package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminSceneController {
    private static String id;
    @FXML
    private javafx.scene.control.Button add;

    public static String getId() {
        return id;
    }

    public void goToAdminAdd(ActionEvent event) throws IOException {

        Stage stage1 = (Stage) add.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/AdminView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
