package nl.tudelft.oopp.demo.controllers;

import javafx.event.Event;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;
import nl.tudelft.oopp.demo.entities.UserEvent;

import java.io.IOException;

public class DeleteEventHandler implements javafx.event.EventHandler {

    private UserEvent userEvent;
    private UserScheduleDayView userScheduleDayView;
    UserServerCommunication con = new UserServerCommunication();

    public DeleteEventHandler(UserEvent userEvent, UserScheduleDayView userScheduleDayView) {
        this.userEvent = userEvent;
        this.userScheduleDayView = userScheduleDayView;
    }

    @Override
    public void handle(Event event) {
        try {
            con.deleteUserEvent(Integer.toString(userEvent.getId()));
            userScheduleDayView.deleteUserEvent(userEvent);
            System.out.println("Deleted personal event: " + userEvent.getNiceString());
            userScheduleDayView.update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
