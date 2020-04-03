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

    /**
     * Method to get ID.
     * @return The id
     */
    public static String getId() {
        return id;
    }

    /**
     * Method to load admin view.
     * @param event Logging in as admin
     * @throws IOException Exception if can't find admin view scene
     */
    public void goToAdminAddSelection(ActionEvent event) throws IOException {

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/AddSelectionAdmin.fxml", mainScreen);
    }

    public void goToAdminAdd(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/AdminView.fxml", mainScreen);
    }

    /**
     * Method to load admin edit view.
     * @param event Clicking on edit.
     * @throws IOException Exception if can't find admin edit view scene
     */
    public void goToEditPage(ActionEvent event) throws IOException {

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/AdminEditView.fxml", mainScreen);
    }

    public void goToAdminHolidayAdd(ActionEvent event) throws IOException {

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/AdminHolidayAdd.fxml", mainScreen);
    }

    public void goBack(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainAdminScene.fxml", mainScreen);

    }
}
