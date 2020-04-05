package nl.tudelft.oopp.demo.controllers;

import java.sql.Time;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;
import nl.tudelft.oopp.demo.entities.UserEvent;
import nl.tudelft.oopp.demo.entities.Users;

public class AddPersonalEvent {
    private int day;
    private int month;
    private int year;
    private UserScheduleDayView userScheduleDayView;
    UserServerCommunication con = new UserServerCommunication();
    @FXML
    private javafx.scene.control.Button button;
    @FXML
    private javafx.scene.control.TextField descriptionField;
    @FXML
    private javafx.scene.control.ChoiceBox hoursChoiceBox;
    @FXML
    private javafx.scene.control.ChoiceBox minutesChoiceBox;


    /**
     * Method to initialize a personal event.
     */
    public void initialize() {
        for (int i = 1; i < 24; i++) {
            String iString = "";
            if (i < 10) {
                iString += "0";
            }
            iString += i;
            hoursChoiceBox.getItems().add(iString);
        }
        for (int i = 0; i < 60; i += 5) {
            String iString = "";
            if (i < 10) {
                iString += "0";
            }
            iString += i;
            minutesChoiceBox.getItems().add(iString);
        }
    }

    /**
     * Method to add an event.
     */
    public void addEvent() {
        if (descriptionField.getText().isBlank()) {
            return;
        }
        String hoursString;
        String minutesString;
        try {
            hoursString = (String) hoursChoiceBox.getValue();
            minutesString = (String) minutesChoiceBox.getValue();
        } catch (NullPointerException e) {
            return;
        }
        String timeSlot = hoursString + ":" + minutesString + ":00";
        UserEvent userEvent = con.addUserEvent(Users.user.getNetid(), timeSlot,
                getDateString(), descriptionField.getText());
        System.out.println("id=" + userEvent.getId());
        userScheduleDayView.addPersonalEvent(userEvent);
        userScheduleDayView.update();
        cancel();
    }

    public void cancel() {
        Stage stage1 = (Stage) button.getScene().getWindow();
        stage1.close();
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

    public UserScheduleDayView getUserScheduleDayView() {
        return userScheduleDayView;
    }

    public void setUserScheduleDayView(UserScheduleDayView userScheduleDayView) {
        this.userScheduleDayView = userScheduleDayView;
    }

    /**
     * Method to return year.
     * @return Year
     */
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Method to get date and return it as a string.
     * @return Date as a string
     */
    public String getDateString() {
        String res = "";
        res += year + "-";
        if (month < 9) {
            res += "0";
        }
        res += month;
        res += "-";
        if (day < 10) {
            res += "0";
        }
        res += day;
        return res;
    }
}
