package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CompletedReservationControllerTest {
    private CompletedReservationController completedReservationController;

    @Test
    public void nameInProperFormatShortTest() {
        completedReservationController = new CompletedReservationController();
        String name = "test";
        assertEquals(name, completedReservationController.nameInProperFormat(name));
    }

    @Test
    public void nameInProperFormatLongTest() {
        completedReservationController = new CompletedReservationController();
        String longString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        assertEquals(expected, completedReservationController.nameInProperFormat(longString));

    }

}
