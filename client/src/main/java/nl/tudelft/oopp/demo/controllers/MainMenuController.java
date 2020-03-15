package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.Rooms;


public class MainMenuController implements Initializable {
    private static String id;

    ServerCommunication con = new ServerCommunication();

    @FXML
    private ScrollPane scene1;

    @FXML
    private AnchorPane pane1;

    @FXML
    private Pane sidePane;

    @FXML
    private AnchorPane mainScreen;

    @FXML
    private AnchorPane filterPane;

    @FXML
    private Button openFilter;

    @FXML
    private ComboBox buildingID;
    @FXML
    private ComboBox buildingID1;
    @FXML
    private ComboBox buildingID2;
    @FXML
    private ComboBox filterSlot;
    @FXML
    private ComboBox filterSlot1;
    @FXML
    private TextField filterCapacity;
    @FXML
    private ComboBox filterFood;
    @FXML
    private DatePicker filterDate;
    @FXML
    private DatePicker filterDate1;
    @FXML
    private DatePicker filterDate2;

    public static String getId() {
        return id;
    }

    public void openFilter(Event event) {
        filterPane.setVisible(true);
    }

    public void closeFilter(Event event) {
        filterPane.setVisible(false);
    }

    public void loadFilter() {
        int j = 0;
        List<Buildings> listGetBuildings = null;
        try {
            listGetBuildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        j = 0;
        String[] listAllBuildings = new String[listGetBuildings.size()];
        for (Buildings t : listGetBuildings) {
            listAllBuildings[j] = "" + t.getBuilding_number();
            j++;
        }

        HelperController h = new HelperController();

        buildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        //buildingID.setValue("All");
        buildingID1.setItems(FXCollections.observableArrayList(listAllBuildings));
        //buildingID1.setValue("All");
        buildingID2.setItems(FXCollections.observableArrayList(listAllBuildings));
        //buildingID2.setValue("All");
        filterSlot.setItems(FXCollections.observableArrayList(h.getAllTimeSlots()));
        //filterSlot.setValue("All");
        filterSlot1.setItems(FXCollections.observableArrayList(h.getAllTimeSlots()));
        //filterSlot1.setValue("All");
    }

    public void searchRoom() throws IOException {
        System.out.println(buildingID.getValue());
        System.out.println(filterCapacity.getText());
        System.out.println(filterSlot.getValue());
        System.out.println(filterDate.getValue());
        List<Buildings> buildings = con.getBuildings();
        List<Rooms> rooms = con.getRooms();
        List<Rooms> allSuitableRooms = new ArrayList<>();
    }

    public void searchBike() {
        System.out.println(buildingID1.getValue());
        System.out.println(filterSlot1.getValue());
    }

    public void searchFood() {
        System.out.println(buildingID2.getValue());
        System.out.println(filterFood.getValue());
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

        HelperController helper = new HelperController();
        helper.loadNextScene("/MainReservationMenu.fxml", mainScreen);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Buildings> buildingsList = new ArrayList<>();

        loadFilter();

        filterPane.setVisible(false);

        int layoutY = 220;

        try {
            buildingsList = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        int capsCount = 0;
        int changeInPosition = 0;

        for (Buildings e : buildingsList) {
            capsCount = 0;
            changeInPosition = 0;
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

            if (i == 0) {
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + e.getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(72 + changeInPosition);
                textName.setLayoutY(14);
                textName.setFont(Font.font("System", 20));
                textName.setId("A");

                pane1.getChildren().add(textID);
                textID.setLayoutX(98);
                textID.setLayoutY(41);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(e.getUrl());
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(4);
                imageBuil.setY(74);
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, e.getBuilding_number() + "");
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
            }
            if (i == 1) {
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + e.getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(377 + changeInPosition);
                textName.setLayoutY(14);
                textName.setFont(Font.font("System", 20));
                textName.setId("B");

                pane1.getChildren().add(textID);
                textID.setLayoutX(397);
                textID.setLayoutY(41);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(e.getUrl());
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(302);
                imageBuil.setY(74);
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, "" + e.getBuilding_number());
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
            }
            if (i == 2) {
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + e.getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(682 + changeInPosition);
                textName.setLayoutY(14);
                textName.setFont(Font.font("System", 20));
                textName.setId("C");

                pane1.getChildren().add(textID);
                textID.setLayoutX(696);
                textID.setLayoutY(41);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(e.getUrl());
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(601);
                imageBuil.setY(74);
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, "" + e.getBuilding_number());
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
        i++;

        for (int j = i; j < buildingsList.size(); j++) {
            capsCount = 0;
            String buildingName = "";
            changeInPosition = 0;
            for (Character a : buildingsList.get(j).getName().toCharArray()) {
                if (a.toString().equals("(")) {
                    break;
                }
                if (Character.isUpperCase(a)) {
                    capsCount++;
                    buildingName = buildingName + a;
                }
            }

            if (capsCount == 1 || capsCount == 0) {
                buildingName = buildingsList.get(j).getName();
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

            for (Node e : pane1.getChildren()) {
                if (e instanceof Label && e.getId() != null) {
                    last = (Label) e;
                }
            }
            if (last.getId().contains("A")) {
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + buildingsList.get(j).getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(387 + changeInPosition);
                textName.setLayoutY(last.getLayoutY());
                textName.setFont(Font.font("System", 20));
                textName.setId("B");

                pane1.getChildren().add(textID);
                textID.setLayoutX(402);
                textID.setLayoutY(last.getLayoutY() + 27);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(buildingsList.get(j).getUrl());
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(302);
                imageBuil.setY(last.getLayoutY() + 60);
                String getId = buildingsList.get(j).getBuilding_number() + "";
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, getId);
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
            }
            if (last.getId().contains("B")) {
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + buildingsList.get(j).getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(682 + changeInPosition);
                textName.setLayoutY(last.getLayoutY());
                textName.setFont(Font.font("System", 20));
                textName.setId("C");

                pane1.getChildren().add(textID);
                textID.setLayoutX(696);
                textID.setLayoutY(last.getLayoutY() + 27);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(buildingsList.get(j).getUrl());
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(601);
                imageBuil.setY(last.getLayoutY() + 60);
                String getId = buildingsList.get(j).getBuilding_number() + "";
                imageBuil.setOnMouseClicked(event -> {
                    try {
                        goToMenuReservation(event, getId);
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                });
            }
            if (last.getId().contains("C")) {
                Label textName = new Label(buildingName);
                Label textID = new Label("Building: " + buildingsList.get(j).getBuilding_number());

                pane1.getChildren().add(textName);
                textName.setLayoutX(82 + changeInPosition);
                textName.setLayoutY(last.getLayoutY() + 268);
                textName.setFont(Font.font("System", 20));
                textName.setId("A");

                pane1.getChildren().add(textID);
                textID.setLayoutX(98);
                textID.setLayoutY(last.getLayoutY() + 295);
                textID.setFont(Font.font("System", 20));

                Image image = new Image(buildingsList.get(j).getUrl());
                ImageView imageBuil = new ImageView(image);
                pane1.getChildren().add(imageBuil);
                imageBuil.fitHeightProperty().setValue(192);
                imageBuil.setFitWidth(282);
                imageBuil.setX(4);
                imageBuil.setY(last.getLayoutY() + 60 + 268);
                String getId = buildingsList.get(j).getBuilding_number() + "";
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
