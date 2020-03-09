package nl.tudelft.oopp.demo.controllers;

import com.sun.tools.javac.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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


public class TimeSlotsController implements Initializable {
    private static String building;
    private static String room;
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

    public static String getRoom() {
        return room;
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
        room = RoomMenuController.getId();
        int checkDate = RoomReservationMenu.getDay();
        int checkMonth = RoomReservationMenu.getMonth() + 1;
        String formatDate = checkDate + "";
        String formatMonth = checkMonth + "";

        if (checkDate < 10) {
            formatDate = "0" + checkDate;
        }
        if (checkMonth < 10) {
            formatMonth = "0" + checkMonth;
        }

        date = RoomReservationMenu.getYear() + "-" + formatMonth + "-" + formatDate;


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
        System.out.println(MainSceneController.getUser());

        con.reservation(MainSceneController.getUser(), timeslot+":00", date, room);

        Stage stage1 = (Stage) slot.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CompleteReservation.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Buildings> list = null;

        try {
            list = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Reservations> allReservations = null;

        try {
            allReservations = con.getReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Time closed = null;
        Time open = null;

        for (Buildings e : list) {
            if (e.getBuilding_number() == Integer.parseInt(MainMenuController.getId())) {
                System.out.println("Works");
                open = e.getOpening_hours();
                closed = e.getClosing_hours();
                break;
            }
        }

        room = RoomMenuController.getId();
        int checkDate = RoomReservationMenu.getDay();
        int checkMonth = RoomReservationMenu.getMonth() + 1;
        String formatDate = checkDate + "";
        String formatMonth = checkMonth + "";

        if (checkDate < 10) {
            formatDate = "0" + checkDate;
        }
        if (checkMonth < 10) {
            formatMonth = "0" + checkMonth;
        }

        date = RoomReservationMenu.getYear() + "-" + formatMonth + "-" + formatDate;

        List<Reservations> allSuitableRes = new ArrayList<>();

        for (Reservations e : allReservations) {
            System.out.println(date);
            System.out.println(e.getDate().toString());
            if (e.getDate().toString().equals(date) && e.getRoom_reserved() != null && e.getRoom_reserved().equals(room)) {
                allSuitableRes.add(e);
            }
        }
        System.out.println(allSuitableRes.toString());

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
                if (!allSuitableRes.isEmpty()) {
                    for (Reservations t : allSuitableRes) {
                        if (t.getTimeslot().toString().substring(0, 5).equals(firsttime[0])) {
                            ((Rectangle) k).fillProperty().setValue(Color.valueOf("red"));
                            k.disableProperty().setValue(true);
                        }
                    }
                }
            }
        }
    }

    public void GoBack(Event event) throws IOException {
        Stage stage1 = (Stage) ReserveScene.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/ReservationRoom.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}