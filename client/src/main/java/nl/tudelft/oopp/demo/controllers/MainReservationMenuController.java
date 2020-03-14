package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;


public class MainReservationMenuController implements Initializable {

    ServerCommunication con = new ServerCommunication();

    @FXML
    private Pane sidePane;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private javafx.scene.control.Button ReserveScene;

    public void CampusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void GoBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainMenu.fxml", mainScreen);
    }

    public void GoToRooms(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/RoomMenu.fxml", mainScreen);
    }

    public void GoToBikes(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/ReservationBike.fxml", mainScreen);
    }

    public void GoToFood(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/ReservationFood.fxml", mainScreen);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Buildings> buildings = new ArrayList<>();
        try {
            buildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int layoutY = 220;
        for (Buildings e : buildings) {
            int capsCount = 0;
            String buildingName = "";
            for (Character a : e.getName().toCharArray()) {
                if (a.equals('(')) {
                    break;
                }
                if (Character.isUpperCase(a)) {
                    capsCount++;
                    buildingName = buildingName + a;
                }
            }
            if (capsCount == 1 || capsCount == 0) {
                buildingName = e.getName();
            } else {
                buildingName = "Faculty of " + buildingName;
            }
            Label sideBuilding = new Label("-" + buildingName);
            sideBuilding.setLayoutX(56);
            sideBuilding.setLayoutY(layoutY + 28);
            sideBuilding.setFont(Font.font("System", FontWeight.BOLD, 14));
            sideBuilding.setTextFill(Color.valueOf("white"));
            layoutY = layoutY + 28;
            sidePane.getChildren().add(sideBuilding);
        }
    }
}
