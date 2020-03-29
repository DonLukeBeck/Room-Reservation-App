package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.UserEvent;
import nl.tudelft.oopp.demo.repositories.PersonaleventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
