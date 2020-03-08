package nl.tudelft.oopp.demo.controllers;

import java.util.Date;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.Calendar;
import nl.tudelft.oopp.demo.communication.ServerCommunication;

public class BikeReservationMenu {
    private static int Fmonth;
    private static int Fyear;
    private static int FDay;

    public static int getMonth() {
        return Fmonth;
    }
    public static int getYear() {
        return Fyear;
    }
    public static int getDay() {
        return FDay;
    }
    public String reservationDate;

    @FXML
    private ChoiceBox YearChoice;


    @FXML
    private ChoiceBox MonthChoice;

    //  private int month;
    // private String year;
    @FXML
    private javafx.scene.control.Button ReserveScene;
    @FXML
    private AnchorPane Mon;
    @FXML
    private GridPane Grid;
    @FXML
    private ScrollPane Scroll;

    @FXML
    private void Year(Event event) throws IOException {
        String[] Ychoice = new String[]{
                "2020", "2021", "2022"
        };
        YearChoice.setItems(FXCollections.observableArrayList("2020", "2021", "2022"));
    }

    @FXML
    private void Month(Event event) throws IOException {
        MonthChoice.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

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

    public void GoBack(Event event) throws IOException {
        Stage stage1 = (Stage) ReserveScene.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainReservationMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void Calendarr(Event event) throws IOException {
        int i = 1;
        int flag = 0;
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int monIndex = -1;
        // System.out.println(MonthChoice.getValue());
        for (int j = 0; j < months.length; j++) {
            if (MonthChoice.getValue().equals(months[j])) {
                monIndex = j;
            }
        }
        int yearIndex = 2020;
        if (YearChoice.getValue().equals("2021")) {
            yearIndex = 2021;
        }
        if (YearChoice.getValue().equals("2022")) {
            yearIndex = 2022;
        }

        Fyear = yearIndex;
        Fmonth = monIndex;

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, yearIndex);
        c.set(Calendar.MONTH, monIndex);

        YearMonth yearMon = YearMonth.of(yearIndex, monIndex + 1);
        int days = yearMon.lengthOfMonth();
        int day = 1;


        for (Node e : Grid.getChildren()) {
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
                    ((AnchorPane) e).getChildren().add(text);
                    day++;
                }
            }
        }
    }

    @FXML
    public void DateOnCalendar(Event event) throws IOException {
        String str = event.getSource().toString();
        AnchorPane e = (AnchorPane) event.getSource();
        String CurrentDate = " ";
        if (!(e.getChildren().isEmpty())) {
            CurrentDate = e.getChildren().toString();
            String[] CurDate = CurrentDate.split("text=");
            //System.out.println(CurrentDate);
            CurrentDate = CurDate[1];
            CurrentDate = CurrentDate.substring(1, 3);
            String[] CurDat = CurrentDate.split("\"");
            CurrentDate = CurDat[0];
            //CurrentDate = CurrentDate.replaceAll(" \" ", " ");
            // System.out.println(CurrentDate);
        } else {
            CurrentDate = "32";
        }

        FDay = Integer.parseInt(CurrentDate);

        if (FDay == 32) return;
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
        Stage stage1 = (Stage) e.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/TimeSlotBikes.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }
}




