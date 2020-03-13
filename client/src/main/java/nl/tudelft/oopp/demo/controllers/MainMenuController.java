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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;


public class MainMenuController implements Initializable {
    private static String id;

    ServerCommunication con = new ServerCommunication();

    @FXML
    private ScrollPane scene1;

    @FXML
    private AnchorPane pane1;

    public static String getId() {
        return id;
    }

    public void CampusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void goToMenuReservation(Event event, String buildingNumber) throws IOException {
        id = buildingNumber;

        Stage stage1 = (Stage) scene1.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainReservationMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Buildings> buildingsList = new ArrayList<>();
        try {
            buildingsList = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        int capsCount = 0;
//        Time open = new Time(6, 0, 0);
//        Time close = new Time(21, 0, 0);
//        Buildings k = new Buildings();
//        k.setName("Civil Engineering and Geosciences");
//        k.setBuilding_number(28);
//        k.setNumber_of_bikes(2);
//        k.setNumber_of_rooms(5);
//        k.setClosing_hours(close);
//        k.setOpening_hours(open);
//
//        buildingsList.add(k);
//        buildingsList.add(k);
//        buildingsList.add(k);
//        buildingsList.add(k);

        List<String> imageBuildingsList = new ArrayList<>();

        imageBuildingsList.add("https://iamap.tudelft.nl/wp-content/uploads/2017/10/34_3ME_V1-900x601.jpg");
        imageBuildingsList.add("https://iamap.tudelft.nl/wp-content/uploads/2017/10/36_EWI_V1_b-900x601.jpg");

        for (Buildings e : buildingsList) {
            String buildingName = "";
            for (Character a : e.getName().toCharArray()) {
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
            if (i == 0) {
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + e.getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(72);
                textName.setLayoutY(14);
                textName.setFont(Font.font("System", 20));
                textName.setId("A");

                pane1.getChildren().add(textID);
                textID.setLayoutX(98);
                textID.setLayoutY(41);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(imageBuildingsList.get(i));
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(4);
                imageBuil.setY(74);
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event , e.getBuilding_number()+"");
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
            }
            if (i == 1) {
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + e.getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(377);
                textName.setLayoutY(14);
                textName.setFont(Font.font("System", 20));
                textName.setId("B");

                pane1.getChildren().add(textID);
                textID.setLayoutX(397);
                textID.setLayoutY(41);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(imageBuildingsList.get(i));
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(302);
                imageBuil.setY(74);
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, ""+e.getBuilding_number());
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
            }
            if (i == 2) {
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + e.getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(682);
                textName.setLayoutY(14);
                textName.setFont(Font.font("System", 20));
                textName.setId("C");

                pane1.getChildren().add(textID);
                textID.setLayoutX(696);
                textID.setLayoutY(41);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(imageBuildingsList.get(i));
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(601);
                imageBuil.setY(74);
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, ""+e.getBuilding_number());
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
                break;
            }
            i++;
        }
        if (i < 2) {
            System.out.println("Not here");
            return;
        }
        Label last = null;

        for (int j = i; j < buildingsList.size(); j++) {
            String buildingName = "";
            for (Character a : buildingsList.get(j).getName().toCharArray()) {
                if (Character.isUpperCase(a)) {
                    capsCount++;
                    buildingName = buildingName + a;
                }
            }
            if (capsCount == 1 || capsCount == 0) {
                buildingName = buildingsList.get(j).getName();
            } else {
                buildingName = "Faculty of " + buildingName;
            }

            for (Node e : pane1.getChildren()) {
                if (e instanceof Label && e.getId() != null) {
                    last = (Label) e;
                }
            }
            if (last.getId().contains("A")) {
                System.out.println("A");
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + buildingsList.get(j).getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(387);
                textName.setLayoutY(last.getLayoutY());
                textName.setFont(Font.font("System", 20));
                textName.setId("B");

                pane1.getChildren().add(textID);
                textID.setLayoutX(402);
                textID.setLayoutY(last.getLayoutY() + 27);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(imageBuildingsList.get(j));
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(302);
                imageBuil.setY(last.getLayoutY() + 60);
                String getId = buildingsList.get(j).getBuilding_number()+"";
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, getId);
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
            }
            if (last.getId().contains("B")) {
                System.out.println("B");
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + buildingsList.get(j).getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(682);
                textName.setLayoutY(last.getLayoutY());
                textName.setFont(Font.font("System", 20));
                textName.setId("C");

                pane1.getChildren().add(textID);
                textID.setLayoutX(696);
                textID.setLayoutY(last.getLayoutY() + 27);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(imageBuildingsList.get(j));
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(601);
                imageBuil.setY(last.getLayoutY() + 60);
                String getId = buildingsList.get(j).getBuilding_number()+"";
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, getId);
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
            }
            if (last.getId().contains("C")) {
                System.out.println("C");
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + buildingsList.get(j).getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(82);
                textName.setLayoutY(last.getLayoutY() + 268);
                textName.setFont(Font.font("System", 20));
                textName.setId("A");

                pane1.getChildren().add(textID);
                textID.setLayoutX(98);
                textID.setLayoutY(last.getLayoutY() + 295);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(imageBuildingsList.get(j));
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(4);
                imageBuil.setY(last.getLayoutY() + 60 + 268);
                String getId = buildingsList.get(j).getBuilding_number()+"";
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, getId);
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
            }
        }
    }
}
