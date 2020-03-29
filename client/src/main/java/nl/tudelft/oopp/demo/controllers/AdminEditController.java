package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TextField editRoomID;
    @FXML
    private TextField numberTables;
    @FXML
    private TextField numberComputers;
    @FXML
    private TextField numberWhiteboards;
    @FXML
    private TextField numberChairs;
    @FXML
    private ChoiceBox roomType;
    @FXML
    private ChoiceBox listRoomsID;
    @FXML
    private ChoiceBox listBuildingID1;
    @FXML
    private ChoiceBox listBuildingID2;



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
        listBuildingID1.setItems(FXCollections.observableArrayList(listAllBuildings));
        listBuildingID2.setItems(FXCollections.observableArrayList(listAllBuildings));

        roomType.setItems(FXCollections.observableArrayList("Select type","Study hall", "Exam hall"));

        listOpen.setValue("07:00");
        listClose.setValue("23:30");
        roomType.setValue("Select type");
        listRoomsID.setValue("Select room");
        listBuildingID1.setValue("Select building");
        listBuildingID2.setValue("Select building");

        listBuildingID1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            //if the item of the list is changed
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    Buildings selectedBuilding = con.getBuildingByName(Integer.parseInt(listAllBuildings[(int)newValue]));
                    editBuildingName.setText(selectedBuilding.getName());
                    String openingHour="";
                    for(int i = 0; i < 5; i++){
                        openingHour = openingHour +  selectedBuilding.getOpeningHours().toString().charAt(i);
                    }
                    listOpen.setValue(openingHour);
                    String closingHour="";
                    for(int i = 0; i < 5; i++){
                        closingHour = closingHour +  selectedBuilding.getClosingHours().toString().charAt(i);
                    }
                    listClose.setValue(closingHour);
                    editBuildingUrl.setText(selectedBuilding.getUrl());
                    editBuildingBikes.setText(Integer.toString(selectedBuilding.getNumber_of_bikes()));
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("No selected building");
                }
            }
        });

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

        int buildingID = Integer.parseInt(listBuildingID1.getValue().toString());


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

        String buildingName = editBuildingName.getText();
        String imageUrl = editBuildingUrl.getText();
        String buildingOpen = listOpen.getValue().toString();
        String buildingClose = listClose.getValue().toString();

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

        if (listBuildingID2.getValue().toString().equals("Select building")) {
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

        String roomID = editRoomID.getText();
        int roomChairs = Integer.parseInt(numberChairs.getText());
        int roomWhiteboards = Integer.parseInt(numberWhiteboards.getText());
        int roomTables = Integer.parseInt(numberTables.getText());
        int roomComputers = Integer.parseInt(numberComputers.getText());
        int roomBuildingID= Integer.parseInt(listBuildingID2.getValue().toString());
        String newRoomType = roomType.getValue().toString();
        String oldRoomId = listRoomsID.getValue().toString();

        con.editRoomAdmin(roomID,
                roomChairs,
                roomWhiteboards,
                roomTables,
                roomComputers,
                roomBuildingID,
                newRoomType,
                oldRoomId);

        HelperController h = new HelperController();
        h.loadNextScene("/AdminView.fxml", mainScreen);
    }
}

