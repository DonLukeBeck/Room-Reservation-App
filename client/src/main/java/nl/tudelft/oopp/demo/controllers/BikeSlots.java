package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;


public class BikeSlots implements Initializable {
    private static String building;
    private static String date;
    private static String timeslot;

    ServerCommunication con = new ServerCommunication();

    @FXML
    private AnchorPane slots;
    @FXML
    private javafx.scene.control.Button ReserveScene;

    public static String getBuilding() {
        return building;
    }

    public static String getDate() {
        return date;
    }

    public static String getTimeslot() {
        return timeslot;
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

    public void timeSlot(Event event) throws IOException {
        building = MainMenuController.getId();

        Rectangle slot = (Rectangle) event.getSource();
        if (slot.fillProperty().getValue().equals(Color.valueOf("#ffc500"))) {
            slot.fillProperty().setValue(Color.valueOf("blue"));
        } else {
            slot.fillProperty().setValue(Color.valueOf("#ffc500"));
        }

        String str = event.getSource().toString();
        String[] temp = str.split(" ");
        String newTemp = "";
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("id=")) {
                newTemp = temp[i];
            }
        }
        String[] ArrId = newTemp.split("=");
        String temp2 = ArrId[1];
        temp2 = temp2.substring(1, temp2.length() - 1);
        timeslot = temp2.replace('A', ':');
        System.out.println(timeslot);

        Stage stage1 = (Stage) slot.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/ReservationBikeCompleted.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int checkDate = BikeReservationMenu.getDay();
        int checkMonth = BikeReservationMenu.getMonth() + 1;
        String formatDate = checkDate + "";
        String formatMonth = checkMonth + "";

        int availablebikes = 0;
        int buildingavailablebikes = 0;

        if (checkDate < 10) {
            formatDate = "0" + checkDate;
        }
        if (checkMonth < 10) {
            formatMonth = "0" + checkMonth;
        }

        date = BikeReservationMenu.getYear() + "-" + formatMonth + "-" + formatDate;

        List<Buildings> list = null;

        try {
            list = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

/*        List<Bikes> bikes = null;

        try {
            bikes = con.getBikes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Bikes e : bikes) {
            if (Integer.parseInt(MainMenuController.getId()) == e.getBikeBuilding()) {
                buildingavailablebikes = e.getBikeAvailability();
                break;
           }
        }
*/
        List<Reservations> reservations = null;
        try {
            reservations = con.getReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Reservations> reservationOnChosenDate = new ArrayList<>();
        for (Reservations e : reservations) {
            if (e.getDate().toString().equals(date) && e.getBike_reserved() != 0) {
                reservationOnChosenDate.add(e);
                System.out.println("Added");
            }
        }
        Time closed = null;
        Time open = null;

        for (Buildings e : list) {
            if (e.getBuilding_number() == Integer.parseInt(MainMenuController.getId())) {
                open = e.getOpening_hours();
                closed = e.getClosing_hours();
                break;
            }
        }

        String opentime = open.toString().substring(0, 5);
        String closingtime = closed.toString().substring(0, 5);
        String[] opening = opentime.split(":");
        String[] closing = closingtime.split(":");
        double start = Integer.parseInt(opening[0]);
        double end = Integer.parseInt(closing[0]);

        if (opening[1].equals("30")) {
            start = start + 0.5;
        }

        if (closing[1].equals("30")) {
            end = end + 0.5;
        }
        if (!reservationOnChosenDate.isEmpty()) {
            for (Node k : slots.getChildren()) {
                availablebikes = buildingavailablebikes;
                if (k instanceof Rectangle) {
                    String str = k.toString();
                    String[] temp = str.split(" ");
                    String newTemp = "";
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i].contains("id=")) {
                            newTemp = temp[i];
                            break;
                        }
                    }
                    String[] ArrId = newTemp.split("=");
                    String temp2 = ArrId[1];
                    temp2 = temp2.substring(1, temp2.length() - 1);
                    String time = temp2.replace('A', ':');

                    String[] firsttime = time.split(" ");
                    String[] seperateHandM = firsttime[0].split(":");

                    for (Reservations t : reservationOnChosenDate) {
                        String reservationSlot = t.getTimeslot().toString();
                        reservationSlot = reservationSlot.substring(0, 5);
                        if (reservationSlot.substring(0, 5).equals(firsttime[0])) {
                            availablebikes--;
                        }
                    }

                    if (availablebikes <= 0) {
                        ((Rectangle) k).fillProperty().setValue(Color.valueOf("red"));
                        k.disableProperty().setValue(true);
                    }

                    double hours = Integer.parseInt(seperateHandM[0]);

                    if (seperateHandM[1].equals("30")) {
                        hours = hours + 0.5;
                    }
                    if (hours < start || hours >= end) {
                        ((Rectangle) k).fillProperty().setValue(Color.valueOf("#827c7c"));
                        k.disableProperty().setValue(true);
                    }
                }
            }
        } else {
            for (Node k : slots.getChildren()) {
                if (k instanceof Rectangle) {
                    String str = k.toString();
                    String[] temp = str.split(" ");
                    String newTemp = "";
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i].contains("id=")) {
                            newTemp = temp[i];
                            break;
                        }
                    }
                    String[] ArrId = newTemp.split("=");
                    String temp2 = ArrId[1];
                    temp2 = temp2.substring(1, temp2.length() - 1);
                    String time = temp2.replace('A', ':');

                    String[] firsttime = time.split(" ");
                    String[] seperateHandM = firsttime[0].split(":");


                    double hours = Integer.parseInt(seperateHandM[0]);

                    if (seperateHandM[1].equals("30")) {
                        hours = hours + 0.5;
                    }
                    if (hours < start || hours >= end) {
                        ((Rectangle) k).fillProperty().setValue(Color.valueOf("#827c7c"));
                        k.disableProperty().setValue(true);
                    }
                }
            }
        }
    }

    public void GoBack(Event event) throws IOException {
        Stage stage1 = (Stage) ReserveScene.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/ReservationBike.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}