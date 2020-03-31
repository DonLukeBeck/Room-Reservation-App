package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddPersonalEventTest {

    private AddPersonalEvent personalEvent;

    /**
     * Setup of a personal event to test methods.
     */
    @BeforeEach
    public void setup() {
        personalEvent = new AddPersonalEvent();
        personalEvent.setDay(1);
        personalEvent.setMonth(1);
        personalEvent.setYear(2020);
    }

    @Test
    public void getDayTest() {
        assertEquals(1, personalEvent.getDay());
    }

    @Test
    public void setDayTest() {
        personalEvent.setDay(2);
        assertEquals(2, personalEvent.getDay());
    }

    @Test
    public void getMonthTest() {
        assertEquals(1, personalEvent.getMonth());
    }

    @Test
    public void setMonthTest() {
        personalEvent.setMonth(2);
        assertEquals(2, personalEvent.getMonth());
    }

    @Test
    public void getYearTest() {
        assertEquals(2020, personalEvent.getYear());
    }

    @Test
    public void setYearTest() {
        personalEvent.setYear(1);
        assertEquals(1, personalEvent.getYear());
    }

    @Test
    public void getDateStringTest() {
        String personalEventDateString = "2020-01-01";
        assertEquals(personalEventDateString, personalEvent.getDateString());
    }

}
