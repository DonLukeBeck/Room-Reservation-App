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
import nl.tudelft.oopp.demo.entities.Rooms;

public class AdminEditController implements Initializable {
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
    private TextField editBuildingName;
    @FXML
    private TextField editBuildingUrl;
    @FXML
    private TextField editBuildingBikes;
    @FXML
    private TextField roomID;
    @FXML
    private TextField roomCapacity;
    @FXML
    private ChoiceBox listBuildingID;
    @FXML
    private ChoiceBox roomType;
    @FXML
    private ChoiceBox listRoomsID;
    @FXML
    private ChoiceBox listBuildingID1;

    /**
     *
     * @param event
     * @throws IOException
     */
    public void goToAdminAdd(ActionEvent event) throws IOException {

        HelperController helper = new HelperController();
        helper.loadNextScene("/AdminEditView.fxml", mainScreen);
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
        List<Rooms> listGetRooms = null;
        try {
            listGetRooms = con.getRooms();
        } catch (IOException e) {
            e.printStackTrace();
        }
        j = 0;
        String[] listAllRooms = new String[listGetRooms.size()];
        for (Rooms r : listGetRooms) {
            listAllRooms[j] = "" + r.getRoomId();
            j++;
        }


        listOpen.setValue("07:00");
        listClose.setValue("23:30");
        roomType.setValue("Student");
        listRoomsID.setValue("Choose room");
        listBuildingID.setValue("Choose building");
        listBuildingID1.setValue("Choose building");
        listOpen.setItems(FXCollections.observableArrayList(list));
        listClose.setItems(FXCollections.observableArrayList(list));
        listRoomsID.setItems(FXCollections.observableArrayList(listAllRooms));
        listBuildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        listBuildingID1.setItems(FXCollections.observableArrayList(listAllBuildings));
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
    public void editBuilding(Event event) throws IOException {
        String bikeCapacity = editBuildingBikes.getText();
        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        int bikes = 0;
        String b = listBuildingID1.getId();
        List<Buildings> listGetBuildings = null;
        try {
            listGetBuildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int j = 0;
        String[] listAllBuildings = new String[listGetBuildings.size()];
        for (Buildings t : listGetBuildings) {
            listAllBuildings[j] = "" + t.getBuilding_number();
            if (t.getBuilding_number() == Integer.parseInt(b)) {
                bikes = t.getNumber_of_bikes();
            }
            j++;
        }


        try {
            bikes = Integer.parseInt(bikeCapacity);
        } catch (Exception e) {
            exception.setText("Only numbers are allowed for bike capacity!");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;

        }
        int buildingID = Integer.parseInt(listBuildingID1.getId());
        String buildingName = editBuildingName.getText();
        String imageUrl = editBuildingUrl.getText();
        String buildingOpen = listOpen.getValue().toString();
        String buildingClose = listClose.getValue().toString();
        System.out.println(buildingName);
        System.out.println(imageUrl);
        System.out.println(bikes);
        System.out.println(buildingOpen);
        System.out.println(buildingClose);


        HelperController h = new HelperController();
        h.loadNextScene("/AdminEditView.fxml", mainScreen);
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    public void editRoom(Event event) throws IOException {
        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }
        if (listRoomsID.getValue().toString().equals("Choose room")) {
            exception.setText("Choose room!");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (roomCapacity.getText().isBlank()) {
            exception.setText("Fill all fields!");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }
        if (listBuildingID.getValue().toString().equals("Choose building")) {
            exception.setText("Choose building!");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        int roomCap = 0;
        try {
            roomCap = Integer.parseInt(roomCapacity.getText());
        } catch (Exception e) {
            exception.setText("Only numbers are allowed for room capacity!");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }
        int building = Integer.parseInt(listBuildingID.getValue().toString());
        System.out.println(listRoomsID.getValue().toString());
        System.out.println(roomCap);
        System.out.println(listBuildingID.getValue().toString());
        System.out.println(roomType.getValue());


        HelperController h = new HelperController();
        h.loadNextScene("/AdminView.fxml", mainScreen);
    }
}

