package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.repositories.ReservationsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationsControllerTest {

    @Mock
    private ReservationsRepository reservationsRepository;

    @InjectMocks
    private ReservationsController reservationsController;

    private Reservations r1;
    private Reservations r2;
    private Date d1;
    private Time t1;

    @Test
    public void getAllReservationsTest() {
        r1 = new Reservations();
        r2 = new Reservations();

        reservationsRepository.save(r1);
        reservationsRepository.save(r2);

        List<Reservations> repo = new ArrayList<>(List.of(r1,r2));

        when(reservationsRepository.findAll()).thenReturn(repo);

        List<Reservations> actual = reservationsController.getAllReservations();

        assertEquals(repo, actual);
    }

    @Test
    public void registerReservationTrueTest() {
        r1 = new Reservations();
        assertTrue(reservationsController.register(r1));
    }

    /*
    @Test
    public void registerReservationContentTest() {
        r1 = new Reservations();
        r1.setDishOrdered("dish");
        r1.setBikeReserved(1);
        r1.setRoomReserved("room");
        r1.setId(1);
        r1.setBuildingReserved(1);
        r1.setUserReserving("user");
        r1.setDate(d1);
        r1.setTimeslot(t1);
        reservationsController.register(r1);
        List<Reservations> actual = reservationsController.getAllReservations();
        List<Reservations> expected = new ArrayList<>(List.of(r1));
        assertEquals(expected, actual);
    }

     */
}
