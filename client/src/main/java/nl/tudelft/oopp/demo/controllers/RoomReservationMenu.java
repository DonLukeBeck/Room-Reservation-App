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
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;

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
    @FXML
    private ChoiceBox MonthChoice;
    @FXML
    private javafx.scene.control.Button ReserveScene;
    @FXML
    private AnchorPane Mon;
    @FXML
    private GridPane Grid;
    @FXML
    private Pane sidePane;

    public static int getMonth() {
        return Fmonth;
    }

    public static int getYear() {
        return Fyear;
    }

    public static int getDay() {
        return FDay;
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
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainReservationMenu.fxml", mainScreen);
    }

    @FXML
    private void calendarSearch(Event event) throws IOException {
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

        Fyear = yearIndex;
        Fmonth = monIndex;

        Calendar c = Calendar.getInstance();
        yearIndex = c.get(Calendar.YEAR);

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
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/TimeSlots.fxml", mainScreen);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] allMonths = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        MonthChoice.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

        Calendar defaultCalendar = Calendar.getInstance();

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
                changeInPosition = 40;
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

        MonthChoice.setValue(defMon);

        int flag = 0;
        int i = 1;

        YearMonth yearMon = YearMonth.of(year, month + 1);

        int days = yearMon.lengthOfMonth();
        int day = 1;

        for (Node e : Grid.getChildren()) {
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



