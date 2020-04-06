package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;

public class FoodReservationCompleted implements Initializable {
    private static String name;
    ServerCommunication con = new ServerCommunication();
    HelperController helper = new HelperController();
    @FXML
    private AnchorPane pane;
    @FXML
    private Button scene;
    @FXML
    private Pane sidePane;
    @FXML
    private Pane rightPane;

    public String getName() {
        return name;
    }


    /**
     * Method for initializing pane, displaying all information on dish:
     * building ID, building name, date, time slot, dish.
     * @param location The location used to resolve relative paths for the root
     *                object, or null if the location is not known
     * @param resources The resources used to localize the root object, or null
     *                 if the root object was not localized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HelperController helperController = new HelperController();
        helperController.loadSidePane(sidePane);
        addRole();

        List<Buildings> buildingsList = null;

        try {
            buildingsList = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addLabelToPane(405, 400, FoodSlots.getBuilding());

        for (Buildings e : buildingsList) {
            if (e.getBuilding_number() == Integer.parseInt(FoodSlots.getBuilding())) {
                name = e.getName();
            }
        }
        String[] nameA = name.split("\\(");
        addLabelToPane(445, 470, nameInProperFormat(nameA[0]));

        addLabelToPane(345, 540, FoodSlots.getDate());

        addLabelToPane(390, 610, FoodSlots.getTimeslot());

        addLabelToPane(345, 680, FoodMenuController.getDishesName());

    }

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }

    /**
     * Method to add a label to pane.
     *
     * @param layoutX x-value of where the label will be at
     * @param layoutY y-value of where the label will be at
     * @param text String displayed in the label
     */
    public void addLabelToPane(double layoutX, double layoutY, String text) {
        Label label = new Label(text);
        label.setLayoutY(layoutY);
        label.setLayoutX(layoutX);
        label.setFont(Font.font("Arial Rounded MT Bold", 24));
        label.textFillProperty().setValue(Color.valueOf("#ffc500"));
        pane.getChildren().add(label);
    }

    /**
     * Method to get the name of a building in a proper format.
     *
     * @param name name not in proper format
     * @return String of name in proper format
     */
    public String nameInProperFormat(String name) {
        String result = "";
        if (name.toCharArray().length > 50) {
            for (char a : name.toCharArray()) {
                if (Character.isUpperCase(a)) {
                    result = result + a;
                }
            }
        } else {
            result = name;
        }
        return result;
    }

    /**
     * Method for campus map to pop up.
     *
     * @param event Clicking on 'Campus Map'
     * @throws IOException Exception if can't find campus map
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

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }

    /**
     * Method to go back to previous page.
     *
     * @param event Clicking on 'Go Back'
     * @throws IOException Exception if can't find main menu page
     */
    public void goToMainMenu(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainMenu.fxml", pane);
    }

    public void paneExit(Event event) throws IOException {
        helper.exit(pane);
    }

    public void paneLogOut(Event event) throws IOException {
        helper.logOut(pane);
    }

    public void paneUserProfile(Event event) throws IOException {
        helper.userProfile(pane);
    }
}
