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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Rooms;


public class RoomMenuController implements Initializable {
    public static List<Rooms> rooms;
    private static String room_id;
    private static int building_id;

    public static int getBuildingId() {
        return building_id;
    }

    ServerCommunication con = new ServerCommunication();
    HelperController helper = new HelperController();
    @FXML
    private AnchorPane pane1;
    @FXML
    private Rectangle rect1;
    @FXML
    private javafx.scene.control.ScrollPane scene1;
    @FXML
    private Pane sidePane;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private Pane rightPane;

    public static String getId() {
        return room_id;
    }

    /**
     * Method to initilize.
     * @param location Link to location
     * @param resources Resource Bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HelperController helper = new HelperController();
        addRole();
        helper.loadSidePane(sidePane);

        List<Rooms> allrooms = new ArrayList<>();
        try {
            allrooms = con.getRooms();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Working");
        if (!MainMenuController.getFilter()) {
            String buildingId = MainMenuController.getId();
            int builId = Integer.parseInt(buildingId);
            rooms = new ArrayList<>();
            for (int i = 0; i < allrooms.size(); i++) {
                System.out.println("Working2");
                if (allrooms.get(i).getAssociatedBuilding() == builId) {
                    rooms.add(allrooms.get(i));
                }
            }
        } else {
            rooms = MainMenuController.getFilterRooms();
        }
        System.out.println("running");

        rooms = roomShownByRole(rooms);
        if (rooms.isEmpty()) {
            System.out.println("No Rooms");
            Label label = new Label("No available rooms!");
            pane1.getChildren().add(label);
            label.setLayoutY(200);
            label.setLayoutX(300);
            label.setFont(Font.font("Arial Rounded MT Bold", 32));
            return;
        }

        for (int j = 0; j < rooms.size(); j++) {
            Rectangle last = null;
            String id1 = "";
            for (Node e : pane1.getChildren()) {
                if (e instanceof Rectangle) {
                    id1 = e.getId();
                    last = (Rectangle) e;
                }
            }

            if (id1.contains("A")) {
                Rectangle box = addBoxToScrollPane(344, last.layoutYProperty().getValue(), "B" + j);

                addLabelToScrollPane(394, box.layoutYProperty().getValue()
                        + 11, "Room ID:");

                addLabelToScrollPane(390, box.layoutYProperty().getValue()
                        + 70, "Capacity:");

                addLabelToScrollPane(390, box.layoutYProperty().getValue() + 40,
                        rooms.get(j).getRoomId());

                addLabelToScrollPane(422, box.layoutYProperty().getValue() + 100,
                        rooms.get(j).getChairs() + "");

                addBoxButton(344, last.layoutYProperty().getValue(), "B" + j);
            }
            if (id1.contains("B")) {
                Rectangle box = addBoxToScrollPane(630, last.layoutYProperty().getValue(), "C" + j);

                addLabelToScrollPane(680, box.layoutYProperty().getValue() + 11, "Room ID:");

                addLabelToScrollPane(676, box.layoutYProperty().getValue() + 70, "Capacity:");

                addLabelToScrollPane(676, box.layoutYProperty().getValue() + 40,
                        rooms.get(j).getRoomId());

                addLabelToScrollPane(708, box.layoutYProperty().getValue() + 100,
                        rooms.get(j).getChairs() + "");

                addBoxButton(630, last.layoutYProperty().getValue(), "C" + j);
            }
            if (id1.contains("C")) {
                Rectangle box = addBoxToScrollPane(58,
                        last.layoutYProperty().getValue() + 176, "A" + j);

                addLabelToScrollPane(108, box.layoutYProperty().getValue()
                        + 11, "Room ID:");

                addLabelToScrollPane(104, box.layoutYProperty().getValue()
                        + 70, "Capacity:");

                addLabelToScrollPane(104, box.layoutYProperty().getValue()
                                + 40,
                        rooms.get(j).getRoomId());

                addLabelToScrollPane(136, box.layoutYProperty().getValue()
                        + 100, rooms.get(j).getChairs() + "");
                addBoxButton(58,
                        last.layoutYProperty().getValue() + 176, "A" + j);
            }

        }

    }

    /**
     *Method to add rooms corresponding to user types.
     * @param list List of rooms before filtering
     * @return List of rooms
     */
    public List<Rooms> roomShownByRole(List<Rooms> list) {
        String user = MainSceneController.getRole();
        List<Rooms> result = new ArrayList<>();
        for (Rooms e : list) {
            if (user.equals("teacher")) {
                result.add(e);
            } else if (e.getType().equals("Study hall") && user.equals("student")) {
                result.add(e);
            }
        }
        return result;
    }

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
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

    /**
     *Add Label to chosen Pane.
     * @param layoutX chosen layout X
     * @param layoutY chosen layout Y
     * @param text text for the Label
     */
    public void addLabelToScrollPane(double layoutX, double layoutY, String text) {
        Label label = new Label(text);
        pane1.getChildren().add(label);
        label.setLayoutY(layoutY);
        label.setLayoutX(layoutX);
        label.setFont(Font.font("Arial Rounded MT Bold", 18));
    }

    /**
     *Add rectangle to the ScrollPane.
     * @param layoutX chosen layout X
     * @param layoutY cosen layout Y
     * @param id chosen id
     * @return the created Rectangle with right properties
     */
    public Rectangle addBoxToScrollPane(double layoutX, double layoutY, String id) {
        Rectangle box = new Rectangle(188, 136);

        box.arcHeightProperty().setValue(30.0);
        box.arcWidthProperty().setValue(30.0);
        box.layoutXProperty().setValue(layoutX);
        box.layoutYProperty().setValue(layoutY);
        box.fillProperty().setValue(Color.valueOf("white"));
        box.setStroke(Color.valueOf("#00A6D6"));
        box.strokeWidthProperty().setValue(2);
        box.setVisible(true);
        box.setId(id);
        box.setOnMouseClicked(event -> {
            try {
                roomChosen(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pane1.getChildren().add(box);
        return box;
    }

    /**
     *Add new rectangle to the chosen place.
     * @param layoutX chosen layout X
     * @param layoutY chosen layout Y
     * @param id chosen id
     */
    public void addBoxButton(double layoutX, double layoutY, String id) {
        Rectangle box = new Rectangle(188, 136);

        box.arcHeightProperty().setValue(30.0);
        box.arcWidthProperty().setValue(30.0);
        box.layoutXProperty().setValue(layoutX);
        box.layoutYProperty().setValue(layoutY);
        box.fillProperty().setValue(Color.valueOf("transparent"));
        box.setId(id);
        box.setOnMouseClicked(event -> {
            try {
                roomChosen(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pane1.getChildren().add(box);
    }

    /**
     * Method for campus map to pop up.
     * @param event Clicking on 'Campus Map'
     * @throws IOException Exception if can't find campus map scene
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
     * Method to go back to previous page.
     * @param event Clicking on 'Go Back'
     * @throws IOException Exception if can't find main menu scene
     */
    public void goBack(Event event) throws IOException {
        MainMenuController.setFilter(false);
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainMenu.fxml", mainScreen);
    }

    /**
     * Save all needed properties for the chosen room.
     * @param event on mouse click
     * @throws IOException Exception if can't find Reservation room scene
     */
    public void roomChosen(Event event) throws IOException {
        String str = event.getSource().toString();
        String[] temp = str.split(" ");
        String newTemp = "";
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("id=")) {
                newTemp = temp[i];
            }
        }
        String[] arrId = newTemp.split("=");
        String temp2 = arrId[1];
        temp2 = temp2.substring(1, temp2.length() - 1);

        int roomIndex = Integer.parseInt(temp2);

        room_id = rooms.get(roomIndex).getRoomId();
        building_id = rooms.get(roomIndex).getAssociatedBuilding();

        //String buildingId = MainMenuController.getId().substring(1);

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/ReservationRoom.fxml", mainScreen);
    }
}
