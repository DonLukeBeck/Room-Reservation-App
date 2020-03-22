package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DishesTest {

    private Dishes d1;

    @BeforeEach
    public void setup() {
        d1 = new Dishes("dish1", 10, 1, "Menu1");
    }

    @Test
    public void constructorTest() {
        assertNotNull(d1);
    }

    @Test
    public void getNameTest() {
        assertEquals("dish1", d1.getName());
    }

    @Test
    public void setNameTest() {
        d1.setName("setNameTest");
        assertEquals("setNameTest", d1.getName());
    }

    @Test
    public void getPriceTest() {
        assertEquals(10, d1.getPrice());
    }

    @Test
    public void setPriceTest() {
        d1.setPrice(20);
        assertEquals(20, d1.getPrice());
    }

    @Test
    public void getVeganTest() {
        assertEquals(1, d1.getVegan());
    }

    @Test
    public void setVeganTest() {
        d1.setVegan(2);
        assertEquals(2, d1.getVegan());
    }

    @Test
    public void getMenuAssociatedTest() {
        assertEquals("Menu1", d1.getMenuAssociated());
    }

    @Test
    public void setMenuAssociatedTest() {
        d1.setMenuAssociated("Menu2");
        assertEquals("Menu2", d1.getMenuAssociated());
    }

}
