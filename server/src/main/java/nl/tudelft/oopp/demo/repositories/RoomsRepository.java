package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


// This will be AUTO IMPLEMENTED by Spring into a Bean called roomsRepository

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    @Query(value = "SELECT * FROM Rooms WHERE roomId = ?1 LIMIT 1", nativeQuery = true)
    Rooms findRoomsByRoomId(String roomId);

    @Query(value = "SELECT * FROM Rooms WHERE associatedBuilding = ?1", nativeQuery = true)
    List<Rooms> findRoomsByBuildingId(int buildingId);


    @Query(value = "UPDATE Rooms "
            + "SET roomId = ?1, chairs = ?2, whiteboards = ?3,"
            + " tables = ?4, computers = ?5, type = ?6 "
            + "WHERE roomId = ?7", nativeQuery = true)
    @Modifying
    @Transactional
    int updateExistingRoom(String newRoomId,
                           int newChairs,
                           int newWhiteboards,
                           int newTables,
                           int newComputers,
                           String newType,
                           String oldRoomId);

    @Query(value = "DELETE FROM Rooms WHERE roomId = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteRoomByRoomID(String roomId);

}
