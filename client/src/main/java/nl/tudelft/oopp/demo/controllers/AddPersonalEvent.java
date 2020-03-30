package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;
import nl.tudelft.oopp.demo.entities.Users;

public class AddPersonalEvent {
    private int day;
    private int month;
    private int year;
    UserServerCommunication con = new UserServerCommunication();
    @FXML
    private javafx.scene.control.Button button;
    @FXML
    private javafx.scene.control.TextField descriptionField;
    @FXML
    private javafx.scene.control.ChoiceBox hoursChoiceBox;
    @FXML
    private javafx.scene.control.ChoiceBox minutesChoiceBox;

    public void initialize() {
        for (int i = 1; i < 24; i++) {
            String iString = "";
            if (i < 10) {
                iString += "0";
            }
            iString += i;
            hoursChoiceBox.getItems().add(iString);
        }
        for (int i = 5; i < 60; i += 5) {
            String iString = "";
            if (i < 10) {
                iString += "0";
            }
            iString += i;
            minutesChoiceBox.getItems().add(iString);
        }
    }

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
        con.addUserEvent(Users.user.getNetid(), timeSlot, getDateString(), descriptionField.getText());
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDateString() {
        String res = "";
        res += year + "-";
        if (month < 9) {
            res += "0";
        }
        res += month + 1;
        res += "-";
        if (day < 10) {
            res += "0";
        }
        res += day;
        return res;
    }
}
