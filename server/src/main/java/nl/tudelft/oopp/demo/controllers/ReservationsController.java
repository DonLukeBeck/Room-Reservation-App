package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.repositories.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class ReservationsController {

    @Autowired
    private ReservationsRepository reservationsRepository;

    @GetMapping("/allReservations")
    public @ResponseBody
    List<Reservations> getAllReservations() {
        // This returns a JSON or XML with the reservations
        return reservationsRepository.findAll();
    }

    /**
     *
     * @param reservation
     * @return
     */
    @PostMapping("/postReservation") // Map ONLY POST Requests
    public @ResponseBody
    boolean register(@RequestBody Reservations reservation) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Reservations newReservation = new Reservations();
        newReservation.setDate(reservation.getDate());
        newReservation.setTimeslot(reservation.getTimeslot());
        newReservation.setUserReserving(reservation.getUserReserving());
        newReservation.setRoomReserved(reservation.getRoomReserved());
        newReservation.setBuildingReserved(reservation.getBuildingReserved());

        System.out.println(newReservation.getId());
        System.out.println(newReservation.getUserReserving());
        System.out.println(newReservation.getRoomReserved());
        System.out.println(newReservation.getTimeslot());
        System.out.println(newReservation.getDate());

        reservationsRepository.save(newReservation);
        return true;
    }

}


