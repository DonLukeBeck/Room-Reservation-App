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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;


public class BikeReservationCompleted implements Initializable {
    private static String name;
    ServerCommunication con = new ServerCommunication();
    @FXML
    private AnchorPane pane;
    @FXML
    private Button scene;

    public String getName() {
        return name;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        builId.textFillProperty().setValue(Color.valueOf("#ffc500"));
        pane.getChildren().add(builId);


        for (Buildings e : list) {
            if (e.getBuilding_number() == Integer.parseInt(BikeSlots.getBuilding())) {
                name = e.getName();
                System.out.println(name);
            }
        }
        Label builname = new Label(name);
        pane.getChildren().add(builname);
        builname.layoutYProperty().setValue(470);
        builname.layoutXProperty().setValue(440);
        builname.setFont(Font.font("Arial Rounded MT Bold", 22));
        builname.textFillProperty().setValue(Color.valueOf("#ffc500"));

        Label date = new Label(BikeSlots.getDate());
        date.layoutYProperty().setValue(545);
        date.layoutXProperty().setValue(345);
        date.setFont(Font.font("Arial Rounded MT Bold", 24));
        date.textFillProperty().setValue(Color.valueOf("#ffc500"));
        pane.getChildren().add(date);

        Label timeslot = new Label(BikeSlots.getTimeslot());
        timeslot.layoutYProperty().setValue(615);
        timeslot.layoutXProperty().setValue(390);
        timeslot.setFont(Font.font("Arial Rounded MT Bold", 24));
        timeslot.textFillProperty().setValue(Color.valueOf("#ffc600"));
        pane.getChildren().add(timeslot);
    }

    public void CampusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void goToMainMenu(Event event) throws IOException {
        Stage stage1 = (Stage) scene.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}