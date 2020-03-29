package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.entities.Users;

public class UserPage {

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

    public void initialize() {
        netIdLabel.setText(Users.user.getNetid());
        roleLabel.setText(Users.user.getRole());
    }

    public void changePassword(Event e) {
        changePasswordLabel.setText("changed pwd (TO BE IMPLEMENTED)");
    }

    public void openSchedule(Event e) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/UserSchedule.fxml", mainScreen);
    }

    public void campusMap(Event e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * @param event
     * @throws IOException
     */
    public void goBack(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage stage1 = (Stage) scene1.getScene().getWindow();
        stage1.close();
    }
}
