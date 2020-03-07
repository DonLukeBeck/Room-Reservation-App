package nl.tudelft.oopp.demo.repositories;


import nl.tudelft.oopp.demo.entities.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DishesRepository extends JpaRepository<Dishes, Long> {

}