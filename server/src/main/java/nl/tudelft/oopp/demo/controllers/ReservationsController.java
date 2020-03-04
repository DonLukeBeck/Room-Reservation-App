package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.repositories.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class ReservationsController {

    @Autowired
    private ReservationsRepository reservationsRepository;

    @GetMapping("/allReservations")
    public @ResponseBody
    Iterable<Reservations> getAllReservations() {
        // This returns a JSON or XML with the reservations
        return reservationsRepository.findAll();
    }

}


