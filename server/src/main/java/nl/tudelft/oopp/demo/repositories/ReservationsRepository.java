package nl.tudelft.oopp.demo.repositories;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Reservations;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    @Query(value = "SELECT SUM(bikeReserved) FROM Reservations "
            + "WHERE buildingReserved=?1 AND date=?2 AND timeslot=?3", nativeQuery = true)
    int reservedBikes(int buildingReserved, Date date, Time timeslot);
}
