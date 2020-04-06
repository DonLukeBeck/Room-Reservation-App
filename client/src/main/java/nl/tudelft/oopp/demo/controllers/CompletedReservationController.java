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

public class CompletedReservationController implements Initializable {
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
    private AnchorPane mainScreen;
    @FXML
    private Pane rightPane;

    /**
     * Method to get Name.
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }
    public void openResources(Event event) throws IOException {
        helper.openResources();
    }

    /**
     * Loading elements when the scene is opened.
     *
     * @param location  location of the chosen fxml
     * @param resources resources needed
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRole();
        List<Buildings> list = null;
        HelperController helper = new HelperController();
        helper.loadSidePane(sidePane);

        try {
            list = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addText(390, 400, "" + TimeSlotsController.getBuilding());

        for (Buildings e : list) {
            if (e.getBuilding_number() == TimeSlotsController.getBuilding()) {
                name = e.getName();
                System.out.println(name);
            }
        }
        String[] nameA = name.split("\\(");

        addText(440, 470, nameInProperFormat(nameA[0]));
        addText(370, 540, TimeSlotsController.getRoom());
        addText(330, 610, TimeSlotsController.getDate());
        addText(380, 680, TimeSlotsController.getTimeslot());

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

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }

    /**
     * Change String to proper format.
     * @param name String to change
     * @return String in needed format
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
     * Create new Label.
     * @param layoutX layout X chosen
     * @param layoutY layout Y chosen
     * @param text String to add to the label.
     */

    public void addText(double layoutX, double layoutY, String text) {
        Label label = new Label(text);
        label.layoutYProperty().setValue(layoutY);
        label.layoutXProperty().setValue(layoutX);
        label.setFont(Font.font("Arial Rounded MT Bold", 24));
        label.textFillProperty().setValue(Color.valueOf("black"));
        pane.getChildren().add(label);
    }

    /**
     * Method to pop up campus map.
     *
     * @param event Clicking on campus map button
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
     * Method to go back to main menu.
     *
     * @param event on mouse click
     * @throws IOException when can not load MainMenu
     */
    public void goToMainMenu(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainMenu.fxml", pane);
    }
}