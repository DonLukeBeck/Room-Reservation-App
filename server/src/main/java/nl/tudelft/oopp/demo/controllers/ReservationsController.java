package nl.tudelft.oopp.demo.controllers;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.repositories.BuildingsRepository;
import nl.tudelft.oopp.demo.repositories.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // This means that this class is a Controller
public class ReservationsController {

    @Autowired
    private ReservationsRepository reservationsRepository;
    private BuildingsRepository buildingsRepository;

    @GetMapping("/allReservations")
    public @ResponseBody
    List<Reservations> getAllReservations() {
        // This returns a JSON or XML with the reservations
        return reservationsRepository.findAll();
    }

    /**
     * Adds a room reservation to the database.
     *
     * @param reservation - reservation to be added to the database
     * @return true when the reservation is saved
     */
    @PostMapping("/postRoomReservation") // Map ONLY POST Requests
    public @ResponseBody
    boolean postRoomReservation(@RequestBody Reservations reservation) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Reservations newReservation = new Reservations();
        newReservation.setDate(reservation.getDate());
        newReservation.setTimeslot(reservation.getTimeslot());
        newReservation.setUserReserving(reservation.getUserReserving());
        newReservation.setRoomReserved(reservation.getRoomReserved());
        newReservation.setBuildingReserved(reservation.getBuildingReserved());

        reservationsRepository.save(newReservation);
        return true;
    }

    /**
     * Adds a food reservation to the database.
     *
     * @param reservation - reservation to be added to the database
     * @return true when the reservation is saved
     */
    @PostMapping("/postFoodReservation") // Map ONLY POST Requests
    public @ResponseBody
    boolean postFoodReservation(@RequestBody Reservations reservation) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Reservations newReservation = new Reservations();
        newReservation.setDate(reservation.getDate());
        newReservation.setTimeslot(reservation.getTimeslot());
        newReservation.setUserReserving(reservation.getUserReserving());
        newReservation.setDishOrdered(reservation.getDishOrdered());
        newReservation.setBuildingReserved(reservation.getBuildingReserved());

        reservationsRepository.save(newReservation);
        return true;
    }

    /**
     * Adds a bike reservation to the database.
     *
     * @param reservation - reservation to be added to the database
     * @return true when the reservation is saved
     */
    @PostMapping("/postBikeReservation") // Map ONLY POST Requests
    public @ResponseBody
    boolean postBikeReservation(@RequestBody Reservations reservation) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Reservations newReservation = new Reservations();
        newReservation.setDate(reservation.getDate());
        newReservation.setTimeslot(reservation.getTimeslot());
        newReservation.setUserReserving(reservation.getUserReserving());
        newReservation.setBikeReserved(1);
        newReservation.setBuildingReserved(reservation.getBuildingReserved());

        reservationsRepository.save(newReservation);
        return true;
    }


    /**
     * Retrieves available bikes from the database.
     *
     * @param buildingNumber - building of the reservation
     * @param date - date of the reservation
     * @param timeslot - timeslot of the reservation
     * @return true if there are available bikes
     */
    @GetMapping("/getAvailableBikes")
    public @ResponseBody
    boolean getAvailableBikes(@RequestParam int buildingNumber, Date date, Time timeslot) {

        int totalBikes = buildingsRepository
                .findBuildingsByBuildingNumber(buildingNumber)
                .getNumberOfBikes();
        int reservedBikes = reservationsRepository.reservedBikes(buildingNumber, date, timeslot);

        if (totalBikes > reservedBikes) {
            return true;
        } else {
            return false;
        }
    }

}


