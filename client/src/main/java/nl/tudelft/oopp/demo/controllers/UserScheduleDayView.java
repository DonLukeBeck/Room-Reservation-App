package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.UserEvent;

public class UserScheduleDayView {
    private int day = 0;
    private int month = 0;
    private int year = 0;
    private int heightReservationsPane = 0;
    private List<Reservations> reservations = new ArrayList<>();
    private List<UserEvent> userEvents = new ArrayList<>();

    private GridPane gridPane;
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
        addAllEventsToGridPane();
        scrollPane.setContent(gridPane);
    }

    /**
     * Helper method to get a nice String of the reservations.
     * @return
     */
    private void addAllEventsToGridPane() {
        gridPane = new GridPane();
        int i = 0;
        for (Reservations r : reservations) {
            AnchorPane pane = new AnchorPane();
            Text text = new Text(r.getNiceString());
            Button deleteButton = new Button();
            deleteButton.setText("Delete");
            deleteButton.setTranslateY(-20);
            deleteButton.setTranslateX(300);
            deleteButton.setOnAction(new DeleteReservationHandler(r, this));
            pane.getChildren().add(text);
            pane.getChildren().add(deleteButton);
            pane.setTranslateY(25 * i);
            gridPane.add(pane, 0, i, 1, 1);
            i++;
        }
        for (UserEvent e : userEvents) {
            AnchorPane pane = new AnchorPane();
            Text text = new Text(e.getNiceString());
            Button deleteButton = new Button();
            deleteButton.setText("Delete");
            deleteButton.setTranslateY(-20);
            deleteButton.setTranslateX(300);
            deleteButton.setOnAction(new DeleteEventHandler(e, this));
            pane.getChildren().add(text);
            pane.getChildren().add(deleteButton);
            pane.setTranslateY(25 * i);
            gridPane.add(pane, 0, i, 1, 1);
            i++;
        }
        gridPane.setTranslateY(40);
    }

    /**
     * Deletes a reservation from the view.
     * @param reservation Reservation to be deleted
     */
    public void deleteReservation(Reservations reservation) {
        reservations.remove(reservation);
        for (Reservations r : reservations) {
            System.out.println(r.getNiceString());
        }
        System.out.println();
    }

    /**
     * Deletes a user event from the view.
     * @param userEvent User event to be deleted
     */
    public void deleteUserEvent(UserEvent userEvent) {
        userEvents.remove(userEvent);
    }

    /**
     * Updates the schedule view.
     */
    public void update() {
        scrollPane.setContent(null);
        String dateString = getDateString();
        dateLabel.setText(dateString);
        addAllEventsToGridPane();
        scrollPane.setContent(gridPane);
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

    public List<UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    /**
     * Method to add a personal event.
     * @throws IOException Exception if can't find add personal event scene
     */
    public void addPersonalEvent() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/AddPersonalEvent.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        AddPersonalEvent controller = loader.<AddPersonalEvent>getController();
        controller.setDay(day);
        controller.setMonth(month);
        controller.setYear(year);
        controller.setUserScheduleDayView(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("favicon.png"));
        stage.show();
    }

    public void addPersonalEvent(UserEvent userEvent) {
        userEvents.add(userEvent);
    }
}
