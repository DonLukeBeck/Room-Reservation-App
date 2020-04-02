package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.UserEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class UserScheduleHandlerTest {

    private UserScheduleHandler userScheduleHandler;
    private List listR;
    private List listR2;
    private List listU;
    private List listU2;
    private Time t1;
    private Date d1;
    private Reservations r1;
    private UserEvent ue1;

    @BeforeEach
    public void setup() {
        t1 = new Time(8,0,0);
        d1 = new Date(1,1,2020);

        r1 = new Reservations();
        ue1 = new UserEvent("user", t1, d1, "description");

        listR = new ArrayList<Reservations>(List.of(r1));
        listR2 = new ArrayList<Reservations>();
        listU = new ArrayList<UserEvent>(List.of(ue1));
        listU2 = new ArrayList<UserEvent>();

        userScheduleHandler = new UserScheduleHandler(1,1,2020, listR, listU);
    }

    @Test
    public void constructorTest() {
        assertNotNull(userScheduleHandler);
    }

    @Test
    public void getDayTest() {
        assertEquals(1, userScheduleHandler.getDay());
    }

    @Test
    public void setDayTest() {
        userScheduleHandler.setDay(2);
        assertEquals(2, userScheduleHandler.getDay());
    }

    @Test
    public void getMonthTest() {
        assertEquals(1, userScheduleHandler.getMonth());
    }

    @Test
    public void setMonthTest() {
        userScheduleHandler.setMonth(2);
        assertEquals(2, userScheduleHandler.getMonth());
    }

    @Test
    public void getYearTest() {
        assertEquals(2020, userScheduleHandler.getYear());
    }

    @Test
    public void setYearTest() {
        userScheduleHandler.setYear(2021);
        assertEquals(2021, userScheduleHandler.getYear());
    }

    @Test
    public void getReservationsTest() {
        assertEquals(listR, userScheduleHandler.getReservations());
    }

    @Test
    public void setReservationsTest() {
        userScheduleHandler.setReservations(listR2);
        assertEquals(listR2, userScheduleHandler.getReservations());
    }

    @Test
    public void getUserEventsTest() {
        assertEquals(listU, userScheduleHandler.getUserEvents());
    }

    @Test
    public void setUserEventsTest() {
        userScheduleHandler.setUserEvents(listU2);
        assertEquals(listU2, userScheduleHandler.getUserEvents());
    }

}
