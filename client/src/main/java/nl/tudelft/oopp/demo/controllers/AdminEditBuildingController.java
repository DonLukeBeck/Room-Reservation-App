package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.tudelft.oopp.demo.communication.AdminServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Rooms;

public class AdminEditBuildingController implements Initializable {
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
     * Method to go to admin edit view.
     * @param event Clicking on edit
     * @throws IOException Exception if can't find admin edit view scene
     */
    public void goToAdminEdit(ActionEvent event) throws IOException {

        HelperController helper = new HelperController();
        helper.loadNextScene("/AdminEditBuildingView.fxml", mainScreen);
    }

    /**
     *Method to initialize.
     * @param location Location of the picture
     * @param resources Resource Bundle
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
        String[] listAllBuildings = new String[listGetBuildings.size() + 1];
        listAllBuildings[0] = "Select building";
        j = 1;
        for (Buildings t : listGetBuildings) {
            listAllBuildings[j] = "" + t.getBuilding_number();
            j++;
        }

        String[] listAllRooms = new String[2];
        listAllRooms[0] = "Select room";
        listAllRooms[1] = "First select building";


        listOpen.setItems(FXCollections.observableArrayList(list));
        listClose.setItems(FXCollections.observableArrayList(list));
        listRoomsID.setItems(FXCollections.observableArrayList(listAllRooms));
        listBuildingID1.setItems(FXCollections.observableArrayList(listAllBuildings));
        listBuildingID2.setItems(FXCollections.observableArrayList(listAllBuildings));

        roomType
                .setItems(FXCollections.observableArrayList("Select type",
                        "Study hall",
                        "Exam hall"));
        listOpen.setValue("07:00");
        listClose.setValue("23:30");
        roomType.setValue("Select type");
        listRoomsID.setValue("Select room");
        listBuildingID1.setValue("Select building");
        listBuildingID2.setValue("Select building");
        listBuildingID1.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    //if the item of the list is changed
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue,
                                        Number newValue) {
                        try {
                            Buildings selectedBuilding = con
                                    .getBuildingByName(Integer
                                            .parseInt(listAllBuildings[(int)newValue]));
                            editBuildingName.setText(selectedBuilding.getName());
                            String openingHour = "";
                            for (int i = 0; i < 5; i++) {
                                openingHour = openingHour +  selectedBuilding
                                        .getOpeningHours().toString().charAt(i);
                            }
                            listOpen.setValue(openingHour);
                            String closingHour = "";
                            for (int i = 0; i < 5; i++) {
                                closingHour = closingHour
                                        + selectedBuilding
                                        .getClosingHours().toString().charAt(i);
                            }
                            listClose.setValue(closingHour);
                            editBuildingUrl.setText(selectedBuilding.getUrl());
                            editBuildingBikes
                                    .setText(Integer
                                            .toString(selectedBuilding
                                                    .getNumber_of_bikes()));
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("No selected building");
                        }
                    }
                });

        listBuildingID2.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue,
                                        Number newValue) {
                        try {
                            Buildings selectedBuilding = con
                                    .getBuildingByName(Integer
                                            .parseInt(listAllBuildings[(int)newValue]));

                            listRoomsID.setValue("Select room");
                            editRoomID.setText("");
                            numberChairs.setText("");
                            numberComputers.setText("");
                            numberTables.setText("");
                            numberWhiteboards.setText("");
                            roomType.setValue("Select type");

                            List<Rooms> listGetRoomsByBuilding = null;
                            try {
                                listGetRoomsByBuilding = con
                                        .getRoomsByBuilding(selectedBuilding
                                                .getBuilding_number());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String[] listRoomsByBuilding = new String[listGetRoomsByBuilding
                                    .size() + 1];
                            listRoomsByBuilding[0] = "Select room";
                            int j = 1;
                            for (Rooms r : listGetRoomsByBuilding) {
                                listRoomsByBuilding[j] = "" + r.getRoomId();
                                j++;
                            }
                            listRoomsID.setItems(FXCollections
                                    .observableArrayList(listRoomsByBuilding));
                            listRoomsID.setValue("Select room");

                            listRoomsID.getSelectionModel()
                                    .selectedIndexProperty()
                                    .addListener(new ChangeListener<Number>() {
                                        @Override
                                        public void changed(
                                                ObservableValue<? extends Number> observable,
                                                            Number oldValue,
                                                            Number newValue) {
                                            if ((int) newValue != 0) {
                                                Rooms selectedRoom = new Rooms();
                                                try {
                                                    for (Rooms r : con
                                                            .getRoomsByBuilding(selectedBuilding
                                                                    .getBuilding_number())) {
                                                        if (r.getRoomId()
                                                                .equals(listRoomsByBuilding[
                                                                        (int) newValue])) {
                                                            selectedRoom = r;
                                                        }
                                                    }
                                                    editRoomID.setText(selectedRoom.getRoomId());
                                                    numberChairs.setText(String
                                                            .valueOf(selectedRoom
                                                                    .getChairs()));
                                                    numberComputers.setText(String
                                                            .valueOf(selectedRoom
                                                                    .getComputers()));
                                                    numberTables.setText(String
                                                            .valueOf(selectedRoom
                                                                    .getTables()));
                                                    numberWhiteboards
                                                            .setText(String
                                                                    .valueOf(selectedRoom
                                                                            .getWhiteboards()));
                                                    roomType.setValue(selectedRoom.getType());
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                    System.out.print("No selected Room");
                                                }
                                            }
                                        }
                                    });
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.print("No selected Building");
                        }
                    }
                });
    }

    /**
     *Method to go back to main admin scene.
     * @param event Clicking on go back
     * @throws IOException Exception if can't find main admin scene
     */
    public void goBack(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainAdminScene.fxml", mainScreen);
    }

    /**
     * Method for admin to edit building.
     * @param event Clicking on edit
     * @throws IOException Exception if can't find admin edit view scene
     */
    public void editBuilding(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        String bikeCapacity = editBuildingBikes.getText();

        int bikes = 0;
        if (!bikeCapacity.isEmpty()) {
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


        if (listBuildingID1.getValue().toString().equals("Select building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (editBuildingName
                .getText().isEmpty() || editBuildingUrl
                .getText().isEmpty() || editBuildingBikes
                .getText().isEmpty()) {
            exception.setText("Fill all fields!");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        String buildingName = editBuildingName.getText();
        String imageUrl = editBuildingUrl.getText();
        String buildingOpen = listOpen.getValue().toString();
        String buildingClose = listClose.getValue().toString();

        try {
            Image image = new Image(imageUrl);
            ImageView buildingImage = new ImageView(image);
        } catch (Exception e) {
            exception.setText("Not valid URL");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        int buildingID = Integer.parseInt(listBuildingID1.getValue().toString());

        con.editBuildingAdmin(buildingID, buildingName, buildingOpen + ""
                + ":00", buildingClose + ""
                + ":00", imageUrl, bikes);

        HelperController h = new HelperController();
        h.loadNextScene("/AdminEditBuildingView.fxml", mainScreen);
    }

    /**
     * Method for admin to edit a room.
     * @param event Clicking on edit room
     * @throws IOException Exception if can't find admin view scene
     */
    public void editRoom(Event event) throws IOException {
        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (listBuildingID2.getValue().toString().equals("Select building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(100);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (listRoomsID.getValue().toString().equals("Select room")) {
            exception.setText("Please select room.");
            exception.setLayoutY(100);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }


        if (roomType.getValue().toString().equals("Select type")) {
            exception.setText("Please select room type.");
            exception.setLayoutY(100);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }
        if (editRoomID.getText().length() > 14) {
            exception.setText("Max 14 characters allowed.");
            exception.setLayoutY(100);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (editRoomID.getText().isEmpty()
                || numberChairs.getText().isEmpty()
                || numberComputers.getText().isEmpty()
                || numberTables.getText().isEmpty()
                || numberWhiteboards.getText().isEmpty()) {
            exception.setText("Fill all fields!");
            exception.setLayoutY(100);
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
        int roomBuildingID = Integer.parseInt(listBuildingID2.getValue().toString());
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
        h.loadNextScene("/AdminEditBuildingView.fxml", mainScreen);
    }

}

