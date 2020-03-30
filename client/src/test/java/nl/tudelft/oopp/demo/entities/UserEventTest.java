package nl.tudelft.oopp.demo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserEventTest {

    private UserEvent ue1;
    private Date d1;
    private Date d2;
    private Time t1;
    private Time t2;

    @BeforeEach
    public void setup() {
        d1 = new Date(2020,1,1);
        d2 = new Date(2020,1,2);
        t1 = new Time(8,0,0);
        t2 = new Time(9,0,0);

        ue1 = new UserEvent("User", t1, d1, "Description");

    }

    @Test
    public void constructorTest() {
        assertNotNull(ue1);
    }

    @Test
    public void getUserTest() {
        assertEquals("User", ue1.getUser());
    }

    @Test
    public void setUserTest() {
        ue1.setUser("Admin");
        assertEquals("Admin", ue1.getUser());
    }

    @Test
    public void getTimeTest() {
        assertEquals(t1, ue1.getTime());
    }

    @Test
    public void setTimeTest() {
        ue1.setTime(t2);
        assertEquals(t2, ue1.getTime());
    }

    @Test
    public void getDateTest() {
        assertEquals(d1, ue1.getDate());
    }

    @Test
    public void setDateTest() {
        ue1.setDate(d2);
        assertEquals(d2, ue1.getDate());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("Description", ue1.getDescription());
    }

    @Test
    public void setDescriptionTest() {
        ue1.setDescription("test");
        assertEquals("test", ue1.getDescription());
    }


}
