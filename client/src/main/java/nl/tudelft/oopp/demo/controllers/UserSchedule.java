package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.Users;

public class UserSchedule implements Initializable {
    private static int Fmonth;
    private static int Fyear;
    private static int FDay;
    private static int MonthNow;
    private static int DayNow;
    public String reservationDate;
    ServerCommunication con = new ServerCommunication();
    @FXML
    private ChoiceBox monthChoice;
    @FXML
    private javafx.scene.control.Button scene1;
    @FXML
    private AnchorPane mon;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private Pane sidePane;

    /**
     *
     * @return
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
     *Method for 'go back' button.
     * @param event Clicking on the go back button
     * @throws IOException
     */
    public void goBack(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserPage.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage stage1 = (Stage) scene1.getScene().getWindow();
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
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
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
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    } else {
                        BorderWidths bor = new BorderWidths(0, 0, 0, 0);
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    }
                    ((AnchorPane) e).getChildren().add(text);
                    //get all events for today
                    List<Reservations> reservationsToday = findReservations(day, Fmonth, Fyear, reservationsUser);
                    Text events = new Text(reservationsToday.size() + " events");
                    events.setTranslateX(30);
                    events.setTranslateY(80);
                    events.setRotate(-90);
                    e.setOnMouseClicked(new UserScheduleHandler(day, Fmonth, Fyear, reservationsToday));
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
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    } else {
                        BorderWidths bor = new BorderWidths(0, 0, 0, 0);
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    }
                    ((AnchorPane) e).getChildren().add(text);

                    //get all events for today
                    List<Reservations> reservationsToday = findReservations(day, Fmonth, Fyear, reservationsUser);
                    Text events = new Text(reservationsToday.size() + " events");
                    events.setTranslateX(30);
                    events.setTranslateY(80);
                    events.setRotate(-90);
                    e.setOnMouseClicked(new UserScheduleHandler(day, Fmonth, Fyear, reservationsToday));
                    ((AnchorPane) e).getChildren().add(events);
                    day++;
                }
            }
        }
    }

    /**
     * Initializes page.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] allMonths = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthChoice.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

        Calendar defaultCalendar = Calendar.getInstance();

        List<Reservations> reservationsUser = findReservations(Users.user);
        //printReservations(reservationsUser);

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
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    }
                    i++;
                    ((AnchorPane) e).getChildren().add(text);

                    //get all events for today
                    List<Reservations> reservationsToday = findReservations(day, month, year, reservationsUser);
                    Text events = new Text(reservationsToday.size() + " events");
                    events.setTranslateX(30);
                    events.setTranslateY(80);
                    events.setRotate(-90);
                    e.setOnMouseClicked(new UserScheduleHandler(day, month, year, reservationsToday));
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
                        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bor)));
                    }

                    ((AnchorPane) e).getChildren().add(text);

                    //get all events for today
                    List<Reservations> reservationsToday = findReservations(day, month, year, reservationsUser);
                    Text events = new Text(reservationsToday.size() + " events");
                    events.setTranslateX(30);
                    events.setTranslateY(80);
                    events.setRotate(-90);
                    e.setOnMouseClicked(new UserScheduleHandler(day, month, year, reservationsToday));
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
     *
     * @param day
     * @param month
     * @param year
     * @param reservations : List with the reservations to filter
     * @return : List with filtered reservations.
     */
    public List<Reservations> findReservations(int day, int month, int year, List<Reservations> reservations) {
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
            /*
            if (day == 22) {
                System.out.println(day + "==" + r.getDate().getDay() + " " + (day == r.getDate().getDay()));
                System.out.println(month + "==" + r.getDate().getMonth() + " " + (month == r.getDate().getMonth()));
                System.out.println(year + "==" + r.getDate().getYear() + " " + (year == r.getDate().getYear()));
            }*/

            if (r.getDate().toString().equals(dateString)) {
                filteredReservations.add(r);
            }
        }

        if (day == 14) {
            System.out.println("dateString = \"" + dateString + "\"");
            printReservations(filteredReservations);
        }

        return filteredReservations;
    }

    public void printReservations(List<Reservations> reservations) {
        for (Reservations r : reservations) {
            System.out.println(r.getUserReserving() + " reserved " +
                    r.getRoomReserved() + " res_ID =" + r.getId() +
                    "\n Date: " + r.getDate() +
                    "\n Year=" + r.getDate().getYear() + ", Month=" +
                    r.getDate().getMonth() + ", Day=" + r.getDate().getDay());
        }
        for (Reservations r : reservations) {
            System.out.println(r.getDate());
        }
    }

    /**
     *Method for 'user page' button.
     * @param event Clicking on the go back button
     * @throws IOException
     */
    public void userPage(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserPage.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage stage1 = (Stage) scene1.getScene().getWindow();
        stage1.close();
    }
}
