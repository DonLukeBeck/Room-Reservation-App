package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyChar;
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

    @Test
    public void addRoomFalseTest() {
        r1 = new Rooms();
        r1.setRoomId("r1");
        when(roomsRepository.findRoomsByRoomId(r1.getRoomId())).thenReturn(r1);
        assertFalse(roomsController.addRoom(r1));
    }

    @Test
    public void addRoomFalseCase2Test() {
        r1 = new Rooms();
        r1.setRoomId("");
        when(roomsRepository.findRoomsByRoomId(r1.getRoomId())).thenReturn(r1);
        assertFalse(roomsController.addRoom(r1));
    }

    @Test
    public void editRoomTrueTest() {
        r1 = new Rooms();

        when(roomsRepository.updateExistingRoom(r1.getRoomId(), r1.getChairs(), r1.getWhiteboards(),
                r1.getTables(), r1.getComputers(), r1.getType(), r1.getRoomId())).thenReturn(1);

        assertTrue(roomsController.editRoom(r1, r1.getRoomId()));
    }

    @Test
    public void editRoomFalseTest() {
        r1 = new Rooms();

        when(roomsRepository.updateExistingRoom(r1.getRoomId(), r1.getChairs(), r1.getWhiteboards(),
                r1.getTables(), r1.getComputers(), r1.getType(), r1.getRoomId())).thenReturn(0);

        assertFalse(roomsController.editRoom(r1, r1.getRoomId()));
    }

    @Test
    public void editRoomFalseCase2Test() {
        r1 = new Rooms();

        when(roomsRepository.updateExistingRoom(r1.getRoomId(), r1.getChairs(), r1.getWhiteboards(),
                r1.getTables(), r1.getComputers(), r1.getType(), r1.getRoomId())).thenThrow(NullPointerException.class);

        assertFalse(roomsController.editRoom(r1, r1.getRoomId()));
    }

    @Test
    public void deleteRoomsTest() {
        r1 = new Rooms();

        when(roomsRepository.deleteRoomByRoomID(r1.getRoomId())).thenReturn(true);
        assertTrue(roomsController.deleteRoom(r1.getRoomId()));
    }

    @Test
    public void getRoomsByBuildings() {
        r1 = new Rooms();
        r1.setAssociatedBuilding(1);

        List<Rooms> repo = new ArrayList<>(List.of(r1));
        when(roomsRepository.findRoomsByBuildingId(r1.getAssociatedBuilding())).thenReturn(repo);
        List<Rooms> actual = roomsController.getRoomsByBuilding(1);

        assertEquals(repo, actual);
    }

}
