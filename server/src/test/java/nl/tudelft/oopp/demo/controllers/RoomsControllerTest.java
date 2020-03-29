package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Rooms;
import nl.tudelft.oopp.demo.repositories.RoomsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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



}
