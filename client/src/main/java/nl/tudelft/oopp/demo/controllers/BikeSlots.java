package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;


public class BikeSlots implements Initializable {
    public static List<SlotReservation> allSlots = new ArrayList<>();
    private static String building;
    private static String date;
    private static String timeslot;
    ServerCommunication con = new ServerCommunication();
    HelperController helper = new HelperController();
    UserServerCommunication send = new UserServerCommunication();
    @FXML
    private AnchorPane slots;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private javafx.scene.control.Button reserveScene;
    @FXML
    private Pane sidePane;
    @FXML
    private Pane rightPane;
    @FXML
    private Label exception;

    /**
     * Method to return building.
     *
     * @return Building
     */
    public static String getBuilding() {
        return building;
    }

    /**
     * Method to return Date.
     *
     * @return Date
     */
    public static String getDate() {
        return date;
    }

    /**
     * Method to get the timeslot.
     *
     * @return Timeslot
     */
    public static List<SlotReservation> getTimeslots() {
        return allSlots;
    }

    /**
     * Extract id from string.
     *
     * @param str provided String
     * @return id
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

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }

    public void openResources(Event event) throws IOException {
        helper.openResources();
    }

    /**
     * Method for campus map to pop up.
     *
     * @param event Clicking on campus map
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
     * If the chosen date is today find the local time.
     *
     * @return Local time in double or 4 if the chosen date in not today
     */
    public double closeSlotsForLocalTime() {
        int chosenDay = BikeReservationMenu.getDay();
        int chosenMonth = BikeReservationMenu.getMonth();
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

    /**
     * Finds rectangle.
     *
     * @param id id of rectangle
     * @return the rectangle
     */
    public Rectangle findRectangle(String id) {
        for (Node k : slots.getChildren()) {
            if (k instanceof Rectangle && id.equals(getTimeSlotFromID(k.toString()))) {
                return (Rectangle) k;
            }
        }
        return null;
    }

    /**
     * Get all information from the chosen time slot.
     *
     * @param event on mouse click
     * @throws IOException when can not load ReservationBikeCompleted
     */
    public void timeSlot(Event event) throws IOException {
        building = MainMenuController.getId();

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

        if (event.getSource() instanceof Rectangle) {
            if (!((Rectangle) event.getSource())
                    .fillProperty().getValue().equals(Color.valueOf("#ffbf00"))) {
                ((Rectangle) event.getSource()).fillProperty().setValue(Color.valueOf("#ffbf00"));
                allSlots.add(new SlotReservation(MainSceneController.getUser(), timeslot + ":00",
                        date, Integer.parseInt(building)));
            } else {
                removeSlotFromList(getTimeSlotFromID(event.getSource().toString()));
                ((Rectangle) event.getSource()).fillProperty().setValue(Color.BLUE);
            }
        } else if (event.getSource() instanceof Label) {
            Rectangle chosen = findRectangle(getTimeSlotFromID(event.getSource().toString()));
            if (!chosen.fillProperty().getValue().equals(Color.valueOf("#ffbf00"))) {
                chosen.fillProperty().setValue(Color.valueOf("#ffbf00"));
                allSlots.add(new SlotReservation(MainSceneController.getUser(), timeslot + ":00",
                        date, Integer.parseInt(building)));
            } else {
                removeSlotFromList(getTimeSlotFromID(event.getSource().toString()));
                chosen.fillProperty().setValue(Color.BLUE);
            }
        }
    }

    /**
     * Method to initialize.
     *
     * @param location  Url to image location
     * @param resources Resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HelperController helper = new HelperController();
        helper.loadSidePane(sidePane);
        addRole();

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
        List<Buildings> buildingsList = new ArrayList<>();
        int layoutY = 220;
        try {
            buildingsList = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Buildings e : buildingsList) {
            int capsCount = 0;
            int changeInPosition = 0;
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

        List<Buildings> list = null;

        try {
            list = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Reservations> reservations = null;
        try {
            reservations = con.getReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Reservations> reservationOnChosenDate = new ArrayList<>();
        for (Reservations e : reservations) {
            if (e.getDate().toString().equals(date) && e.getBikeReserved() != 0) {
                reservationOnChosenDate.add(e);
            }
        }
        Time closed = null;
        Time open = null;

        for (Buildings e : list) {
            if (e.getBuilding_number() == Integer.parseInt(MainMenuController.getId())) {
                open = e.getOpeningHours();
                closed = e.getClosingHours();
                buildingavailablebikes = e.getNumber_of_bikes();
                break;
            }
        }

        String opentime = open.toString().substring(0, 5);
        String closingtime = closed.toString().substring(0, 5);
        String[] opening = opentime.split(":");
        String[] closing = closingtime.split(":");
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
        double localTime = closeSlotsForLocalTime();
        if (start < localTime) {
            start = localTime;
        }
        System.out.println(buildingavailablebikes);
        // if (!reservationOnChosenDate.isEmpty()) {
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
                String[] arrId = newTemp.split("=");
                String temp2 = arrId[1];
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
            if (k instanceof Label) {
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

                for (Reservations t : reservationOnChosenDate) {
                    String reservationSlot = t.getTimeslot().toString();
                    reservationSlot = reservationSlot.substring(0, 5);
                    if (reservationSlot.substring(0, 5).equals(firsttime[0])) {
                        availablebikes--;
                    }
                }

                if (availablebikes <= 0) {
                    k.disableProperty().setValue(true);
                }

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
     * Reserves slots.
     *
     * @param actionEvent - event on mouse click
     * @throws IOException Exception if can't find complete reservation scene
     */
    public void reserveSlots(ActionEvent actionEvent) throws IOException {
        if (allSlots.size() > 4) {
            exception.setVisible(true);
            return;
        }
        for (SlotReservation e : allSlots) {
            send.bikeReservation(e.getUserSlot(), e.getTimeslotSlot(),
                    e.getDateSlot(), e.getBuildingSlot());
        }
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/ReservationBikeCompleted.fxml", mainScreen);
    }

    /**
     * Removes a slot from the list.
     *
     * @param id the id of the slot
     */
    public void removeSlotFromList(String id) {
        SlotReservation remove = null;
        for (SlotReservation e : allSlots) {
            if (e.getTimeslotSlot().equals(id + ":00")) {
                remove = e;
            }
        }
        allSlots.remove(remove);
    }

    /**
     * Method for go back button.
     *
     * @param event Clicking on go back
     * @throws IOException when can not load ReservationBike
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/ReservationBike.fxml", mainScreen);
    }

    public class SlotReservation {
        private String user;
        private String date;
        private String timeslot;
        private int building;
        private String room;

        /**
         * Reservation for a slot.
         *
         * @param user     the user reserving
         * @param timeslot timeslot
         * @param date     date of reservation
         * @param building building id
         */
        public SlotReservation(String user, String timeslot,
                               String date, int building) {
            this.user = user;
            this.date = date;
            this.timeslot = timeslot;
            this.building = building;
        }

        public String getUserSlot() {
            return this.user;
        }

        public String getDateSlot() {
            return this.date;
        }

        public String getTimeslotSlot() {
            return this.timeslot;
        }

        public int getBuildingSlot() {
            return this.building;
        }
    }

}