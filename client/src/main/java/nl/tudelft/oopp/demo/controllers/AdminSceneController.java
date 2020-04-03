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

    /**
     * Method to go to admin holiday add scene.
     * @param event Admin clicking add holiday
     * @throws IOException Exception if can't find admin holiday add scene
     */
    public void goToAdminHolidayAdd(ActionEvent event) throws IOException {

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/AdminHolidayAdd.fxml", mainScreen);
    }

    /**
     * Method to go back to main admin scene.
     * @param event Clicking go back button
     * @throws IOException Exception if can't find main admin scene
     */
    public void goBack(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainAdminScene.fxml", mainScreen);

    }

    public void goToAdminDelete(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/Admin DeleteView.fxml", mainScreen);
    }
}
