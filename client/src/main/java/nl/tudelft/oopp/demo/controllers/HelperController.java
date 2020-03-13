package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;



public class HelperController {
    ServerCommunication con = new ServerCommunication();

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
