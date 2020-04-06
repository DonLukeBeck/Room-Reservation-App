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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;

public class MainReservationMenuController implements Initializable {

    ServerCommunication con = new ServerCommunication();
    HelperController helper = new HelperController();

    @FXML
    private Pane sidePane;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private javafx.scene.control.Button reserveScene;
    @FXML
    private Pane rightPane;

    /**
     * Method for campus map to pop up.
     *
     * @param event Clicking on 'Campus Map'
     * @throws IOException Exception if can't find campus map page
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
     * Method to go to previous page.
     *
     * @param event Clicking on 'Go Back'
     * @throws IOException Exception if can't find Main menu page
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainMenu.fxml", mainScreen);
    }

    /**
     * Method to go to room reservation.
     *
     * @param event Clicking on 'Rooms'
     * @throws IOException Exception if can't find room menu page
     */
    public void goToRooms(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/RoomMenu.fxml", mainScreen);
    }

    /**
     * Method to go to bike reservation.
     *
     * @param event Clicking on 'Bikes'
     * @throws IOException Exception if can't find reservation bike page
     */
    public void goToBikes(Event event) throws IOException {
        int id = Integer.parseInt(MainMenuController.getId());
        HelperController helperController = new HelperController();
        if (con.getBuildingByName(id).getNumber_of_bikes() <= 0) {
            helperController.loadNextScene("/NoBikes.fxml", mainScreen);
        } else {
            helperController.loadNextScene("/ReservationBike.fxml", mainScreen);
        }
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

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }

    /**
     * Method to go to food reservation.
     *
     * @param event Clicking on 'Food'
     * @throws IOException Exception if can't find food menu page
     */
    public void goToFood(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/FoodMenu.fxml", mainScreen);
    }

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }

    /**
     * Method to initialize.
     *
     * @param location  Link to the location
     * @param resources Resource Bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRole();
        List<Buildings> buildings = new ArrayList<>();
        try {
            buildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int layoutY = 220;
        for (Buildings e : buildings) {
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
            Label sideBuilding = new Label("-" + buildingName);
            sideBuilding.setLayoutX(56);
            sideBuilding.setLayoutY(layoutY + 28);
            sideBuilding.setFont(Font.font("System", FontWeight.BOLD, 14));
            sideBuilding.setTextFill(Color.valueOf("white"));
            layoutY = layoutY + 28;
            sidePane.getChildren().add(sideBuilding);
        }
    }
}
