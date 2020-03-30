package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.entities.Reservations;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserScheduleDayView {
    private int day = 0;
    private int month = 0;
    private int year = 0;
    private List<Reservations> reservations = new ArrayList<>();

    @FXML
    private javafx.scene.control.Label dateLabel;
    @FXML
    private javafx.scene.control.ScrollPane scrollPane;

    /**
     * Initializer method.
     */
    public void initialize() {
        String dateString = getDateString();
        dateLabel.setText(dateString);
        String reservationsString = getReservationsString();
        Text reservationsText = new Text(reservationsString);
        scrollPane.setContent(reservationsText);
    }

    /**
     * Helper method to get a nice String of the reservations.
     * @return
     */
    private String getReservationsString() {
        String res = "";
        for (Reservations r : reservations) {
            res += r.getNiceString();
            res += "\n\n";
        }
        return res;
    }

    /**
     * Helper method to get a nice String of the date.
     *
     * @return : the string.
     */
    private String getDateString() {
        String dateString = "";
        if (day < 10) {
            dateString += "0";
        }
        dateString += day;
        switch (month + 1) {
            case 1:
                dateString += " January ";
                break;
            case 2:
                dateString += " February ";
                break;
            case 3:
                dateString += " March ";
                break;
            case 4:
                dateString += " April ";
                break;
            case 5:
                dateString += " May ";
                break;
            case 6:
                dateString += " June ";
                break;
            case 7:
                dateString += " July ";
                break;
            case 8:
                dateString += " August ";
                break;
            case 9:
                dateString += " September ";
                break;
            case 10:
                dateString += " October ";
                break;
            case 11:
                dateString += " November ";
                break;
            case 12:
                dateString += " December ";
                break;
        }
        dateString += year;
        return dateString;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }

    public void addPersonalEvent() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/AddPersonalEvent.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        AddPersonalEvent controller = loader.<AddPersonalEvent>getController();
        controller.setDay(day);
        controller.setMonth(month);
        controller.setYear(year);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
