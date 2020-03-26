package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dishesRepository

public interface DishesRepository extends JpaRepository<Dishes, Long> {
    @Query(value = "SELECT name, price, vegan FROM Dishes "
            + "JOIN `projects_OOPP-Project-29`.Menus ON dishName=name "
            + "WHERE buildingNumber = ?1", nativeQuery = true)
    List<Dishes> findAllByBuildingNumber(int bnr);
}
