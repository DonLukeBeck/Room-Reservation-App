package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AddPersonalEvent {
    private int day;
    private int month;
    private int year;
    @FXML
    javafx.scene.control.Button button;

    public void addEvent() {
        System.out.println("adding event");
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
}
