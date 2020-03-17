package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called holidaysRepository

public interface HolidaysRepository extends JpaRepository<Holidays, Long> {

}
