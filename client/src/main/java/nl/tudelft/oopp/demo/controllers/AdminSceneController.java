package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AdminSceneController {
    private static String id;
    @FXML
    private javafx.scene.control.Button add;

    @FXML
    private AnchorPane mainScreen;

    public static String getId() {
        return id;
    }

    public void goToAdminAdd(ActionEvent event) throws IOException {

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/AdminView.fxml", mainScreen);
    }
}
