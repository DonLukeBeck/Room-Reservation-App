package nl.tudelft.oopp.demo.repositories;


import nl.tudelft.oopp.demo.entities.Bikes;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BikesRepository extends JpaRepository<Bikes, Long> {

}
