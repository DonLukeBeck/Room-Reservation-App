package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

class BuildingsTest {

    private Time time1;
    private Time time2;
    private Time time3;
    private Buildings b1;

    @BeforeEach
    public void setup() {
        time1 = new Time(8,0,0);
        time2 = new Time(20,0,0);
        time3 = new Time(9,0,0);
        b1 = new Buildings();
        b1.setBuildingNumber(1);
        b1.setName("Building1");
        b1.setOpeningHours(time1);
        b1.setClosingHours(time2);
        b1.setNumberOfBikes(100);
        b1.setUrl("building1.org");
    }

    @Test
    public void constructorTest() {
        assertNotNull(b1);
    }

    @Test
    public void getBuildingNumberTest() {
        assertEquals(1, b1.getBuilding_number());
    }

    @Test
    public void setBuildingNumberTest() {
        b1.setBuildingNumber(2);
        assertEquals(2, b1.getBuilding_number());
    }

    @Test
    public void getNameTest() {
        assertEquals("Building1", b1.getName());
    }

    @Test
    public void setNameTest() {
        b1.setName("Building 10");
        assertEquals("Building 10", b1.getName());
    }

    @Test
    public void getOpeningHoursTest() {
        assertEquals(time1, b1.getOpeningHours());
    }

    @Test
    public void setOpeningHoursTest() {
        b1.setOpeningHours(time3);
        assertEquals(time3, b1.getOpeningHours());
    }

    @Test
    public void getClosingHoursTest() {
        assertEquals(time2, b1.getClosingHours());
    }

    @Test
    public void setClosingHoursTest() {
        b1.setClosingHours(time3);
        assertEquals(time3, b1.getClosingHours());
    }

    @Test
    public void getNumberOfBikesTest() {
        assertEquals(100, b1.getNumber_of_bikes());
    }

    @Test
    public void setNumberOfBikesTest() {
        b1.setNumberOfBikes(50);
        assertEquals(50, b1.getNumber_of_bikes());
    }

    @Test
    public void getUrlTest() {
        assertEquals("building1.org", b1.getUrl());
    }

    @Test
    public void setUrlTest() {
        b1.setUrl("b1.org");
        assertEquals("b1.org", b1.getUrl());
    }

}

