package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dishesRepository

public interface DishesRepository extends JpaRepository<Dishes, Long> {
    @Query(value = "SELECT name, price, vegan FROM Dishes "
            + "JOIN `projects_OOPP-Project-29`.Menus ON dishName=name "
            + "WHERE buildingNumber = ?1", nativeQuery = true)
    List<Dishes> findAllByBuildingNumber(int bnr);

    @Query(value = "SELECT * FROM Dishes WHERE name = ?1 LIMIT 1", nativeQuery = true)
    Dishes findDishesByName(String name);

    @Query(value = "UPDATE Dishes "
            + "SET name = ?1, price = ?2, vegan = ?3,"
            + "WHERE name = ?4", nativeQuery = true)
    @Modifying
    @Transactional
    int updateExistingDish(String name, int price, int vegan, String oldName);

    @Query(value = "DELETE FROM Dishes WHERE name = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteDishByName(String name);
}
