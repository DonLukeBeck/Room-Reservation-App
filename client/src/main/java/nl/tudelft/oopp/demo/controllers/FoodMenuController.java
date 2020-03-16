package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FoodMenuController {

    @FXML
    private javafx.scene.control.ScrollPane scene1;
    @FXML
    private javafx.scene.control.Button reserveScene;

    /**
     * Method to pop up campus map.
     * @param event Clicking on 'campus map'
     * @throws IOException
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
     * Method to go back to previous page.
     * @param event Clicking on 'Go Back"
     * @throws IOException
     */
    public void goBack(Event event) throws IOException {
        Stage stage1 = (Stage) reserveScene.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainReservationMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
