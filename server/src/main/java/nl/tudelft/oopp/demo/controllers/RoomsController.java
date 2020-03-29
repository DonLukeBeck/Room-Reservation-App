package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Rooms;
import nl.tudelft.oopp.demo.repositories.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // This means that this class is a Controller
public class RoomsController {

    @Autowired
    private RoomsRepository roomsRepository;

    @GetMapping("/allRooms")
    public @ResponseBody
    List<Rooms> getAllRooms() {
        // This returns a JSON or XML with the rooms
        return roomsRepository.findAll();
    }

    /** Adds a room, inserted by the admin, to the database.
     * @param room - room to be added to the database
     * @return true if room successfully added, false otherwise
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
            newRoom.setAssociatedBuilding(room.getAssociatedBuilding());
            newRoom.setChairs(room.getChairs());
            newRoom.setWhiteboards(room.getWhiteboards());
            newRoom.setComputers(room.getComputers());
            newRoom.setTables(room.getTables());
            newRoom.setRoomId(room.getRoomId());
            newRoom.setType(room.getType());
            roomsRepository.save(newRoom);
            return true;
        }
    }

    /** Edits a room, inserted by the admin, in the database.
     * @param room - room to be edited in the database
     * @return true if room successfully edited, false otherwise
     */
    @RequestMapping("/editRoom") // Map ONLY POST Requests
    public @ResponseBody
    boolean editRoom(@RequestBody Rooms room, @RequestParam String oldRoomId) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            if (roomsRepository.updateExistingRoom(room.getRoomId(),
                    room.getChairs(),
                    room.getWhiteboards(),
                    room.getTables(),
                    room.getComputers(),
                    room.getType(),
                    oldRoomId) == 1) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @GetMapping("/deleteRoom")
    public @ResponseBody
    boolean deleteRoom(@RequestParam String roomId) {
        // This returns a JSON or XML with the menus
        return roomsRepository.deleteRoomByRoomID(roomId);
    }

    @GetMapping("/roomsByBuilding")
    public @ResponseBody
    List<Rooms> getRoomsByBuilding(@RequestParam int bnr) {
        // This returns a JSON or XML with the menus
        return roomsRepository.findRoomsByBuildingId(bnr);
    }


}


