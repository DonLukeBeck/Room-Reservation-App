package nl.tudelft.oopp.demo.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DishesTest {

    @Test
    public void constructorTest() {
        Dishes d1 = new Dishes("test", 10, 1, "testMenu");
        assertNotNull(d1);
    }
}
