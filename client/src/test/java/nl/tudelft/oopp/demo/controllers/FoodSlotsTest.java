package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FoodSlotsTest {
    ServerCommunication con = new ServerCommunication();

    private double hourNow;
    private double minuteNow;
    private double hour2;

    @BeforeEach
    public void setUp() {
        hourNow = java.time.LocalTime.now().getHour();
        minuteNow = java.time.LocalTime.now().getMinute();

        hour2 = hourNow;
        if (minuteNow < 20) {
            hour2 += 0.5;
        }
        else if (minuteNow < 50) {
            hour2 += 1;
        }
        else {
            hour2 += 1.5;
        }
    }

    @Test
    public void getLocalTimeHourTest() {
        assertNotEquals(FoodSlots.getLocalTime(), hourNow);
    }

    @Test
    public void getLocalTimeTest() {
        assertEquals(FoodSlots.getLocalTime(), hour2);
    }

    @Test
    public void getTimeSlotFromIdShortTest() {
        String shortString = "id=A10A30, a";
        String timeSlot = "10:30";
        assertEquals(timeSlot, FoodSlots.getTimeSlotFromID(shortString));
    }

    @Test
    public void getTimeSlotFromIdWrongFormat() {
        String shortString = "id=10A30, a";
        String timeSlot = "10:30";
        assertNotEquals(timeSlot, FoodSlots.getTimeSlotFromID(shortString));
    }

    @Test
    public void getTimeSlotFromIdLongTest() {
        String longString = "aaaid=A10A30, testString";
        String timeSlot = "10:30";
        assertEquals(timeSlot, FoodSlots.getTimeSlotFromID(longString));
    }
}
