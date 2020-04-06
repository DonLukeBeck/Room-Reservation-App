package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NoBikesController implements Initializable {
    HelperController helper = new HelperController();
    @FXML
    private Pane sidePane;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private Pane rightPane;

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }

    public void paneExit(Event event) throws IOException {
        helper.exit(mainScreen);
    }

    public void paneLogOut(Event event) throws IOException {
        helper.logOut(mainScreen);
    }

    public void paneUserProfile(Event event) throws IOException {
        helper.userProfile(mainScreen);
    }

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }

    public void openResources(Event event) throws IOException {
        helper.openResources();
    }

    /**
     * Method to go back to previous page.
     *
     * @param event Clicking on 'Go Back'
     * @throws IOException Exception if can't find main menu scene
     */
    public void goBack(Event event) throws IOException {
        MainMenuController.setFilter(false);
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainMenu.fxml", mainScreen);
    }

    /**
     * Method for campus map to pop up.
     *
     * @param event Clicking on 'Campus Map'
     * @throws IOException Exception if can't find campus map scene
     */
    public void campusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Method to initialize.
     *
     * @param location  Link to location
     * @param resources Resource Bundle
     */
    public void initialize(URL location, ResourceBundle resources) {
        addRole();
        helper.loadSidePane(sidePane);
    }
}
