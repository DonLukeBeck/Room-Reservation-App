package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.UserEvent;
import nl.tudelft.oopp.demo.repositories.PersonaleventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserEventsController {

    @Autowired
    private PersonaleventsRepository personaleventsRepository;

    @GetMapping("/allPersonalEvents")
    public @ResponseBody
    List<UserEvent> getAllPersonalEvents() {
        // This returns a JSON or XML with the reservations
        return personaleventsRepository.findAll();
    }

    /**
     * Method to add event to repository.
     * @param userEvent Event to be added to repository
     * @return True if event saved to repository
     */
    @PostMapping("/postUserEvent") // Map ONLY POST Requests
    public @ResponseBody
    UserEvent addUserEvent(@RequestBody UserEvent userEvent) {
        // @ResponseBody means the returned String is the response, not a view name
        UserEvent newUserEvent = new UserEvent();
        newUserEvent.setUser(userEvent.getUser());
        newUserEvent.setTime(userEvent.getTime());
        newUserEvent.setDate(userEvent.getDate());
        newUserEvent.setDescription(userEvent.getDescription());
        personaleventsRepository.save(newUserEvent);
        return personaleventsRepository.getLastUserEvent();
    }

    @GetMapping("/deleteUserEvent")
    public @ResponseBody
    boolean deleteUserEvent(@RequestParam String id) {
        // This returns a JSON or XML with the reservations
        personaleventsRepository.deleteUserEventByID(id);
        return true;
    }
}
