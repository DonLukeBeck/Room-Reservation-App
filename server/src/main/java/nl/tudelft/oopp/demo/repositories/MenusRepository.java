package nl.tudelft.oopp.demo.repositories;

import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Menus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called menusRepository

public interface MenusRepository extends JpaRepository<Menus, Long> {

    @Query(value = "INSERT INTO Menus (buildingNumber, dishName) Values (?1,?2)", nativeQuery = true)
    @Modifying
    @Transactional
    int addMenu(int buildingNumber,String dishName);

    @Query(value = "SELECT * FROM Menus"
            + " WHERE buildingNumber = ?1 AND dishName =?2  LIMIT 1", nativeQuery = true)
    Menus findMenu(int buildingNumber, String dishName);

    @Query(value = "UPDATE Menus "
            + "SET buildingNumber = ?1,  dishName= ?2,"
            + "WHERE dishName = ?3", nativeQuery = true)
    @Modifying
    @Transactional
    int updateExistingMenu(int buildingNumber,String newDishName, String oldDishName);

    @Query(value = "DELETE FROM Menus "
            + "WHERE buildingNumber = ?1 AND dishName = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteMenu(int buildingNumber, String dishName);

}
