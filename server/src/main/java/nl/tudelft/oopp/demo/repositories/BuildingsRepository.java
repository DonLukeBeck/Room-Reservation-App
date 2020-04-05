package nl.tudelft.oopp.demo.repositories;

import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called buildingsRepository

public interface BuildingsRepository extends JpaRepository<Buildings, Long> {
    @Query(value = "SELECT * FROM Buildings WHERE buildingNumber = ?1 LIMIT 1", nativeQuery = true)
    Buildings findBuildingsByBuildingNumber(int buildingNumber);

    @Query(value = "DELETE FROM Buildings WHERE buildingNumber = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteBuildingByBuildingNumber(int buildingNumber);

}
