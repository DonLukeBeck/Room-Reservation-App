package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenusTest {

    private Menus m1;

    @BeforeEach
    public void setup() {
        m1 = new Menus();
        m1.setDishName("burger");
        m1.setBuildingNumber(1);
    }

    @Test
    public void constructorTest() {
        assertNotNull(m1);
    }

    @Test
    public void getDishNameTest() {
        assertEquals("burger", m1.getDishName());
    }

    @Test
    public void setDishNameTest() {
        m1.setDishName("testSetDishName");
        assertEquals("testSetDishName", m1.getDishName());
    }

    @Test
    public void getMenuBuildingTest() {
        assertEquals(1, m1.getBuildingNumber());
    }

    @Test
    public void setMenuBuildingTest() {
        m1.setBuildingNumber(2);
        assertEquals(2, m1.getBuildingNumber());
    }

}

