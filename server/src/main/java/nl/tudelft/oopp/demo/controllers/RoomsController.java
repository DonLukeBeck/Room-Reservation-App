package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Rooms;
import nl.tudelft.oopp.demo.repositories.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class RoomsController {

    @Autowired
    private RoomsRepository roomsRepository;

    @GetMapping("/allRooms")
    public @ResponseBody
    List<Rooms> getAllRooms() {
        // This returns a JSON or XML with the buildings
        return roomsRepository.findAll();
    }

}


