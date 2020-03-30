package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.UserEvent;
import nl.tudelft.oopp.demo.entities.Users;
import nl.tudelft.oopp.demo.repositories.PersonaleventsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersEventControllerTest {

    @Mock
    private PersonaleventsRepository personaleventsRepository;

    @InjectMocks
    private UserEventsController userEventsController;

    private Users u1;
    private UserEvent ue1;

    @Test
    public void getAllPersonalEventsTest() {
        u1 = new Users();
        ue1 = new UserEvent();

        personaleventsRepository.save(ue1);

        List<UserEvent> repo = new ArrayList<UserEvent>(List.of(ue1));
        when(personaleventsRepository.findAll()).thenReturn(repo);

        List<UserEvent> actual = userEventsController.getAllPersonalEvents();
        assertEquals(repo, actual);
    }

    @Test
    public void addRoomTest() {
        ue1 = new UserEvent();
        assertTrue(userEventsController.addRoom(ue1));
    }

}
