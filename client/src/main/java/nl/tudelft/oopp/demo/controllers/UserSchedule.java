package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.UserEvent;
import nl.tudelft.oopp.demo.entities.Users;

public class UserSchedule implements Initializable {
    private static int Fmonth;
    private static int Fyear;
    private static int FDay;
    private static int MonthNow;
    private static int DayNow;
    public String reservationDate;
    ServerCommunication con = new ServerCommunication();
    HelperController helper = new HelperController();
    @FXML
    private ChoiceBox monthChoice;
    @FXML
    private AnchorPane mon;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private Pane sidePane;
    @FXML
    private Pane rightPane;

    /**
     * Method to get month.
     * @return Return month integer
     */
    public static int getMonth() {
        return Fmonth;
    }

    /**
     * Method to get the Year.
     * @return The year
     */
    public static int getYear() {
        return Fyear;
    }

    /**
     * Method to get the day.
     * @return The day
     */
    public static int getDay() {
        return FDay;
    }

    /**
     *Method for 'campus map' button.
     * @param event Event that triggers the campus map pop-up, in this case clicking on campus map
     * @throws IOException Exception if can't find campus map scene
     */
    public void campusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("favicon.png"));
        stage.show();
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

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }


    /**
     *Method for 'go back' button.
     * @param event Clicking on the go back button
     * @throws IOException Exception if can't find user page scene
     */
    public void goBack(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserPage.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.getIcons().add(new Image("favicon.png"));
        Stage stage1 = (Stage) monthChoice.getScene().getWindow();
        stage1.close();
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void calendarr(Event event) throws IOException {
        int i = 1;
        int flag = 0;
        String[] months = new String[]{"January", "February", "March", "April", "May", "June",
                                       "July", "August", "September", "October", "November",
                                       "December"};
        int monIndex = -1;
        // System.out.println(MonthChoice.getValue());
        for (int j = 0; j < months.length; j++) {
            if (monthChoice.getValue().equals(months[j])) {
                monIndex = j;
            }
        }
        int yearIndex = 2020;

        Fyear = yearIndex;
        Fmonth = monIndex;

        Calendar c = Calendar.getInstance();
        yearIndex = c.get(Calendar.YEAR);

        c.set(Calendar.YEAR, yearIndex);
        c.set(Calendar.MONTH, monIndex);

        YearMonth yearMon = YearMonth.of(yearIndex, monIndex + 1);
        int days = yearMon.lengthOfMonth();
        int day = 1;

        List<Reservations> reservationsUser = findReservations(Users.user);
        List<UserEvent> userEvents = findUserEvents(Users.user);

        for (Node e : grid.getChildren()) {
            c.set(Calendar.DAY_OF_MONTH, i);
            try {
                ((AnchorPane) e).getChildren().clear();
            } catch (Exception idc) {
                System.out.println("Meaningless error");
            }

            String[] time1 = (c.getTime() + "").split(" ");
            String time = time1[0];
            if (flag == 0) {
                if (e.getId().equals(time)) {
                    flag++;
                    Text text = new Text(day + "");
                    text.setX(5);
                    text.setY(115);
                    text.setRotate(-90);

                    i++;
                    if (day == DayNow && Fmonth == MonthNow) {
                        System.out.println("Here");
                        BorderWidths bor = new BorderWidths(5, 5, 5, 5);
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE,
                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    } else {
                        BorderWidths bor = new BorderWidths(0, 0, 0, 0);
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    }
                    ((AnchorPane) e).getChildren().add(text);
                    //get all events for today
                    List<Reservations> reservationsToday = findReservations(day, Fmonth, Fyear,
                            reservationsUser);
                    List<UserEvent> userEventsToday = findUserEvents(day,
                            Fmonth, Fyear, userEvents);
                    Text events = new Text((reservationsToday.size()
                            + userEventsToday.size()) + " events");
                    events.setTranslateX(30);
                    events.setTranslateY(80);
                    events.setRotate(-90);
                    e.setOnMouseClicked(new UserScheduleHandler(day, Fmonth,
                            Fyear, reservationsToday, userEventsToday));
                    ((AnchorPane) e).getChildren().add(events);
                    day++;
                }
            } else {
                Text text = new Text(day + "");
                text.setX(5);
                text.setY(115);
                text.setRotate(-90);
                i++;
                days--;
                if (days > 0) {
                    if (day == DayNow && Fmonth == MonthNow) {
                        System.out.println("Here");
                        BorderWidths bor = new BorderWidths(5, 5, 5, 5);
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE,
                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    } else {
                        BorderWidths bor = new BorderWidths(0, 0, 0, 0);
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    }
                    ((AnchorPane) e).getChildren().add(text);

                    //get all events for today
                    List<Reservations> reservationsToday = findReservations(day, Fmonth, Fyear,
                            reservationsUser);
                    List<UserEvent> userEventsToday = findUserEvents(day, Fmonth,
                            Fyear, userEvents);
                    Text events = new Text((reservationsToday.size()
                            + userEventsToday.size()) + " events");
                    events.setTranslateX(30);
                    events.setTranslateY(80);
                    events.setRotate(-90);
                    e.setOnMouseClicked(new UserScheduleHandler(day, Fmonth,
                            Fyear, reservationsToday,
                            userEventsToday));
                    ((AnchorPane) e).getChildren().add(events);
                    day++;
                }
            }
        }
    }

    /**
     * Initializes page.
     * @param location Link to location
     * @param resources Resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] allMonths = new String[]{"January", "February", "March", "April", "May",
                                             "June", "July", "August", "September", "October",
                                             "November", "December"};
        monthChoice.setItems(FXCollections.observableArrayList("January", "February",
                "March", "April", "May", "June", "July", "August", "September", "October",
                "November", "December"));

        Calendar defaultCalendar = Calendar.getInstance();

        List<Reservations> reservationsUser = findReservations(Users.user);
        List<UserEvent> userEvents = findUserEvents(Users.user);

        HelperController helper = new HelperController();
        helper.loadSidePane(sidePane);

        int year = defaultCalendar.get(Calendar.YEAR);
        int month = defaultCalendar.get(Calendar.MONTH);
        int day1 = defaultCalendar.get(Calendar.DAY_OF_MONTH);
        DayNow = day1;

        List<Buildings> buildings = new ArrayList<>();
        try {
            buildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        defaultCalendar.set(Calendar.YEAR, year);
        defaultCalendar.set(Calendar.MONTH, month);

        MonthNow = month;
        Fyear = year;
        Fmonth = month;
        String defMon = null;
        for (int i = 0; i < allMonths.length; i++) {
            if (i == month) {
                defMon = allMonths[i];
                break;
            }
        }

        monthChoice.setValue(defMon);

        int flag = 0;
        int i = 1;

        YearMonth yearMon = YearMonth.of(year, month + 1);

        int days = yearMon.lengthOfMonth();
        int day = 1;

        for (Node e : grid.getChildren()) {
            defaultCalendar.set(Calendar.DAY_OF_MONTH, i);
            try {
                ((AnchorPane) e).getChildren().clear();
            } catch (Exception idc) {
                System.out.println("Meaningless error");
            }


            String[] time1 = (defaultCalendar.getTime() + "").split(" ");
            String time = time1[0];
            if (flag == 0) {
                if (e.getId().equals(time)) {
                    flag++;
                    Text text = new Text(day + "");
                    text.setX(5);
                    text.setY(115);
                    text.setRotate(-90);
                    if (day == day1) {
                        System.out.println("Here");
                        BorderWidths bor = new BorderWidths(5, 5, 5, 5);
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE,
                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    }
                    i++;
                    ((AnchorPane) e).getChildren().add(text);

                    //get all events for today
                    List<Reservations> reservationsToday = findReservations(day, month,
                            year, reservationsUser);
                    List<UserEvent> userEventsToday = findUserEvents(day, month, year, userEvents);
                    Text events = new Text((reservationsToday.size()
                            + userEventsToday.size()) + " events");
                    events.setTranslateX(30);
                    events.setTranslateY(80);
                    events.setRotate(-90);
                    e.setOnMouseClicked(new UserScheduleHandler(day, month, year, reservationsToday,
                            userEventsToday));
                    ((AnchorPane) e).getChildren().add(events);
                    day++;
                }
            } else {
                Text text = new Text(day + "");
                text.setX(5);
                text.setY(115);
                text.setRotate(-90);
                i++;
                days--;
                if (days > 0) {
                    if (day == day1) {
                        BorderWidths bor = new BorderWidths(5, 5, 5, 5);
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE,
                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    }

                    ((AnchorPane) e).getChildren().add(text);

                    //get all events for today
                    List<Reservations> reservationsToday = findReservations(day, month,
                            year, reservationsUser);
                    List<UserEvent> userEventsToday = findUserEvents(day, month, year, userEvents);
                    Text events = new Text((reservationsToday.size()
                            + userEventsToday.size()) + " events");
                    events.setTranslateX(30);
                    events.setTranslateY(80);
                    events.setRotate(-90);
                    e.setOnMouseClicked(new UserScheduleHandler(day, month, year, reservationsToday,
                            userEventsToday));
                    ((AnchorPane) e).getChildren().add(events);
                    day++;
                }
            }
        }
    }

    /**
     * finds all reservations of a given user.
     *
     * @param user : the user.
     * @return a List with the reservations.
     */
    public List<Reservations> findReservations(Users user) {
        List<Reservations> list = new ArrayList<>();
        try {
            list = con.getReservations();
        } catch (IOException e) {
            return list;
        }
        List<Reservations> filteredReservations = new ArrayList<>();
        for (Reservations r : list) {
            if (r.getUserReserving().equals(user.getNetid())) {
                filteredReservations.add(r);
            }
        }
        return filteredReservations;
    }

    /**
     * Filters a reservation list for a given date.
     * @param day Given day
     * @param month Given month
     * @param year Given year
     * @param reservations : List with the reservations to filter
     * @return : List with filtered reservations.
     */
    public List<Reservations> findReservations(int day, int month, int year,
                                               List<Reservations> reservations) {
        List<Reservations> filteredReservations = new ArrayList<>();

        String dateString = "";
        dateString += year;
        dateString += "-";
        if (month < 9) {
            dateString += "0";
        }
        dateString += month + 1;
        dateString += "-";
        if (day < 10) {
            dateString += "0";
        }
        dateString += day;

        for (Reservations r : reservations) {
            if (r.getDate().toString().equals(dateString)) {
                filteredReservations.add(r);
            }
        }

        if (day == 14) {
            System.out.println("dateString = \"" + dateString + "\"");
            printReservations(filteredReservations);
        }

        Collections.sort(filteredReservations);
        return filteredReservations;
    }

    /**
     * Method to find events per user.
     * @param user User to find events of
     * @return List of user events
     */
    public List<UserEvent> findUserEvents(Users user) {
        List<UserEvent> list = new ArrayList<>();
        try {
            list = con.getUserEvents();
        } catch (IOException e) {
            System.out.println("got an IOException when trying to retrieve user events.");
            e.printStackTrace();
            return list;
        }
        System.out.println("retrieved events correctly");
        List<UserEvent> filteredEvents = new ArrayList<>();
        for (UserEvent e : list) {
            if (e.getUser().equals(user.getNetid())) {
                filteredEvents.add(e);
            }
        }
        System.out.println("For this user, " + filteredEvents.size() + " events were found.");
        return filteredEvents;
    }

    /**
     * Method to filter user events per date.
     * @param day Given day
     * @param month Given month
     * @param year Given year
     * @param events List of all user events
     * @return Filtered list of user events
     */
    public List<UserEvent> findUserEvents(int day, int month, int year, List<UserEvent> events) {
        List<UserEvent> filteredEvents = new ArrayList<>();

        String dateString = "";
        dateString += year;
        dateString += "-";
        if (month < 10) {
            dateString += "0";
        }
        dateString += month;
        dateString += "-";
        if (day < 10) {
            dateString += "0";
        }
        dateString += day;

        for (UserEvent e : events) {
            if (e.getDate().toString().equals(dateString)) {
                filteredEvents.add(e);
            }
        }
        Collections.sort(filteredEvents);
        return filteredEvents;
    }

    /**
     * Method to output reservations.
     * @param reservations List of reservations to print
     */
    public void printReservations(List<Reservations> reservations) {
        for (Reservations r : reservations) {
            System.out.println(r.getUserReserving() + " reserved "
                    + r.getRoomReserved() + " res_ID =" + r.getId()
                    + "\n Date: " + r.getDate()
                    + "\n Year=" + r.getDate().getYear() + ", Month="
                    + r.getDate().getMonth() + ", Day=" + r.getDate().getDay());
        }
        for (Reservations r : reservations) {
            System.out.println(r.getDate());
        }
    }

    /**
     * Method for 'user page' button.
     * @param event Clicking on the go back button
     * @throws IOException Exception if can't find user page scene
     */
    public void userPage(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserPage.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("favicon.png"));
        stage.show();

        Stage stage1 = (Stage) monthChoice.getScene().getWindow();
        stage1.close();
    }
}
