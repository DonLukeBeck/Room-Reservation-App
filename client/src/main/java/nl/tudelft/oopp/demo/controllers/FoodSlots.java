package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;

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
    @FXML
    private Pane rightPane;

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
     * Method for getting the starting hour using LocalTime.
     *
     * @return the hour used for the start of the building
     */
    public static double getLocalTime() {
        LocalTime localtime = java.time.LocalTime.now();
        double hour = localtime.getHour();
        if (localtime.getMinute() <= 20) {
            hour += 0.5;
        } else if (localtime.getMinute() <= 50) {
            hour += 1;
        } else {
            hour += 1.5;
        }
        return hour;
    }

    /**
     * Method for getting the opening and closing time of a building.
     *
     * @param start  the starting time of a building
     * @param closed the closing time of a building
     * @return the starting time and closing time in an array
     */
    public static double[] getEndAndStart(Time start, Time closed) {
        String openTime = start.toString().substring(0, 5);
        String closingTime = closed.toString().substring(0, 5);
        String[] opening = openTime.split(":");
        String[] closing = closingTime.split(":");

        double startTime = Integer.parseInt(opening[0]);
        double timeNow = getLocalTime();

        if (startTime < timeNow) {
            startTime = timeNow;
        } else if (opening[1].equals("30")) {
            startTime = startTime + 0.5;
        }

        double endTime = Integer.parseInt(closing[0]);

        if (endTime < 6) {
            endTime = endTime + 24;
        }
        if (closing[1].equals("30")) {
            endTime = endTime + 0.5;
        }

        double[] endAndStart = new double[2];
        endAndStart[0] = endTime;
        endAndStart[1] = startTime;

        return endAndStart;
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
     * Method to pop up campus map.
     *
     * @param event Clicking on 'campus map'
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

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }

    public void openResources(Event event) throws IOException {
        helper.openResources();
    }

    /**
     * Get all information from the chosen time slot.
     *
     * @param event on mouse click
     * @throws IOException when can not load ReservationFoodCompleted
     */
    public void timeSlot(Event event) throws IOException {
        building = MainMenuController.getId();
        room = null;

        Calendar defaultCalendar = Calendar.getInstance();

        int currentYear = defaultCalendar.get(Calendar.YEAR);
        int currentMonth = defaultCalendar.get(Calendar.MONTH) + 1;
        int currentDay = defaultCalendar.get(Calendar.DAY_OF_MONTH);

        String formatDate = currentDay + "";
        String formatMonth = currentMonth + "";

        if (currentDay < 10) {
            formatDate = "0" + currentDay;
        }
        if (currentMonth < 10) {
            formatMonth = "0" + currentMonth;
        }

        date = currentYear + "-" + formatMonth + "-" + formatDate;

        timeslot = getTimeSlotFromID(event.getSource().toString());
        String dishName = FoodMenuController.getDishesName();

        con.foodReservation(MainSceneController.getUser(), timeslot
                + ":00", date, Integer.parseInt(building), dishName);

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/ReservationFoodCompleted.fxml", mainScreen);
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

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }

    /**
     * Method for disabling all timeslots that aren't available.
     *
     * @param start start of enabled timeslots
     * @param end   end of enabled timeslots
     */
    public void disableNotSuitableSlots(double start, double end) {

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
            }
            if (k instanceof Label) {
                String time = getTimeSlotFromID(k.toString());
                String[] firstTime = time.split(" ");
                String[] seperateHandM = firstTime[0].split(":");
                double hours = Integer.parseInt(seperateHandM[0]);

                if (seperateHandM[1].equals("30")) {
                    hours = hours + 0.5;
                }
                if (hours < start || hours >= end) {
                    k.disableProperty().setValue(true);
                }
            }
        }
    }

    /**
     * Method for initializing timeslots.
     *
     * @param location  The location used to resolve relative paths for the root object,
     *                  or null if the location is not known
     * @param resources The resources used to localize the root object,
     *                  or null if the root object was not localized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HelperController helper = new HelperController();
        helper.loadSidePane(sidePane);
        addRole();

        List<Buildings> list = null;
        try {
            list = con.getBuildings();
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

        double[] endAndStart = getEndAndStart(open, closed);
        disableNotSuitableSlots(endAndStart[1], endAndStart[0]);
    }

    /**
     * Method to go back.
     *
     * @param event Clicking on 'Go Back'
     * @throws IOException when can not load FoodMenu
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/FoodMenu.fxml", mainScreen);
    }
}
