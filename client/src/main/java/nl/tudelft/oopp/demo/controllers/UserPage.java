package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.entities.Users;

public class UserPage {
    HelperController helper = new HelperController();

    @FXML
    private javafx.scene.control.Button scene1;
    @FXML
    private javafx.scene.control.Label netIdLabel;
    @FXML
    private javafx.scene.control.Label roleLabel;
    @FXML
    private javafx.scene.control.Label changePasswordLabel;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private Pane rightPane;



    public void initialize() {
        netIdLabel.setText(Users.user.getNetid());
        roleLabel.setText(Users.user.getRole());
    }

    public void paneExit(Event event) throws IOException {
        helper.exit(mainScreen);
    }

    public void paneLogOut(Event event) throws  IOException {
        helper.logOut(mainScreen);
    }

    public void paneUserProfile(Event event) throws IOException {
        helper.userProfile(mainScreen);
    }

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }



    public void changePassword(Event e) {
        changePasswordLabel.setText("changed pwd (TO BE IMPLEMENTED)");
    }

    public void openSchedule(Event e) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/UserSchedule.fxml", mainScreen);
    }

    /**
     * Method for campus map to pop up.
     * @param e Clicking on campus map button
     * @throws IOException Exception if can't find campus map scene
     */
    public void campusMap(Event e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("favicon.png"));
        stage.show();
    }

    /**
     * Method to go back to main menu.
     * @param event Clicking on go back
     * @throws IOException Exception if can't find main menu scene
     */
    public void goBack(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("favicon.png"));
        stage.show();

        Stage stage1 = (Stage) scene1.getScene().getWindow();
        stage1.close();
    }
}
