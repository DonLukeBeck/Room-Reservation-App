package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dishesRepository

public interface DishesRepository extends JpaRepository<Dishes, Long> {

}
