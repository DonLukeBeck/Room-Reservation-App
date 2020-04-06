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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private static boolean filter = false;
    private static List<Rooms> filterRooms;

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
    private ComboBox buildingID;
    @FXML
    private TextField filterCapacity;
    @FXML
    private ComboBox filterRoomType;
    @FXML
    private CheckBox filterTables;
    @FXML
    private CheckBox filterWhiteboards;
    @FXML
    private CheckBox filterComputers;
    @FXML
    private Pane rightPane;

    public static boolean getFilter() {
        return filter;
    }

    public static void setFilter(boolean f) {
        filter = f;
    }

    public static List<Rooms> getFilterRooms() {
        return filterRooms;
    }

    public static String getId() {
        return id;
    }

    /**
     * Method to open filter.
     *
     * @param event Clicking on filter
     */
    public void openFilter(Event event) {
        if (filterPane.isVisible()) {
            filterPane.setVisible(false);
        } else {
            filterPane.setVisible(true);
        }
    }

    public void closeFilter(Event event) {
        filterPane.setVisible(false);
    }

    public void paneExit(Event event) throws IOException {
        helper.exit(mainScreen);
    }

    public void paneLogOut(Event event) throws IOException {
        helper.logOut(mainScreen);
    }

    public void paneUserProfile(Event event) throws IOException {
        helper.userProfile(mainScreen);
    }

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
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
        String[] listAllBuildings = new String[listGetBuildings.size()];
        for (Buildings t : listGetBuildings) {
            listAllBuildings[j] = "" + t.getBuilding_number();
            j++;
        }

        buildingID.setItems(FXCollections.observableArrayList(listAllBuildings));

        filterRoomType.setItems(FXCollections.observableArrayList("Exam hall", "Study hall"));
    }

    /**
     * Getting the input of the user from the fields of the filter.
     * Filtering for suitable rooms
     *
     * @throws IOException Exception if can't find room menu page
     */
    public void searchRoom() throws IOException {
        int building = 1000;
        try {
            building = Integer.parseInt(buildingID.getValue().toString());

        } catch (Exception r) {
            System.out.println(r);
        }
        List<Rooms> allRooms = con.getRooms();
        List<Rooms> suitableRooms = new ArrayList<>();

        for (Rooms t : allRooms) {
            if (building == 1000) {
                suitableRooms.add(t);
            }
            if (t.getAssociatedBuilding() == building) {
                suitableRooms.add(t);
            }
        }

        int capacity = 0;
        if (!filterCapacity.getText().isBlank()) {
            try {
                capacity = Integer.parseInt(filterCapacity.getText());
            } catch (Exception k) {
                capacity = 0;
            }
        }

        int minComp = 0;
        int minBoards = 0;
        int minTables = 0;

        boolean computers = filterComputers.isSelected();
        boolean whiteboards = filterWhiteboards.isSelected();
        boolean tables = filterTables.isSelected();

        if (computers) {
            minComp++;
        }
        if (whiteboards) {
            minBoards++;
        }
        if (tables) {
            minTables++;
        }

        String type = "";
        if (filterRoomType.getValue() != null) {
            type = filterRoomType.getValue().toString();
        } else {
            type = "Study hall";
        }
        List<Rooms> result = new ArrayList<>();

        for (Rooms e : suitableRooms) {
            if (e.getComputers() >= minComp && e.getWhiteboards() >= minBoards
                    && e.getTables() >= minTables
                    && e.getChairs() >= capacity && e.getType().equals(type)) {

                result.add(e);
            }
        }
        filterRooms = result;
        setFilter(true);
        helper.loadNextScene("/RoomMenu.fxml", mainScreen);
    }

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }


    /**
     * Opening image of the campus map.
     *
     * @param event Clicking on campus map button
     * @throws IOException Exception if can't find campus map scene
     */
    public void campusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("images/favicon.png"));
        stage.show();
    }

    /**
     * Opens the fxml of the next scene on click.
     *
     * @param event          Clicking on reservation
     * @param buildingNumber Building number as a string
     * @throws IOException Exception if can't find main reservation menu page
     */
    public void goToMenuReservation(Event event, String buildingNumber) throws IOException {
        id = buildingNumber;

        HelperController helper = new HelperController();
        helper.loadNextScene("/MainReservationMenu.fxml", mainScreen);
    }

    /**
     * Create new label.
     *
     * @param layoutX chosen layout X
     * @param layoutY chosen layout Y
     * @param text    text for the label
     */
    public void addLabelSidePane(double layoutX, double layoutY, String text) {
        Label sideBuilding = new Label(text);
        sideBuilding.setLayoutX(layoutX);
        sideBuilding.setLayoutY(layoutY);
        sideBuilding.setFont(Font.font("System", FontWeight.BOLD, 14));
        sideBuilding.setTextFill(Color.valueOf("white"));
        sidePane.getChildren().add(sideBuilding);
    }

    /**
     * Add label to ScrollPane.
     *
     * @param layoutX chosen layout X
     * @param layoutY chosen layout Y
     * @param text    text for the label
     * @param id      id for the Label
     */
    public void addLabelScrollPane(double layoutX, double layoutY, String text, String id) {
        Label buildingLabel = new Label(text);
        buildingLabel.setLayoutX(layoutX);
        buildingLabel.setLayoutY(layoutY);
        buildingLabel.setFont(Font.font("System", 20));
        buildingLabel.setId(id);
        pane1.getChildren().add(buildingLabel);
    }

    /**
     * Method to change format of building name.
     *
     * @param e Building to change the name of
     * @return Building name in desired format
     */
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

    /**
     * Add image to ScrollPane.
     *
     * @param e       chosen building.
     * @param layoutX layoutX
     * @param layoutY layout Y
     */
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

    /**
     * Calculate the change in the position of the building name.
     * @param buildingName name of the building
     * @return change in layout position
     */

    public double changeInPositionOfName(String buildingName) {
        int changeInPosition = 0;

        if (buildingName.toCharArray().length > 14) {
            changeInPosition = 2 + ((buildingName.toCharArray().length - 14) * (-8));
        }
        if (buildingName.toCharArray().length < 14 && buildingName.toCharArray().length > 10) {
            changeInPosition = ((14 - buildingName.toCharArray().length) * (8));
        }
        if (buildingName.toCharArray().length == 10) {
            changeInPosition = 10;
        }
        if (buildingName.toCharArray().length < 10 && buildingName.toCharArray().length > 5) {
            changeInPosition = (10 - buildingName.toCharArray().length) * 11;
        }
        if (buildingName.toCharArray().length == 5) {
            changeInPosition = 40;
        }
        if (buildingName.toCharArray().length < 5) {
            changeInPosition = (10 - buildingName.toCharArray().length) * 6;
        }
        return changeInPosition;
    }

    /**
     * Add the first three building to the ScrollPane.
     *
     * @param buildingsList list with the first 3 buildings
     * @return the index of the last building
     */
    public int addFirstThreeBuildings(List<Buildings> buildingsList) {
        double changeInPosition = 0;
        int i = 0;
        int layoutY = 220;

        for (Buildings e : buildingsList) {
            String buildingName = changeInPositionOfBuildingName(e);
            changeInPosition = changeInPositionOfName(buildingName);

            addLabelSidePane(56, layoutY + 28, "-" + buildingName);
            layoutY = layoutY + 28;

            if (i == 0) {
                addLabelScrollPane(82 + changeInPosition, 14, buildingName, "A");
                addLabelScrollPane(98, 41, "Building: " + e.getBuilding_number(), null);
                addImageToScrollPane(e, 4, 74);
            }
            if (i == 1) {

                addLabelScrollPane(387 + changeInPosition, 14, buildingName, "B");
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
     * Loading all needed scene controllers when the fxml is loaded.
     *
     * @param location  Link to location
     * @param resources Resource Bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRole();
        setFilter(false);
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
        double changeInPosition = 0;
        int checkNumberOfBuildings = addFirstThreeBuildings(buildingsList);

        if (checkNumberOfBuildings < 2) {
            return;
        }

        i = checkNumberOfBuildings;

        Label last = null;
        i++;

        for (int j = i; j < buildingsList.size(); j++) {
            String buildingName = changeInPositionOfBuildingName(buildingsList.get(j));
            changeInPosition = changeInPositionOfName(buildingName);

            addLabelSidePane(56, layoutY + 28, "-" + buildingName);
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

    /**
     * Method to load the user page.
     *
     * @param event on mouse click
     * @throws IOException Exception if can't find user page
     */
    public void userPage(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserPage.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("images/favicon.png"));
        stage.show();

        Stage stage1 = (Stage) scene1.getScene().getWindow();
        stage1.close();
    }
}
