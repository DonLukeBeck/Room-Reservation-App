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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;

public class FoodReservationCompleted implements Initializable {
    ServerCommunication con = new ServerCommunication();
    @FXML
    private AnchorPane pane;
    @FXML
    private Button scene;
    @FXML
    private Pane sidePane;

    private static String name;


    public String getName() {   return name;    }


    @Override
    public void initialize (URL location, ResourceBundle resources) {
        HelperController helperController = new HelperController();
        helperController.loadSidePane(sidePane);

        List<Buildings> buildingsList = null;

        try {
            buildingsList = con.getBuildings();
        }   catch (IOException e){
            e.printStackTrace();
        }

        Label builId = new Label(FoodSlots.getBuilding());
        builId.setFont(Font.font("Arial Rounded MT Bold", 24));
        builId.layoutYProperty().setValue(400);
        builId.layoutXProperty().setValue(390);
        builId.textFillProperty().setValue(Color.valueOf("#ffc500"));
        pane.getChildren().add(builId);

        for (Buildings e : buildingsList) {
            if (e.getBuilding_number() == Integer.parseInt(FoodSlots.getBuilding())) {
                name = e.getName();
                System.out.println(name);
            }
        }
        String[] nameA = name.split("\\(");
        Label builname = new Label(nameInProperFormat(nameA[0]));
        pane.getChildren().add(builname);
        builname.layoutYProperty().setValue(470);
        builname.layoutXProperty().setValue(440);
        builname.setFont(Font.font("Arial Rounded MT Bold", 24));
        builname.textFillProperty().setValue(Color.valueOf("ffc500"));

        Label date = new Label(FoodSlots.getDate());
        date.layoutYProperty().setValue(540);
        date.layoutXProperty().setValue(345);
        date.setFont(Font.font("Arial Rounded MT Bold", 24));
        date.textFillProperty().setValue(Color.valueOf("#ffc500"));
        pane.getChildren().add(date);

        Label timeslot = new Label(FoodSlots.getTimeslot());
        timeslot.layoutYProperty().setValue(610);
        timeslot.layoutXProperty().setValue(390);
        timeslot.setFont(Font.font("Arial Rounded MT Bold", 24));
        timeslot.textFillProperty().setValue(Color.valueOf("#ffc600"));
        pane.getChildren().add(timeslot);

        Label orderedDish = new Label(FoodMenuController.getDishesName());
        orderedDish.layoutYProperty().setValue(680);
        orderedDish.layoutXProperty().setValue(345);
        orderedDish.setFont(Font.font("Arial Rounded MT Bold", 24));
        orderedDish.textFillProperty().setValue(Color.valueOf("ffc600"));
        pane.getChildren().add(orderedDish);
    }

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
     *Method for campus map to pop up.
     * @param event Clicking on 'Campus Map'
     * @throws IOException
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
     *Method to go back to previous page.
     * @param event Clicking on 'Go Back'
     * @throws IOException
     */
    public void goToMainMenu(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainMenu.fxml", pane);
    }


}
