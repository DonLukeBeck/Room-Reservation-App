package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import nl.tudelft.oopp.demo.entities.UserEvent;
import nl.tudelft.oopp.demo.entities.Users;
import nl.tudelft.oopp.demo.repositories.PersonaleventsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsersEventControllerTest {

    @Mock
    private PersonaleventsRepository personaleventsRepository;

    @InjectMocks
    private UserEventsController userEventsController;

    private Users u1;
    private UserEvent ue1;
    private Date d1;
    private Time t1;

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
    public void deleteUserEventTest() {
        u1 = new Users();
        assertTrue(userEventsController.deleteUserEvent(u1.getNetid()));
    }


    @Test
    public void addUserEventTest() {
        ue1 = new UserEvent();
        when(personaleventsRepository.getLastUserEvent()).thenReturn(ue1);
        assertEquals(ue1, userEventsController.addUserEvent(ue1));
    }



}
