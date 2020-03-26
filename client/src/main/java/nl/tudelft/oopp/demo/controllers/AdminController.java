package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;


@SuppressWarnings("checkstyle:Indentation")
public class AdminController implements Initializable {
    ServerCommunication con = new ServerCommunication();

    @FXML
    private javafx.scene.control.Button add;

    @FXML
    private javafx.scene.control.Button goBack;

    @FXML
    private ComboBox listOpen;

    @FXML
    private ComboBox listClose;

    @FXML
    private AnchorPane mainScreen;
    @FXML
    private TextField addBuildingID;
    @FXML
    private TextField addBuildingName;
    @FXML
    private TextField addBuildingUrl;
    @FXML
    private TextField addBuildingBikes;
    @FXML
    private TextField roomID;
    @FXML
    private TextField roomCapacity;
    @FXML
    private ChoiceBox listBuildingID;
    @FXML
    private ChoiceBox roomType;

    /**
     *
     * @param event
     * @throws IOException
     */
    public void goToAdminAdd(ActionEvent event) throws IOException {

        HelperController helper = new HelperController();
        helper.loadNextScene("/AdminView.fxml", mainScreen);
    }

    /***
     *
     * @param location Location of the picture
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] list = new String[48];
        int j = 0;
        for (int i = 0; i <= 23; i++) {
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
        listOpen.setValue("07:00");
        listClose.setValue("23:30");
        roomType.setValue("Student");
        listBuildingID.setValue("Choose building");
        listOpen.setItems(FXCollections.observableArrayList(list));
        listClose.setItems(FXCollections.observableArrayList(list));
        listBuildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        roomType.setItems(FXCollections.observableArrayList("Study hall", "Exam hall"));
    }

    /***
     *
     * @param event
     * @throws IOException
     */
    public void goBack(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainAdminScene.fxml", mainScreen);
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    public void addBuilding(Event event) throws IOException {
        String bikeCapacity = addBuildingBikes.getText();
        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (addBuildingID.getText().isBlank() || addBuildingName.getText().isBlank() || addBuildingUrl.getText().isBlank() || addBuildingUrl.getText().isBlank()) {
            exception.setText("Fill all fields!");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        int buildingID = 0;
        try {
            buildingID = Integer.parseInt(addBuildingID.getText());
        } catch (Exception e) {
            addException(45, 120, "Only numbers are allowed for building ID!", exception);

            return;

        }

        int bikes = 0;
        try {
            bikes = Integer.parseInt(bikeCapacity);
        } catch (Exception e) {
            addException(45, 120, "Only numbers are allowed for bike capacity!", exception);
            return;

        }
        String buildingName = addBuildingName.getText();
        String imageUrl = addBuildingUrl.getText();
        String buildingOpen = listOpen.getValue().toString();
        String buildingClose = listClose.getValue().toString();
        System.out.println(buildingName);
        System.out.println(buildingID);
        System.out.println(imageUrl);
        System.out.println(bikes);
        System.out.println(buildingOpen);
        System.out.println(buildingClose);
        con.addBuildingAdmin(buildingID, buildingName, buildingOpen + ""
                + ":00", buildingClose + ":00", imageUrl, bikes, 0);
        HelperController h = new HelperController();
        h.loadNextScene("/AdminView.fxml", mainScreen);
    }

    /**
     * @param event
     * @throws IOException
     */
    public void addRoom(Event event) throws IOException {
        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (roomID.getText().isBlank() || roomCapacity.getText().isBlank()) {
            addException(670, 120, "Fill all fields!", exception);
            return;
        }
        if (listBuildingID.getValue().toString().equals("Choose building")) {
            addException(670, 120, "Choose building!", exception);
            return;
        }

        int roomCap = 0;
        try {
            roomCap = Integer.parseInt(roomCapacity.getText());
        } catch (Exception e) {
            addException(670, 120, "Only numbers are allowed for room capacity!", exception);
            return;
        }
        int building = Integer.parseInt(listBuildingID.getValue().toString());
        System.out.println(roomID.getText());
        System.out.println(roomCap);
        System.out.println(building);
        System.out.println(roomType.getValue());
        con.addRoomAdmin(roomID.getText(), roomCap, building, roomType.getValue().toString());

        HelperController h = new HelperController();
        h.loadNextScene("/AdminView.fxml", mainScreen);
    }

    public void addException(double layoutX, double layoutY, String text, Label exception) {
        exception.setText(text);
        exception.setLayoutY(layoutY);
        exception.setLayoutX(layoutX);
        exception.setTextFill(Color.valueOf("red"));
        exception.setFont(Font.font(20));
        exception.setId("Exception");
    }
}
