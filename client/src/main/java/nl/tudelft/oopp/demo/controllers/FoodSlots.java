package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;

public class FoodSlots implements Initializable {
    private static String building;
    private static String room;
    private static String date;
    private static String timeslot;
    UserServerCommunication con = new UserServerCommunication();
    HelperController helper = new HelperController();

    @FXML
    private AnchorPane slots;
    @FXML
    private javafx.scene.control.Button reserveScene;
    @FXML
    private Pane sidePane;
    @FXML
    private AnchorPane mainScreen;

    /**
     * Method to get building.
     *
     * @return Building
     */
    public static String getBuilding() {
        return building;
    }

    /**
     * Method to get Room.
     *
     * @return Room
     */
    public static String getRoom() {
        return room;
    }

    /**
     * Method to get Date.
     *
     * @return Date
     */
    public static String getDate() {
        return date;
    }

    /**
     * Method to get Timeslot.
     *
     * @return Timeslot
     */
    public static String getTimeslot() {
        return timeslot;
    }

    /**
     * Method to pop up campus map.
     *
     * @param event Clicking on 'campus map'
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
     * @param event
     * @throws IOException
     */
    public void timeSlot(Event event) throws IOException {
        building = MainMenuController.getId();
        room = null;

        Calendar defaultCalendar = Calendar.getInstance();

        int currentYear = defaultCalendar.get(Calendar.YEAR);
        int currentMonth = defaultCalendar.get(Calendar.MONTH);
        int currentDay = defaultCalendar.get(Calendar.DAY_OF_MONTH);

//        int checkDate = RoomReservationMenu.getDay();
//        int checkMonth = RoomReservationMenu.getMonth() + 1;
        String formatDate = currentDay + "";
        String formatMonth = currentMonth + "";

        if (currentDay < 10) {
            formatDate = "0" + currentDay;
        }
        if (currentMonth < 10) {
            formatMonth = "0" + currentMonth;
        }

        date = currentYear + "-" + formatMonth + "-" + formatDate;


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
        String[] arrId = newTemp.split("=");
        String temp2 = arrId[1];
        temp2 = temp2.substring(1, temp2.length() - 1);
        timeslot = temp2.replace('A', ':');

        String dishName = FoodMenuController.getDishesName();

        con.foodReservation(MainSceneController.getUser(), timeslot + ":00", date, Integer.parseInt(building), dishName);

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/ReservationFoodCompleted.fxml", mainScreen);
    }
    public void paneExit(Event event) throws IOException {
        helper.exit(mainScreen);
    }
    public void paneLogOut(Event event) throws  IOException {
        helper.logOut(mainScreen);
    }
    public void paneUserProfile(Event event) throws IOException {
        helper.userProfile(mainScreen);
    }

    /**
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Buildings> list = null;

        HelperController helper = new HelperController();
        helper.loadSidePane(sidePane);

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
                open = e.getOpeningHours();
                closed = e.getClosingHours();
                break;
            }
        }

        room = RoomMenuController.getId();
        /* int checkDate = RoomReservationMenu.getDay();
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
        */

//
//        List<Reservations> allSuitableRes = new ArrayList<>();
//
//        for (Reservations e : allReservations) {
//            if (e.getDate().toString().equals(date) && e.getRoomReserved() != null && e.getRoomReserved().equals(room)) {
//                allSuitableRes.add(e);
//            }
//        }

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
                String[] arrId = newTemp.split("=");
                String temp2 = arrId[1];
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

    /**
     * Method to go back.
     *
     * @param event Clicking on 'Go Back'
     * @throws IOException
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/FoodMenu.fxml", mainScreen);
    }
}
