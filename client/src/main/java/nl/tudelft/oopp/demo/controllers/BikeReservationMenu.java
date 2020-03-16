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

public class BikeReservationMenu implements Initializable {
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
        Stage stage1 = (Stage) reserveScene.getScene().getWindow();
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
                    day++;
                }
            }
        }
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void dateOnCalendar(Event event) throws IOException {
        String str = event.getSource().toString();
        AnchorPane e = (AnchorPane) event.getSource();
        String currentDate = " ";
        if (!(e.getChildren().isEmpty())) {
            currentDate = e.getChildren().toString();
            String[] curDate = currentDate.split("text=");
            //System.out.println(CurrentDate);
            currentDate = curDate[1];
            currentDate = currentDate.substring(1, 3);
            String[] curDat = currentDate.split("\"");
            currentDate = curDat[0];
            //CurrentDate = CurrentDate.replaceAll(" \" ", " ");
            // System.out.println(CurrentDate);
        } else {
            currentDate = "32";
        }

        FDay = Integer.parseInt(currentDate);

        if (FDay == 32) {
            return;
        }
        // System.out.println(FDay);


        //Date date = new GregorianCalendar(Fyear, Fmonth, FDay).getTime();
        Calendar check = Calendar.getInstance();
        check.set(Calendar.MONTH, Fmonth);
        check.set(Calendar.YEAR, Fyear);
        check.set(Calendar.DAY_OF_MONTH, FDay);
        //System.out.println(check.toString());
        Date date1 = check.getTime();
        Calendar now = Calendar.getInstance();
        Date now1 = now.getTime();

        if (date1.before(now1)) {
            FXMLLoader loader = new FXMLLoader();
            URL xmlUrl = getClass().getResource("/PreviousDateAlert.fxml");
            loader.setLocation(xmlUrl);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            return;
        }

        reservationDate = date1.toString();
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/TimeSlotBikes.fxml", mainScreen);

    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] allMonths = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthChoice.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

        Calendar defaultCalendar = Calendar.getInstance();

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
                    day++;
                }
            }
        }
    }
}
