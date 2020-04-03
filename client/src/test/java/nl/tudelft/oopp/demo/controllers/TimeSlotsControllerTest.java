package nl.tudelft.oopp.demo.controllers;

import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TimeSlotsControllerTest {

    @Test
    public void getEndAndStartTest() {
        Time start = new Time(0);
        Time end = new Time(3600000);
        double[] arr = TimeSlotsController.getEndAndStart(start, end);
        double endTime = 26;
        double startTime = 1;
        assertEquals(endTime, arr[0]);
        assertEquals(startTime, arr[1]);
    }

    @Test
    public void getTimeSlotFromIdShortTest() {
        String shortString = "id=A10A30, a";
        String timeSlot = "10:30";
        assertEquals(timeSlot, TimeSlotsController.getTimeSlotFromID(shortString));
    }

    @Test
    public void getTimeSlotFromIdWrongFormat() {
        String shortString = "id=10A30, a";
        String timeSlot = "10:30";
        assertNotEquals(timeSlot, TimeSlotsController.getTimeSlotFromID(shortString));
    }

    @Test
    public void getTimeSlotFromIdLongTest() {
        String longString = "aaaid=A10A30, testString";
        String timeSlot = "10:30";
        assertEquals(timeSlot, TimeSlotsController.getTimeSlotFromID(longString));
    }

}
