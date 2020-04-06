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

public class BikeReservationCompleted implements Initializable {
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
     * Method to initialize.
     *
     * @param location  Location of the picture
     * @param resources Resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRole();
        HelperController helper = new HelperController();
        helper.loadSidePane(sidePane);

        List<Buildings> list = null;

        try {
            list = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Label builId = new Label(BikeSlots.getBuilding());
        builId.setFont(Font.font("Arial Rounded MT Bold", 24));
        builId.layoutYProperty().setValue(400);
        builId.layoutXProperty().setValue(390);
        builId.textFillProperty().setValue(Color.valueOf("black"));
        pane.getChildren().add(builId);


        for (Buildings e : list) {
            if (e.getBuilding_number() == Integer.parseInt(BikeSlots.getBuilding())) {
                name = e.getName();
                System.out.println(name);
            }
        }
        String[] nameA = name.split("\\(");
        Label builname = new Label(nameInProperFormat(nameA[0]));
        pane.getChildren().add(builname);
        builname.layoutYProperty().setValue(470);
        builname.layoutXProperty().setValue(440);
        builname.setFont(Font.font("Arial Rounded MT Bold", 22));
        builname.textFillProperty().setValue(Color.valueOf("black"));

        Label date = new Label(BikeSlots.getDate());
        date.layoutYProperty().setValue(545);
        date.layoutXProperty().setValue(345);
        date.setFont(Font.font("Arial Rounded MT Bold", 24));
        date.textFillProperty().setValue(Color.valueOf("black"));
        pane.getChildren().add(date);

        Label timeslot = new Label(BikeSlots.getTimeslot());
        timeslot.layoutYProperty().setValue(615);
        timeslot.layoutXProperty().setValue(390);
        timeslot.setFont(Font.font("Arial Rounded MT Bold", 24));
        timeslot.textFillProperty().setValue(Color.valueOf("black"));
        pane.getChildren().add(timeslot);
    }

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }

    /**
     * Method for campus map to pop up.
     *
     * @param event Clicking on 'Campus Map'
     * @throws IOException when can not load CampusMap
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
     * Method to go back to previous page.
     *
     * @param event Clicking on 'Go Back'
     * @throws IOException when it can not the MainMenu page
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

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }

    public void openResources(Event event) throws IOException {
        helper.openResources();
    }

    /**
     * Method to format the name properly.
     *
     * @param name Name input
     * @return Name in proper format
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
}
