package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservationsTest {

    private Time timeslot;
    private Time timeslot2;
    private Date date;
    private Date date2;
    private Reservations r1;
    private Reservations r2;

    @BeforeEach
    public void setup() {
        timeslot = new Time(0,45,0);
        timeslot2 = new Time(1,0,0);
        date = new Date(2020, 4, 20);
        date2 = new Date(2020, 4, 28);

        r1 = new Reservations();
        r1.setBuildingReserved(1);
        r1.setUser_reserving("user");
        r1.setTimeslot(timeslot);
        r1.setDate(date);
        r1.setId(1);
        r1.setRoomReserved("1");
        r1.setBikeReserved(1);
        r1.setDishOrdered("testDish");
    }

    @Test
    public void constructorTest() {
        assertNotNull(r1);
    }

    @Test
    public void getIdTest() {
        assertEquals(1, r1.getId());
    }

    @Test
    public void setIdTest() {
        r1.setId(2);
        assertEquals(2, r1.getId());
    }

    @Test
    public void getTimeslotTest() {
        assertEquals(timeslot, r1.getTimeslot());
    }

    @Test
    public void setTimeslotTest() {
        r1.setTimeslot(timeslot2);
        assertEquals(timeslot2, r1.getTimeslot());
    }

    @Test
    public void getDateTest() {
        assertEquals(date, r1.getDate());
    }

    @Test
    public void setDateTest() {
        r1.setDate(date2);
        assertEquals(date2, r1.getDate());
    }

    @Test
    public void getUserReservingTest() {
        assertEquals("user", r1.getUserReserving());
    }

    @Test
    public void setUserReservingTest() {
        r1.setUser_reserving("testSetUserReserving");
        assertEquals("testSetUserReserving", r1.getUserReserving());
    }

    @Test
    public void getRoomReservedTest() {
        assertEquals("1", r1.getRoomReserved());
    }

    @Test
    public void setRoomReservedTest() {
        r1.setRoomReserved("Auditorium");
        assertEquals("Auditorium", r1.getRoomReserved());
    }

    @Test
    public void getBikeReservedTest() {
        assertEquals(1, r1.getBikeReserved());
    }

    @Test
    public void setBikeReservedTest() {
        r1.setBikeReserved(2);
        assertEquals(2, r1.getBikeReserved());
    }

    @Test
    public void getDishOrderedTest() {
        assertEquals("testDish", r1.getDishOrdered());
    }

    @Test
    public void setDishOrderedTest() {
        r1.setDishOrdered("anything");
        assertEquals("anything", r1.getDishOrdered());
    }

    @Test
    public void getBuildingReservedTest() {
        assertEquals(1, r1.getBuilding_reserved());
    }

    @Test
    public void setBuildingReservedTest() {
        r1.setBuildingReserved(42);
        assertEquals(42, r1.getBuilding_reserved());
    }

    @Test
    public void getNiceStringRoomTest() {
        String r1NiceString = "00:45:00:  Room 1 ordered in building 1";
        assertEquals(r1NiceString, r1.getNiceString());
    }

    @Test
    public void getNiceStringDishTest() {
        r1.setRoomReserved(null);
        String expected = "00:45:00:  Dish testDish ordered in building 1";
        assertEquals(expected, r1.getNiceString());
    }

    @Test
    public void getNiceStringBikeTest() {
        r1.setRoomReserved(null);
        r1.setDishOrdered(null);
        String expected = "00:45:00:  Bike ordered in building 1";
        assertEquals(expected, r1.getNiceString());
    }


    @Test
    public void compareToTest() {
        r2 = new Reservations();
        r2.setDate(date2);
        r2.setTimeslot(timeslot2);
        assertEquals(-1, r1.compareTo(r2));
    }


}

