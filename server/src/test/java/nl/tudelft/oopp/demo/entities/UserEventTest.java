package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserEventTest {

    private UserEvent ue1;
    private Date d1;
    private Date d2;
    private Time t1;
    private Time t2;

    /**
     * Method to setup all the paramaters before each test.
     */
    @BeforeEach
    public void setup() {
        t1 = new Time(8,0,0);
        t2 = new Time(20,0,0);

        d1 = new Date(2020,4, 1);
        d2 = new Date(2020,4,2);

        ue1 = new UserEvent();
        ue1.setId(1);
        ue1.setDate(d1);
        ue1.setTime(t1);
        ue1.setUser("User");
        ue1.setDescription("Description");
    }

    @Test
    public void constructorTest() {
        assertNotNull(ue1);
    }

    @Test
    public void getIdTest() {
        assertEquals(1, ue1.getId());
    }

    @Test
    public void setIdTest() {
        ue1.setId(2);
        assertEquals(2, ue1.getId());
    }

    @Test
    public void getUserTest() {
        assertEquals("User", ue1.getUser());
    }

    @Test
    public void setUserTest() {
        ue1.setUser("fezard");
        assertEquals("fezard", ue1.getUser());
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
