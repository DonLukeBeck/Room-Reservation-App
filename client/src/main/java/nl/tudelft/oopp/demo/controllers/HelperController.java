package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
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

public class HelperController {
    ServerCommunication con = new ServerCommunication();

    /**
     * Method to get all timeslots.
     *
     * @return List of all timeslots
     */
    public String[] getAllTimeSlots() {
        String[] list = new String[48];
        int j = 0;
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                list[j] = "0" + i + ":00";
                j++;
                list[j] = "0" + i + ":30";
                j++;
            } else {
                list[j] = i + ":00";
                j++;
                list[j] = i + ":30";
                j++;
            }
        }
        return list;
    }

    /**
     * @param path
     * @param scene1
     * @throws IOException
     */
    public void loadNextScene(String path, AnchorPane scene1) throws IOException {
        Stage stage1 = (Stage) scene1.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource(path);
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage1.setScene(new Scene(root));
        stage1.setTitle("Room Reservation App");
        stage1.show();
    }

    /**
     * @param sidePane
     */
    public void loadSidePane(Pane sidePane) {
        List<Buildings> buildingsList = new ArrayList<>();
        int layoutY = 220;
        try {
            buildingsList = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Buildings e : buildingsList) {
            int capsCount = 0;
            int changeInPosition = 0;
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
                changeInPosition = 40;
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
