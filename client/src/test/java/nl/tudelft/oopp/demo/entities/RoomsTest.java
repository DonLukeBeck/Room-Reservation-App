package nl.tudelft.oopp.demo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomsTest {

    private Rooms room1;

    @BeforeEach
    public void setup(){
    room1 = new Rooms("Auditorium", 300, "Lecture Hall", 36);
    }

    @Test
    public void constructorTest() {
        assertNotNull(room1);
    }

    @Test
    public void getRoomIdTest() {
        assertEquals("Auditorium", room1.getRoomId());
    }

    @Test
    public void setRoomIdTest() {
        room1.setRoomId("Nothing");
        assertEquals("Nothing", room1.getRoomId());
    }

    @Test
    public void getCapacityTest() {
        assertEquals(300, room1.getCapacity());
    }

    @Test
    public void setCapacityTest() {
        room1.setCapacity(500);
        assertEquals(500, room1.getCapacity());
    }

    @Test
    public void getTypeTest() {
        assertEquals("Lecture Hall", room1.getType());
    }

    @Test
    public void setTypeTest(){
        room1.setType("Exam Hall");
        assertEquals("Exam Hall", room1.getType());
    }

    @Test
    public void getAssociatedBuildingTest(){
        assertEquals(36, room1.getAssociatedBuilding());
    }

    @Test
    public void setAssociatedBuildingTest(){
        room1.setAssociatedBuilding(42);
        assertEquals(42, room1.getAssociatedBuilding());
    }

}
