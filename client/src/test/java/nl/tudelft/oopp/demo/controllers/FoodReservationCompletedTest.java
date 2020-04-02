package nl.tudelft.oopp.demo.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodReservationCompletedTest {

    private FoodReservationCompleted foodReservationCompleted;

    @Test
    public void nameInProperFormatShortTest() {
        foodReservationCompleted = new FoodReservationCompleted();
        String name = "test";
        assertEquals(name, foodReservationCompleted.nameInProperFormat(name));
    }

    @Test
    public void nameInProperFormatLongTest() {
        foodReservationCompleted = new FoodReservationCompleted();
        String longString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        assertEquals(expected, foodReservationCompleted.nameInProperFormat(longString));

    }

}
