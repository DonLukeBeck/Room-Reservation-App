package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserScheduleDayViewTest {

    private UserScheduleDayView userScheduleDayView;

    @BeforeEach
    public void setup() {
        userScheduleDayView = new UserScheduleDayView();
        userScheduleDayView.setDay(1);
        userScheduleDayView.setMonth(1);
        userScheduleDayView.setYear(2020);
    }

    @Test
    public void getDayTest() {
        assertEquals(1, userScheduleDayView.getDay());
    }

    @Test
    public void setDayTest() {
        userScheduleDayView.setDay(2);
        assertEquals(2, userScheduleDayView.getDay());
    }

    @Test
    public void getMonthTest() {
        assertEquals(1, userScheduleDayView.getMonth());
    }

    @Test
    public void setMonthTest() {
        userScheduleDayView.setMonth(2);
        assertEquals(2, userScheduleDayView.getMonth());
    }

    @Test
    public void getYearTest() {
        assertEquals(2020, userScheduleDayView.getYear());
    }

    @Test
    public void setYearTest() {
        userScheduleDayView.setYear(2021);
        assertEquals(2021, userScheduleDayView.getYear());
    }

}
