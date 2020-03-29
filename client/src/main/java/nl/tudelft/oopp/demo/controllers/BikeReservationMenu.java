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
import nl.tudelft.oopp.demo.entities.Holidays;

public class BikeReservationMenu implements Initializable {
    private static int Fmonth;
    private static int Fyear;
    private static int FDay;
    private static int MonthNow;
    private static int DayNow;
    public String reservationDate;
    HelperController helper = new HelperController();
    ServerCommunication con = new ServerCommunication();

    @FXML
    private ChoiceBox monthChoice;
    @FXML
    private javafx.scene.control.Button reserveScene;
    @FXML
    private AnchorPane mon;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane mainScreen;

    @FXML
    private Pane sidePane;

    /**
     * @return
     */
    public static int getMonth() {
        return Fmonth;
    }

    /**
     * Method to get the Year.
     *
     * @return The year
     */
    public static int getYear() {
        return Fyear;
    }

    /**
     * Method to get the day.
     *
     * @return The day
     */
    public static int getDay() {
        return FDay;
    }

    /**
     * Method for 'campus map' button.
     *
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
     * Method for 'go back' button.
     *
     * @param event Clicking on the go back button
     * @throws IOException
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainReservationMenu.fxml", mainScreen);

    }


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

    public void addText(Node e, double layoutX, double layoutY, String text) {
        Text day = new Text(text);
        day.setX(layoutX);
        day.setY(layoutY);
        day.setRotate(-90);
        if (e instanceof AnchorPane) {
            ((AnchorPane) e).getChildren().add(day);
        }

    }

    public void addBorderToTheChosenDate(Node e) {
        BorderWidths border = new BorderWidths(5, 5, 5, 5);
        ((AnchorPane) e).setBorder(new Border(new BorderStroke(Color.BLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, border)));
    }

    public void clearAllDates() {
        for (Node k : grid.getChildren()) {
            try {
                ((AnchorPane) k).getChildren().clear();
                BorderWidths border = new BorderWidths(0, 0, 0, 0);
                ((AnchorPane) k).setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, border)));
            } catch (Exception idc) {
                System.out.println("Meaningless error");
            }

        }
    }

    public void addDatesOnCalendar(Calendar c, int lengthOfMonth) {
        int flag = 0;
        int i = 1;
        int days = lengthOfMonth;
        int day = 1;
        int hasHoliday = 0;

        clearAllDates();

        List<Integer> holiday = null;
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
                    if (hasHoliday == 1 && !holiday.isEmpty() && day == holiday.get(0)) {
                        e.setStyle("-fx-background-color: #a5ee6e");
                        addText(e, 30, 68, "Holiday");
                        e.disableProperty().setValue(true);
                        holiday.remove(0);
                    }
                    flag++;
                    addText(e, 5, 115, day + "");

                    if (day == DayNow && Fmonth == MonthNow) {
                        addBorderToTheChosenDate(e);
                    }

                    i++;
                    day++;
                }
            } else {
                if (hasHoliday == 1 && !holiday.isEmpty() && day == holiday.get(0)) {
                    e.setStyle("-fx-background-color: #a5ee6e");
                    addText(e, 30, 68, "Holiday");
                    e.disableProperty().setValue(true);
                    holiday.remove(0);
                }
                addText(e, 5, 115, day + "");
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
     * @param event
     * @throws IOException
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

    public void openAlert() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/PreviousDateAlert.fxml");
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

        if (date1.before(now1)) {
            openAlert();
            return;
        }

        reservationDate = date1.toString();
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/TimeSlotBikes.fxml", mainScreen);
    }

    public List<Integer> holidays(int month, int monLen) throws IOException {
        List<Holidays> list = con.getHolidays();

        List<Integer> holidaysForMonth = new ArrayList<>();
        List<Integer> allHolidayDates = new ArrayList<>();

        for (Holidays e : list) {
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(e.getStartDate());

            if (month == startDate.get(Calendar.MONTH)) {
                int startDay = startDate.get(Calendar.DAY_OF_MONTH) - 1;
                System.out.println(startDay);
                holidaysForMonth.add(startDay);

                Calendar endDate = Calendar.getInstance();
                endDate.setTime(e.getEndDate());

                if (endDate.get(Calendar.MONTH) == startDate.get(Calendar.MONTH)) {
                    int endDay = endDate.get(Calendar.DAY_OF_MONTH) - 1;
                    System.out.println(endDay);
                    holidaysForMonth.add(endDay);
                } else {
                    holidaysForMonth.add(monLen);
                }
            }
        }

        for (int i = 0; i < holidaysForMonth.size(); i++) {
            int j = holidaysForMonth.get(i);
            while (j <= holidaysForMonth.get(i + 1)) {
                allHolidayDates.add(j);
                j++;
            }
            i++;
        }

        if (allHolidayDates.isEmpty()) {
            return null;
        } else {
            return allHolidayDates;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}