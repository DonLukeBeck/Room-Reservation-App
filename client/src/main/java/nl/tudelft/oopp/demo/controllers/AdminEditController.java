package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.tudelft.oopp.demo.communication.AdminServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Rooms;

public class AdminEditController implements Initializable {
    AdminServerCommunication con = new AdminServerCommunication();

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
        String[] listAllBuildings = new String[listGetBuildings.size()+1];
        listAllBuildings[0] = "Select building";
        j = 1;
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
        String[] listAllRooms = new String[listGetRooms.size()+1];
        listAllRooms[0] = "Select room";
        j = 1;
        for (Rooms r : listGetRooms) {
            listAllRooms[j] = "" + r.getRoomId();
            j++;
        }



        listOpen.setItems(FXCollections.observableArrayList(list));
        listClose.setItems(FXCollections.observableArrayList(list));
        listRoomsID.setItems(FXCollections.observableArrayList(listAllRooms));
        listBuildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        listBuildingID1.setItems(FXCollections.observableArrayList(listAllBuildings));
        roomType.setItems(FXCollections.observableArrayList("Select type","Study hall", "Exam hall"));

        listOpen.setValue("07:00");
        listClose.setValue("23:30");
        roomType.setValue("Select type");
        listRoomsID.setValue("Select room");
        listBuildingID.setValue("Select building");
        listBuildingID1.setValue("Select building");
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
        if(!bikeCapacity.isEmpty()) {
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
        }

        int buildingID = Integer.parseInt(listBuildingID1.getValue().toString());
        String buildingName = editBuildingName.getText();
        String imageUrl = editBuildingUrl.getText();
        String buildingOpen = listOpen.getValue().toString();
        String buildingClose = listClose.getValue().toString();

        System.out.println(buildingName);
        System.out.println(imageUrl);
        System.out.println(bikes);
        System.out.println(buildingOpen);
        System.out.println(buildingClose);

        if (listBuildingID1.getValue().toString().equals("Select building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        con.editBuildingAdmin(buildingID, buildingName, buildingOpen+ ""
                + ":00", buildingClose+ ""
                + ":00", imageUrl, bikes);

        HelperController h = new HelperController();
        h.loadNextScene("/AdminEditView.fxml", mainScreen);
    }

    /**
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
        if (listRoomsID.getValue().toString().equals("Select room")) {
            exception.setText("Please select room.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (roomCapacity.getText().isBlank()) {
            exception.setText("Please enter new room capacity.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }
        if (listBuildingID.getValue().toString().equals("Select building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (roomType.getValue().toString().equals("Select type")) {
            exception.setText("Please select room type.");
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

        //con.editRoomAdmin(roomID.getText(), roomCap, building, roomType.getValue().toString(), );

        HelperController h = new HelperController();
        h.loadNextScene("/AdminView.fxml", mainScreen);
    }
}

