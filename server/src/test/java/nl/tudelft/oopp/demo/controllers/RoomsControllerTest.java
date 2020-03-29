package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Rooms;
import nl.tudelft.oopp.demo.repositories.RoomsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoomsControllerTest {

    @Mock
    private RoomsRepository roomsRepository;

    @InjectMocks
    private RoomsController roomsController;

    private Rooms r1;
    private Rooms r2;

    @Test
    public void getAllRoomsTest() {
        r1 = new Rooms();
        r2 = new Rooms();

        roomsRepository.save(r1);
        roomsRepository.save(r2);

        List<Rooms> repo = new ArrayList<>(List.of(r1,r2));
        when(roomsRepository.findAll()).thenReturn(repo);

        List<Rooms> actual = roomsController.getAllRooms();

        assertEquals(repo, actual);

    }

    @Test
    public void addRoomTrueTest() {
        r1 = new Rooms();
        assertTrue(roomsController.addRoom(r1));
    }

    /*
    @Test
    public void addRoomFalseTest() {
        r1 = new Rooms();
        roomsController.addRoom(r1);
        assertFalse(roomsController.addRoom(r1));
    }

    @Test
    public void addRoomTest() {
        r1 = new Rooms();
        r1.setAssociatedBuilding(1);
        r1.setRoomId("Room");
        r1.setCapacity(1);
        r1.setType("Lecture Hall");

        roomsController.addRoom(r1);

        List<Rooms> actual = roomsController.getAllRooms();
        List<Rooms> expected = new ArrayList<>(List.of(r1));

        assertEquals(expected, actual);

    }
     */

}
