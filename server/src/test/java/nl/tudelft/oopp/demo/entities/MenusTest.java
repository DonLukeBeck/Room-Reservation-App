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
        m1.setDeliveryPlace("Menu1Location");
        m1.setBuilding(1);
    }

    @Test
    public void constructorTest() {
        assertNotNull(m1);
    }

    @Test
    public void getDeliveryPlaceTest() {
        assertEquals("Menu1Location", m1.getDeliveryPlace());
    }

    @Test
    public void setDeliveryPlaceTest() {
        m1.setDeliveryPlace("testSetDeliveryPlace");
        assertEquals("testSetDeliveryPlace", m1.getDeliveryPlace());
    }

    @Test
    public void getMenuBuildingTest() {
        assertEquals(1, m1.getBuilding());
    }

    @Test
    public void setMenuBuildingTest() {
        m1.setBuilding(2);
        assertEquals(2, m1.getBuilding());
    }

}

