package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BikeReservationCompletedTest {

    private BikeReservationCompleted bikeReservationCompleted;

    @Test
    public void nameInProperFormatShortTest() {
        bikeReservationCompleted = new BikeReservationCompleted();
        String name = "test";
        assertEquals(name, bikeReservationCompleted.nameInProperFormat(name));
    }

    @Test
    public void nameInProperFormatLongTest() {
        bikeReservationCompleted = new BikeReservationCompleted();
        String longString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        assertEquals(expected, bikeReservationCompleted.nameInProperFormat(longString));

    }


}
