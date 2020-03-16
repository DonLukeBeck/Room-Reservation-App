package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Rooms;
import nl.tudelft.oopp.demo.repositories.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     *
     * @param room
     * @return
     */
    @PostMapping("/addRoom") // Map ONLY POST Requests
    public @ResponseBody
    boolean addRoom(@RequestBody Rooms room) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            if (!roomsRepository.findRoomsByRoomId(room.getRoomId()).getRoomId().isEmpty()) {
                return false;
            }
            return false;
        } catch (NullPointerException e) {
            Rooms newRoom = new Rooms();
            System.out.println(room.getAssociated_building());
            newRoom.setAssociated_building(room.getAssociated_building());
            newRoom.setCapacity(room.getCapacity());
            newRoom.setRoomId(room.getRoomId());
            newRoom.setType(room.getType());
            roomsRepository.save(newRoom);
            return true;
        }

    }

}


