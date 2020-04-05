package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

// This will be AUTO IMPLEMENTED by Spring

public interface PersonaleventsRepository extends JpaRepository<UserEvent, Long> {
    @Query(value = "DELETE FROM UserEvent WHERE id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteUserEventByID(String id);

    @Query(value = "SELECT * FROM UserEvent WHERE id = (SELECT MAX(id) FROM UserEvent)", nativeQuery = true)
    UserEvent getLastUserEvent();
}
