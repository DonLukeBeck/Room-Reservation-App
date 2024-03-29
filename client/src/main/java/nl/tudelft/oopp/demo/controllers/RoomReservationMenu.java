package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import nl.tudelft.oopp.demo.entities.Holidays;

public class RoomReservationMenu implements Initializable {
    private static int Fmonth;
    private static int Fyear;
    private static int FDay;
    private static int MonthNow;
    private static int DayNow;
    public String reservationDate;
    ServerCommunication con = new ServerCommunication();
    @FXML
    AnchorPane mainScreen;
    HelperController helper = new HelperController();
    @FXML
    private ChoiceBox monthChoice;

    @FXML
    private GridPane grid;
    @FXML
    private Pane sidePane;
    @FXML
    private Pane rightPane;

    /**
     * Method to get Month.
     *
     * @return Month
     */
    public static int getMonth() {
        return Fmonth;
    }

    /**
     * Method to get year.
     *
     * @return Year
     */
    public static int getYear() {
        return Fyear;
    }

    public static int getDayNow() {
        return DayNow;
    }

    public static int getMonthNow() {
        return MonthNow;
    }

    /**
     * Method to get Day.
     *
     * @return Day
     */
    public static int getDay() {
        return FDay;
    }

    /**
     * Method for campus map to pop up.
     *
     * @param event Clicking on 'Campus Map'
     * @throws IOException Exception if can't find campus map
     */
    public void campusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Room Reservation App");
        stage.getIcons().add(new Image("images/favicon.png"));
        stage.show();
    }

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }

    /**
     * Method to go back to previous page.
     *
     * @param event Clicking on 'Go Back'
     * @throws IOException Exception if can't find main menu scene
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainReservationMenu.fxml", mainScreen);
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
     * Method to convert month from string to int.
     *
     * @return Month as integer
     */
    public int getMonthFromSearch() {
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", ""
                + "July", "August", "September", "October", "November", "December"};
        int monIndex = -1;
        for (int j = 0; j < months.length; j++) {
            if (monthChoice.getValue().equals(months[j])) {
                monIndex = j;
            }
        }
        return monIndex;
    }

    /**
     * Add new Text.
     *
     * @param e       Node where the Text will be added
     * @param layoutX chosen layout X
     * @param layoutY chosen layout Y
     * @param text    text to be added to the Text
     */
    public void addText(Node e, double layoutX, double layoutY, String text) {
        Text day = new Text(text);
        day.setX(layoutX);
        day.setY(layoutY);
        day.setRotate(-90);
        if (e instanceof AnchorPane) {
            ((AnchorPane) e).getChildren().add(day);
        }

    }

    /**
     * Method to add border to a date on calendar.
     *
     * @param e Date to have border added to
     */
    public void addBorderToTheChosenDate(Node e) {
        BorderWidths border = new BorderWidths(5, 5, 5, 5);
        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, border)));
    }

    /**
     * Method to clear all dates.
     */
    public void clearAllDates() {
        for (Node k : grid.getChildren()) {
            try {
                ((AnchorPane) k).getChildren().clear();
                k.setStyle("-fx-background-color: transparent");
                k.setDisable(false);
                BorderWidths border = new BorderWidths(0, 0, 0, 0);
                ((AnchorPane) k).setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, border)));
            } catch (Exception idc) {
                System.out.println("Meaningless error");
            }

        }
    }

    /**
     * Method to add dates onto calendar template.
     *
     * @param c             Calendar passed as parameter
     * @param lengthOfMonth Number of days in that month
     */
    public void addDatesOnCalendar(Calendar c, int lengthOfMonth) {
        int flag = 0;
        int i = 1;
        int days = lengthOfMonth;
        int day = 1;
        int hasHoliday = 0;

        clearAllDates();

        List<HolidayTuple> holiday = null;
        try {
            holiday = holidays(Fmonth, days);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (holiday == null || holiday.isEmpty()) {
            hasHoliday = 0;
        } else {
            hasHoliday = 1;
        }

        for (Node e : grid.getChildren()) {
            if (days == 1) {
                return;
            }

            c.set(Calendar.DAY_OF_MONTH, i);
            try {
                ((AnchorPane) e).getChildren().clear();
                BorderWidths border = new BorderWidths(0, 0, 0, 0);
                ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, border)));
            } catch (Exception idc) {
                System.out.println("Meaningless error");
            }

            String[] time1 = (c.getTime() + "").split(" ");
            String time = time1[0];
            if (flag == 0) {
                if (e.getId().equals(time)) {
                    flag++;
                    addText(e, 5, 115, day + "");
                    if (hasHoliday == 1 && !holiday.isEmpty()
                            && day == holiday.get(0).getHolidayDay()) {

                        e.setStyle("-fx-background-color: #a5ee6e");
                        addText(e, 10, 68, holiday.get(0).getComment());
                        e.disableProperty().setValue(true);
                        holiday.remove(0);
                    }

                    if (day == DayNow && Fmonth == MonthNow) {
                        addBorderToTheChosenDate(e);
                    }
                    i++;
                    day++;
                }
            } else {
                addText(e, 5, 115, day + "");
                if (hasHoliday == 1 && !holiday.isEmpty()
                        && day == holiday.get(0).getHolidayDay()) {

                    e.setStyle("-fx-background-color: #a5ee6e");
                    addText(e, 10, 68, holiday.get(0).getComment());
                    e.disableProperty().setValue(true);
                    holiday.remove(0);
                }
                days--;
                if (days > 0) {
                    if (day == DayNow && Fmonth == MonthNow) {

                        addBorderToTheChosenDate(e);
                    }
                    day++;
                }
            }
        }
    }

    /**
     * Method to search for specific calendar month.
     *
     * @param event on mouse click
     * @throws IOException Exception if no such calendar exists
     */
    @FXML
    private void calendarSearch(Event event) throws IOException {
        int flag = 0;
        int yearIndex = 2020;

        Fyear = yearIndex;
        Fmonth = getMonthFromSearch();

        Calendar c = Calendar.getInstance();
        yearIndex = c.get(Calendar.YEAR);

        c.set(Calendar.YEAR, yearIndex);
        c.set(Calendar.MONTH, Fmonth);

        YearMonth yearMon = YearMonth.of(yearIndex, Fmonth + 1);

        int days = yearMon.lengthOfMonth();
        addDatesOnCalendar(c, days);
    }

    /**
     * Method to find the current date.
     *
     * @param e AnchorPane from which children we will search for the current date
     * @return Current date
     */
    public String findCurrentDate(AnchorPane e) {
        String currentDate = " ";
        currentDate = e.getChildren().toString();
        String[] curDate = currentDate.split("text=");
        currentDate = curDate[1];
        currentDate = currentDate.substring(1, 3);
        String[] curDat = currentDate.split("\"");
        currentDate = curDat[0];
        return currentDate;
    }

    /**
     * Opens when previous date is chosen.
     *
     * @throws IOException Exception if can't find previous date alers scene
     */
    public void openAlert() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/PreviousDateAlert.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Room Reservation App");
        stage.getIcons().add(new Image("images/favicon.png"));
        stage.show();
    }

    /**
     * Saves all needed properties for the chosen date.
     *
     * @param event on mouse click
     * @throws IOException Exception if can't find timeslot scene
     */
    @FXML
    public void dateOnCalendar(Event event) throws IOException {
        AnchorPane e = (AnchorPane) event.getSource();
        String currentDate = " ";
        if (!(e.getChildren().isEmpty())) {
            currentDate = findCurrentDate(e);

        } else {
            //currentDate = "32";
            return;
        }

        FDay = Integer.parseInt(currentDate);

        if (FDay == 32) {
            return;
        }

        Calendar check = Calendar.getInstance();
        check.set(Calendar.MONTH, Fmonth);
        check.set(Calendar.YEAR, Fyear);
        check.set(Calendar.DAY_OF_MONTH, FDay);
        Date date1 = check.getTime();
        Calendar now = Calendar.getInstance();
        Date now1 = now.getTime();

        if (!date1.toString().equals(now1.toString())) {

            if (date1.before(now1)) {
                openAlert();
                return;
            }
        }

        reservationDate = date1.toString();
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/TimeSlots.fxml", mainScreen);
    }

    /**
     * Method to keep track of holidays.
     *
     * @param month  Month during which holidays happen
     * @param monLen Holidays duration
     * @return Holidays dates
     * @throws IOException Exception if can't find corresponding calendar
     */
    public List<HolidayTuple> holidays(int month, int monLen) throws IOException {
        List<Holidays> list = con.getHolidays();

        List<HolidayTuple> holidaysForMonth = new ArrayList<>();
        List<HolidayTuple> allHolidayDates = new ArrayList<>();

        for (Holidays e : list) {

            Calendar startDate = Calendar.getInstance();
            startDate.setTime(e.getStartDate());

            Calendar endDate = Calendar.getInstance();
            endDate.setTime(e.getEndDate());

            if (month == startDate.get(Calendar.MONTH)) {

                int startDay = startDate.get(Calendar.DAY_OF_MONTH);

                holidaysForMonth.add(new HolidayTuple(startDay, e.getComments()));


                if (endDate.get(Calendar.MONTH) == startDate.get(Calendar.MONTH)) {
                    int endDay = endDate.get(Calendar.DAY_OF_MONTH);

                    holidaysForMonth.add(new HolidayTuple(endDay, e.getComments()));
                } else {
                    holidaysForMonth.add(new HolidayTuple(monLen, e.getComments()));
                }
            } else if (month == endDate.get(Calendar.MONTH)
                    && month != startDate.get(Calendar.MONTH)) {
                int startDay = 1;

                holidaysForMonth.add(new HolidayTuple(startDay, e.getComments()));

                int endDay = endDate.get(Calendar.DAY_OF_MONTH);
                holidaysForMonth.add(new HolidayTuple(endDay, e.getComments()));

            } else if (startDate.get(Calendar.MONTH) < month
                    && endDate.get(Calendar.MONTH) > month) {
                int startDay = 1;
                holidaysForMonth.add(new HolidayTuple(startDay, e.getComments()));
                holidaysForMonth.add(new HolidayTuple(monLen, e.getComments()));
            }
        }

        for (int i = 0; i < holidaysForMonth.size(); i++) {
            int j = holidaysForMonth.get(i).getHolidayDay();
            while (j <= holidaysForMonth.get(i + 1).getHolidayDay()) {
                allHolidayDates.add(new HolidayTuple(j, holidaysForMonth.get(i).getComment()));
                j++;
            }
            i++;
        }

        if (allHolidayDates.isEmpty()) {
            return null;
        } else {

            return HelperController.insertionSort(allHolidayDates);
        }
    }

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }

    public void openResources(Event event) throws IOException {
        helper.openResources();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRole();
        String[] allMonths = new String[]{"January", "February", "March", "April", "May", "June", ""
                + "July", "August", "September", "October", "November", "December"};
        monthChoice.setItems(FXCollections.observableArrayList(allMonths));

        Calendar defaultCalendar = Calendar.getInstance();

        helper.loadSidePane(sidePane);

        int year = defaultCalendar.get(Calendar.YEAR);
        int month = defaultCalendar.get(Calendar.MONTH);
        int day1 = defaultCalendar.get(Calendar.DAY_OF_MONTH);
        DayNow = day1;

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


        YearMonth yearMon = YearMonth.of(year, month + 1);

        int days = yearMon.lengthOfMonth();

        addDatesOnCalendar(defaultCalendar, days);
    }

    public class HolidayTuple {
        private int day;
        private String comment;

        public HolidayTuple(int day, String comment) {
            this.day = day;
            this.comment = comment;
        }

        public int getHolidayDay() {
            return this.day;
        }

        public String getComment() {
            return this.comment;
        }
    }
}
