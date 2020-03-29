package nl.tudelft.oopp.demo.controllers;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import nl.tudelft.oopp.demo.entities.Reservations;

import java.beans.EventHandler;
import java.util.List;

public class UserScheduleHandler implements javafx.event.EventHandler<MouseEvent> {
    private int day;
    private int month;
    private int year;
    private List<Reservations> reservations;

    public UserScheduleHandler(int day, int month, int year, List<Reservations> reservations) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.reservations = reservations;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Day clicked: " + day + "/" + month + "/" + year);
    }
}