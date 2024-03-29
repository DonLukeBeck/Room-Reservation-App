package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Text;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.UserEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    /**
     * Method to setup before every test.
     */
    @BeforeEach
    public void setup() {
        t1 = new Time(8,0,0);
        d1 = new Date(1,1,2020);

        r1 = new Reservations();
        //ue1 = new UserEvent("user", t1, d1, "description");
        ue1 = new UserEvent();
        ue1.setDate(d1);
        ue1.setTime(t1);
        ue1.setUser("user");
        ue1.setId(1);
        ue1.setDescription("description");

        listR = new ArrayList<Reservations>(List.of(r1));
        listR2 = new ArrayList<Reservations>();
        listU = new ArrayList<UserEvent>(List.of(ue1));
        listU2 = new ArrayList<UserEvent>();
        Text eventsText = new Text("3 events");

        userScheduleHandler = new UserScheduleHandler(1,1,2020, listR, listU, eventsText);
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
