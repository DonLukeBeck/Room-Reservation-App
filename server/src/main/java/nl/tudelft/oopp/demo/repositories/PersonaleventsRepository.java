package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring

public interface PersonaleventsRepository extends JpaRepository<UserEvent, Long> {
}
