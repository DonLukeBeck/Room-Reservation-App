package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called roomsRepository

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    @Query(value = "SELECT * FROM Rooms WHERE roomId = ?1 LIMIT 1", nativeQuery = true)
    Rooms findRoomsByRoomId(String roomId);

    @Query(value = "SELECT * FROM Rooms WHERE associatedBuilding = ?1", nativeQuery = true)
    List<Rooms> findRoomsByBuildingId(int buildingId);

    @Query(value = "UPDATE Rooms "
            + "SET roomdId = ?1, capacity = ?2, type = ?3 "
            + "WHERE roomId =?4", nativeQuery = true)
    boolean updateExistingRoom(String newRoomId, int newCapacity, String newType, String oldRoomId);

    @Query(value = "DELETE FROM Rooms WHERE roomdId = ?1", nativeQuery = true)
    boolean deleteRoomByRoomID(String roomId);

}
