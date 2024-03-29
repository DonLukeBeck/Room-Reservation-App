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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.tudelft.oopp.demo.communication.AdminServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;


@SuppressWarnings("checkstyle:Indentation")
public class AdminAddBuildingController implements Initializable {

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
    private TextField numberChairs;
    @FXML
    private TextField numberWhiteboards;
    @FXML
    private TextField numberTables;
    @FXML
    private TextField numberComputers;
    @FXML
    private ChoiceBox listBuildingID;
    @FXML
    private ChoiceBox roomType;

    /**
     * Sends user to admin add page.
     *
     * @param event Logging in as admin
     * @throws IOException Exception if can't find admin view scene
     */
    public void goToAdminAdd(ActionEvent event) throws IOException {

        HelperController helper = new HelperController();
        helper.loadNextScene("/AdminAddBuildingView.fxml", mainScreen);
    }

    /**
     * Method for admin to initialize picture and resource bundle.
     *
     * @param location  Location of the picture
     * @param resources Resource bundle
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

        listOpen.setItems(FXCollections.observableArrayList(list));
        listClose.setItems(FXCollections.observableArrayList(list));
        listBuildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        roomType.setItems(FXCollections
                .observableArrayList("Select type", "Study hall", "Exam hall"));

        listOpen.setValue("07:00");
        listClose.setValue("23:30");
        roomType.setValue("Select type");
        listBuildingID.setValue("Select building");

    }

    /**
     * Method to go back to main admin scene.
     *
     * @param event Clicking on go back
     * @throws IOException Exception if can't find main admin scene
     */
    public void goBack(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainAdminScene.fxml", mainScreen);
    }

    /**
     * Method for admin to add a building.
     *
     * @param event Clicking on add building
     * @throws IOException Exception if can't find admin view scene
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

        if (addBuildingID
                .getText().isBlank() || addBuildingName
                .getText().isBlank() || addBuildingUrl
                .getText().isBlank() || addBuildingUrl
                .getText().isBlank()) {
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

        try {
            Image image = new Image(imageUrl);
            ImageView buildingImage = new ImageView(image);
        } catch (Exception e) {
            addException(45, 120, "Not valid URL!", exception);
            return;
        }


        con.addBuildingAdmin(buildingID, buildingName, buildingOpen + ""
                + ":00", buildingClose + ":00", imageUrl, bikes);
        HelperController h = new HelperController();
        h.loadNextScene("/AdminAddBuildingView.fxml", mainScreen);
    }

    /**
     * Method for admin to add a room.
     *
     * @param event Clicking on add room
     * @throws IOException Exception if can't find admin view scene
     */
    public void addRoom(Event event) throws IOException {
        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
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

        if (roomID.getText().isBlank() || numberChairs.getText().isBlank()) {
            addException(670, 120, "Fill all fields!", exception);
            return;
        }

        if (roomID.getText().length() > 14) {
            exception.setText("Max 14 characters allowed.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        int roomCap = 0;
        try {
            roomCap = Integer.parseInt(numberChairs.getText());
        } catch (Exception e) {
            addException(670, 120, "Only numbers are allowed for number of chairs!", exception);
            return;
        }

        int roomWhiteboards = 0;
        try {
            roomWhiteboards = Integer.parseInt(numberWhiteboards.getText());
        } catch (Exception e) {
            addException(670,
                    120, "Only numbers are allowed for number of whiteboards!", exception);
            return;
        }

        int roomTables = 0;
        try {
            roomTables = Integer.parseInt(numberTables.getText());
        } catch (Exception e) {
            addException(670, 120, "Only numbers are allowed for number of tables!", exception);
            return;
        }

        int roomComputers = 0;
        try {
            roomComputers = Integer.parseInt(numberComputers.getText());
        } catch (Exception e) {
            addException(670, 120, "Only numbers are allowed for number of computers!", exception);
            return;
        }

        int building = Integer.parseInt(listBuildingID.getValue().toString());

        con.addRoomAdmin(roomID.getText(),
                roomCap,
                roomWhiteboards,
                roomTables,
                roomComputers,
                building,
                roomType.getValue().toString());

        HelperController h = new HelperController();
        h.loadNextScene("/AdminAddBuildingView.fxml", mainScreen);
    }

    /**
     * Open exception when needed.
     *
     * @param layoutX   layout X
     * @param layoutY   layout Y
     * @param text      text for the exception
     * @param exception Label which text will be changed
     */
    public void addException(double layoutX, double layoutY, String text, Label exception) {
        exception.setText(text);
        exception.setLayoutY(layoutY);
        exception.setLayoutX(layoutX);
        exception.setTextFill(Color.valueOf("red"));
        exception.setFont(Font.font(20));
        exception.setId("Exception");
    }
}
