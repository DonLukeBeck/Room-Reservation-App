package nl.tudelft.oopp.demo.repositories;


import nl.tudelft.oopp.demo.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RoomsRepository extends JpaRepository<Rooms, Long> {

}
