package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;

import javafx.event.Event;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;
import nl.tudelft.oopp.demo.entities.Reservations;

public class DeleteReservationHandler implements javafx.event.EventHandler {

    private Reservations reservation;
    private UserScheduleDayView userScheduleDayView;
    UserServerCommunication con = new UserServerCommunication();

    public DeleteReservationHandler(Reservations reservation,
                                    UserScheduleDayView userScheduleDayView) {
        this.reservation = reservation;
        this.userScheduleDayView = userScheduleDayView;
    }

    @Override
    public void handle(Event event) {
        try {
            con.deleteReservation(Integer.toString(reservation.getId()));
            userScheduleDayView.deleteReservation(reservation);
            System.out.println("Deleted reservation: " + reservation.getNiceString());
            userScheduleDayView.update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
