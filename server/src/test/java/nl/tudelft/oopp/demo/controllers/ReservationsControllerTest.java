package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.repositories.BuildingsRepository;
import nl.tudelft.oopp.demo.repositories.ReservationsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReservationsControllerTest {

    @Mock
    private ReservationsRepository reservationsRepository;

    @Mock
    private BuildingsRepository buildingsRepository;

    @InjectMocks
    private ReservationsController reservationsController;

    private Reservations r1;
    private Reservations r2;
    private Date d1;
    private Time t1;
    private Buildings b1;

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
    public void postRoomReservationsTest() {
        r1 = new Reservations();
        assertTrue(reservationsController.postRoomReservation(r1));
    }

    @Test
    public void postFoodReservationsTest() {
        r1 = new Reservations();
        assertTrue(reservationsController.postFoodReservation(r1));
    }

    @Test
    public void postBikeReservationsTest() {
        r1 = new Reservations();
        assertTrue(reservationsController.postBikeReservation(r1));
    }

    @Test
    public void getAvailableBikesTrueTest() {
        r1 = new Reservations();
        b1 = new Buildings();

        b1.setBuildingNumber(1);
        b1.setNumberOfBikes(10);

        when(buildingsRepository.findBuildingsByBuildingNumber(1)).thenReturn(b1);
        when(reservationsRepository.reservedBikes(1, d1, t1)).thenReturn(9);

        assertTrue(reservationsController.getAvailableBikes(1, d1, t1));
    }

    @Test
    public void getAvailableBikesFalseTest() {
        r1 = new Reservations();
        b1 = new Buildings();

        b1.setBuildingNumber(1);
        b1.setNumberOfBikes(10);

        when(buildingsRepository.findBuildingsByBuildingNumber(1)).thenReturn(b1);
        when(reservationsRepository.reservedBikes(1, d1, t1)).thenReturn(10);

        assertFalse(reservationsController.getAvailableBikes(1, d1, t1));
    }

}
