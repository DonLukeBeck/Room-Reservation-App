package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.UserEvent;

public class UserScheduleHandler implements javafx.event.EventHandler<MouseEvent> {
    private int day;
    private int month;
    private int year;
    private List<Reservations> reservations;
    private List<UserEvent> userEvents;

    public UserScheduleHandler(int day, int month, int year, List<Reservations> reservations,
                               List<UserEvent> userEvents) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.reservations = reservations;
        this.userEvents = userEvents;
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

    public List<UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Day clicked: " + day + "/" + month + "/" + year);
        try {
            openDayView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDayView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserScheduleDayView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        UserScheduleDayView controller = loader.<UserScheduleDayView>getController();
        controller.setDay(day);
        controller.setMonth(month);
        controller.setYear(year);
        controller.setReservations(reservations);
        controller.initialize();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}