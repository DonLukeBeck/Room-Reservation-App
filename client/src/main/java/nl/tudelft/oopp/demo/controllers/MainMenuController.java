package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import nl.tudelft.oopp.demo.entities.Rooms;

public class MainMenuController implements Initializable {
    private static String id;

    ServerCommunication con = new ServerCommunication();
    HelperController helper = new HelperController();
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

    /**
     * Loads all scene controllers related to the filer.
     */
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

        buildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        //buildingID.setValue("All");
        buildingID1.setItems(FXCollections.observableArrayList(listAllBuildings));
        //buildingID1.setValue("All");
        buildingID2.setItems(FXCollections.observableArrayList(listAllBuildings));
        //buildingID2.setValue("All");
        filterSlot.setItems(FXCollections.observableArrayList(helper.getAllTimeSlots()));
        //filterSlot.setValue("All");
        filterSlot1.setItems(FXCollections.observableArrayList(helper.getAllTimeSlots()));
        //filterSlot1.setValue("All");
    }

    /**
     * Getting the input of the user from the fields of the filter.
     * Filtering for suitable rooms
     *
     * @throws IOException
     */
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

    /**
     * Opening image of the campus map
     *
     * @param event
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
     * Opens the fxml of the next scene on click
     *
     * @param event
     * @param buildingNumber
     * @throws IOException
     */
    public void goToMenuReservation(Event event, String buildingNumber) throws IOException {
        id = buildingNumber;

        HelperController helper = new HelperController();
        helper.loadNextScene("/MainReservationMenu.fxml", mainScreen);
    }

    public void addLabelSidePane(double layoutX, double layoutY, String text) {
        Label sideBuilding = new Label(text);
        sideBuilding.setLayoutX(layoutX);
        sideBuilding.setLayoutY(layoutY);
        sideBuilding.setFont(Font.font("System", FontWeight.BOLD, 14));
        sideBuilding.setTextFill(Color.valueOf("white"));
        sidePane.getChildren().add(sideBuilding);
    }

    public void addLabelScrollPane(double layoutX, double layoutY, String text, String id) {
        Label buildingLabel = new Label(text);
        buildingLabel.setLayoutX(layoutX);
        buildingLabel.setLayoutY(layoutY);
        buildingLabel.setFont(Font.font("System", 20));
        buildingLabel.setId(id);
        pane1.getChildren().add(buildingLabel);
    }

    public String changeInPositionOfBuildingName(Buildings e) {
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
        return buildingName;
    }

    public void addImageToScrollPane(Buildings e, double layoutX, double layoutY) {
        Image image = new Image(e.getUrl());
        ImageView buildingImage = new ImageView(image);
        pane1.getChildren().add(buildingImage);
        buildingImage.fitHeightProperty().setValue(192);
        buildingImage.setFitWidth(282);
        buildingImage.setX(layoutX);
        buildingImage.setY(layoutY);
        buildingImage.setOnMouseClicked(event -> {
            try {
                goToMenuReservation(event, e.getBuilding_number() + "");
            } catch (IOException k) {
                k.printStackTrace();
            }
        });
    }

    public int addFirstThreeBuildings(List<Buildings> buildingsList) {
        int changeInPosition = 0;
        int i = 0;
        int layoutY = 220;

        for (Buildings e : buildingsList) {
            String buildingName = changeInPositionOfBuildingName(e);
            if (!buildingName.contains("Faculty")) {
                changeInPosition = 40;
            } else {
                changeInPosition = 0;
            }

            addLabelSidePane(56, layoutY + 28, "-" + buildingName);
            layoutY = layoutY + 28;
            System.out.println(layoutY);

            if (i == 0) {
                addLabelScrollPane(72 + changeInPosition, 14, buildingName, "A");
                addLabelScrollPane(98, 41, "Building: " + e.getBuilding_number(), null);
                addImageToScrollPane(e, 4, 74);
            }
            if (i == 1) {

                addLabelScrollPane(377 + changeInPosition, 14, buildingName, "B");
                addLabelScrollPane(397, 41, "Building: " + e.getBuilding_number(), null);
                addImageToScrollPane(e, 302, 74);
            }
            if (i == 2) {
                addLabelScrollPane(682 + changeInPosition, 14, buildingName, "C");
                addLabelScrollPane(696, 41, "Building: " + e.getBuilding_number(), null);
                addImageToScrollPane(e, 601, 74);
                break;
            }
            i++;
        }
        return i;
    }

    /**
     * Loading all needed scene controllers when the fxml is loaded
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Buildings> buildingsList = new ArrayList<>();

        loadFilter();

        filterPane.setVisible(false);

        int layoutY = 304;

        try {
            buildingsList = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        int changeInPosition = 0;
        int checkNumberOfBuildings = addFirstThreeBuildings(buildingsList);

        if (checkNumberOfBuildings < 2) {
            return;
        }

        i = checkNumberOfBuildings;

        Label last = null;
        i++;

        for (int j = i; j < buildingsList.size(); j++) {
            String buildingName = changeInPositionOfBuildingName(buildingsList.get(j));

            if (!buildingName.contains("Faculty")) {
                changeInPosition = 40;
            } else {
                changeInPosition = 0;
            }

            addLabelSidePane(56, layoutY + 28, buildingName);
            layoutY = layoutY + 28;

            for (Node e : pane1.getChildren()) {
                if (e instanceof Label && e.getId() != null) {
                    last = (Label) e;
                }
            }

            if (last.getId().contains("A")) {
                addLabelScrollPane(387 + changeInPosition, last.getLayoutY(), buildingName, "B");
                addLabelScrollPane(402, last.getLayoutY() + 27,
                        "Building: " + buildingsList.get(j).getBuilding_number(),
                        null
                );
                addImageToScrollPane(buildingsList.get(j), 302, last.getLayoutY() + 60);
            }
            if (last.getId().contains("B")) {
                addLabelScrollPane(682 + changeInPosition, last.getLayoutY(), buildingName, "C");
                addLabelScrollPane(696, last.getLayoutY() + 27,
                        "Building: " + buildingsList.get(j).getBuilding_number(),
                        null
                );
                addImageToScrollPane(buildingsList.get(j), 601, last.getLayoutY() + 60);
            }
            if (last.getId().contains("C")) {
                addLabelScrollPane(82 + changeInPosition, last.getLayoutY() + 268,
                        buildingName, "A"
                );
                addLabelScrollPane(98, last.getLayoutY() + 295,
                        "Building: " + buildingsList.get(j).getBuilding_number(),
                        null
                );
                addImageToScrollPane(buildingsList.get(j), 4, last.getLayoutY() + 60 + 268);
            }
        }
    }

    public void userPage(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserPage.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage stage1 = (Stage) scene1.getScene().getWindow();
        stage1.close();
    }
}
