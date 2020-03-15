package nl.tudelft.oopp.demo.repositories;


import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BuildingsRepository extends JpaRepository<Buildings, Long> {
    @Query(value = "SELECT * FROM buildings WHERE building_number = ?1 LIMIT 1", nativeQuery = true)
    Buildings findBuildingsByBuildingNumber(int building_number);
}
