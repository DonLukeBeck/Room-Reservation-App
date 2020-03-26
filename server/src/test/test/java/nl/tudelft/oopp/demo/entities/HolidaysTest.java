package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HolidaysTest {

    private Holidays h1;
    private Date d1;
    private Date d2;
    private Date d3;

    @BeforeEach
    public void setup() {
        d1 = new Date(2020, 12, 15);
        d2 = new Date(2021, 01, 6);
        d3 = new Date(2021, 01,7);
        h1 = new Holidays(1, d1, d2, "test");
    }

    @Test
    public void constructorTest() {
        assertNotNull(d1);
    }

    @Test
    public void getCommentsTest() {
        assertEquals("test", h1.getComments());
    }

    @Test
    public void setCommentsTest() {
        h1.setComments("testSetComments");
        assertEquals("testSetComments", h1.getComments());
    }

    @Test
    public void getEndDateTest() {
        assertEquals(d2, h1.getEndDate());
    }

    @Test
    public void setEndDateTest() {
        h1.setEndDate(d3);
        assertEquals(d3, h1.getEndDate());
    }

    @Test
    public void getStartDateTest() {
        assertEquals(d1, h1.getStartDate());
    }

    @Test
    public void setStartDateTest() {
        h1.setStartDate(d2);
        assertEquals(d2, h1.getStartDate());
    }

    @Test
    public void getHolidaysIdTest() {
        assertEquals(1, h1.getHolidaysID());
    }

    @Test
    public void setHolidaysIdTest() {
        h1.setHolidaysID(2);
        assertEquals(2, h1.getHolidaysID());
    }

}
