package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;


public class TimeSlotsController implements Initializable {
    private static int building;
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
    @FXML
    private Pane rightPane;

    /**
     * Method to get Building.
     *
     * @return Building
     */
    public static int getBuilding() {
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
     * Method for getting timeslot in proper format from string.
     *
     * @param str used string to get timeslot from
     * @return timeslot in right format
     */
    public static String getTimeSlotFromID(String str) {
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
        return timeslot;
    }

    /**
     * Find end and start time in proper format.
     *
     * @param open   Time when building opens
     * @param closed Time when building closes
     * @return array with the opening and closing time in needed format
     */
    public static double[] getEndAndStart(Time open, Time closed) {
        String openTime = open.toString().substring(0, 5);
        String closingTime = closed.toString().substring(0, 5);
        String[] opening = openTime.split(":");
        String[] closing = closingTime.split(":");
        double start = Integer.parseInt(opening[0]);
        double end = Integer.parseInt(closing[0]);
        if (end < 6) {
            end = end + 24;
        }

        if (opening[1].equals("30")) {
            start = start + 0.5;
        }

        if (closing[1].equals("30")) {
            end = end + 0.5;
        }

        double[] endAndStart = new double[2];
        endAndStart[0] = end;
        endAndStart[1] = start;

        return endAndStart;
    }

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }

    /**
     * Method to pop up campus map.
     *
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
        stage.getIcons().add(new Image("images/favicon.png"));
        stage.show();
    }

    /**
     * If the chosen date is today find the local time.
     * @return Local time in double or 4 if the chosen date in not today
     */
    public double closeSlotsForLocalTime() {
        int chosenDay = RoomReservationMenu.getDay();
        int chosenMonth = RoomReservationMenu.getMonth();

        Calendar c = Calendar.getInstance();
        int dayNow = c.get(Calendar.DAY_OF_MONTH);
        int monthNow = c.get(Calendar.MONTH);

        double hour = 4;
        if (chosenDay == dayNow && chosenMonth == monthNow) {
            LocalTime localtime = java.time.LocalTime.now();
            hour = localtime.getHour();
            if (localtime.getMinute() <= 20) {
                hour += 0.5;
            } else if (localtime.getMinute() <= 50) {
                hour += 1;
            } else {
                hour += 1.5;
            }
            return hour;
        }
        return hour;
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
     * Chosen time slot.
     *
     * @param event on mouse click
     * @throws IOException Exception if can't find complete reservation scene
     */
    public void timeSlot(Event event) throws IOException {
        building = RoomMenuController.getBuildingId();
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

        timeslot = getTimeSlotFromID(event.getSource().toString());

        con.roomReservation(MainSceneController.getUser(), timeslot + ":00",
                date, building, room);

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/CompleteReservation.fxml", mainScreen);
    }

    /**
     * Disable not suitable slots.
     *
     * @param allSuitableRes list with reservation for chosen date
     * @param start          time when building opens
     * @param end            time when building closes
     */
    public void disableNotSuitableSlots(List<Reservations> allSuitableRes,
                                        double start, double end) {

        double localTime = closeSlotsForLocalTime();
        if (start < localTime) {
            start = localTime;
        }

        for (Node k : slots.getChildren()) {
            if (k instanceof Rectangle) {
                String time = getTimeSlotFromID(k.toString());
                String[] firstTime = time.split(" ");
                String[] seperateHandM = firstTime[0].split(":");
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
                        if (t.getTimeslot().toString().substring(0, 5).equals(firstTime[0])) {
                            ((Rectangle) k).fillProperty().setValue(Color.valueOf("red"));
                            k.disableProperty().setValue(true);
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to change date format.
     *
     * @return Date
     */
    public String returnDateInSuitableFormat() {
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
        return date;
    }

    /**
     * Method to initialize.
     *
     * @param location  The location used to resolve relative paths for the root object,
     *                  or null if the location is not known
     * @param resources The resources used to localize the root object,
     *                  or null if the root object was not localized
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

        List<Reservations> allReservations = null;

        try {
            allReservations = con.getReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Time closed = null;
        Time open = null;

        for (Buildings e : list) {
            if (e.getBuilding_number() == RoomMenuController.getBuildingId()) {
                System.out.println("Works");
                open = e.getOpeningHours();
                closed = e.getClosingHours();
                break;
            }
        }

        room = RoomMenuController.getId();

        date = returnDateInSuitableFormat();

        List<Reservations> allSuitableRes = new ArrayList<>();

        for (Reservations e : allReservations) {
            if (e.getDate().toString().equals(date) && e.getRoomReserved() != null
                    && e.getRoomReserved().equals(room)) {
                allSuitableRes.add(e);
            }
        }

        double[] endAndStart = getEndAndStart(open, closed);

        disableNotSuitableSlots(allSuitableRes, endAndStart[1], endAndStart[0]);
    }

    /**
     * Method to go back to previous page.
     *
     * @param event Clicking on 'Go Back'
     * @throws IOException on error in loading
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/ReservationRoom.fxml", mainScreen);
    }
}
