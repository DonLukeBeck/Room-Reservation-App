package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomsTest {

    private Rooms room1;

    @BeforeEach
    public void setup() {
        room1 = new Rooms();
        room1.setAssociatedBuilding(36);
        room1.setRoomId("Auditorium");
        room1.setType("Lecture Hall");
        room1.setChairs(1);
        room1.setComputers(1);
        room1.setWhiteboards(1);
        room1.setTables(1);
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
    public void getTypeTest() {
        assertEquals("Lecture Hall", room1.getType());
    }

    @Test
    public void setTypeTest() {
        room1.setType("Exam Hall");
        assertEquals("Exam Hall", room1.getType());
    }

    @Test
    public void getAssociatedBuildingTest() {
        assertEquals(36, room1.getAssociatedBuilding());
    }

    @Test
    public void setAssociatedBuildingTest() {
        room1.setAssociatedBuilding(42);
        assertEquals(42, room1.getAssociatedBuilding());
    }

    @Test
    public void getChairsTest() {
        assertEquals(1, room1.getChairs());
    }

    @Test
    public void setChairsTest() {
        room1.setChairs(2);
        assertEquals(2, room1.getChairs());
    }

    @Test
    public void getComputersTest() {
        assertEquals(1, room1.getComputers());
    }

    @Test
    public void setComputersTest() {
        room1.setComputers(2);
        assertEquals(2, room1.getComputers());
    }

    @Test
    public void getWhiteboardsTest() {
        assertEquals(1, room1.getWhiteboards());
    }

    @Test
    public void setWhiteboardsTest() {
        room1.setWhiteboards(2);
        assertEquals(2, room1.getWhiteboards());
    }

    @Test
    public void getTablesTest() {
        assertEquals(1, room1.getTables());
    }

    @Test
    public void setTablesTest() {
        room1.setTables(2);
        assertEquals(2, room1.getTables());
    }


}

