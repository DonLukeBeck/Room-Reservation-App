package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    @Query(value = "SELECT * FROM Rooms WHERE roomId = ?1 LIMIT 1", nativeQuery = true)
    Rooms findRoomsByRoomId(String roomId);
}
