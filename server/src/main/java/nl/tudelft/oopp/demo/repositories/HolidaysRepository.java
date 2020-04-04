package nl.tudelft.oopp.demo.repositories;

import java.util.Date;
import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called holidaysRepository

public interface HolidaysRepository extends JpaRepository<Holidays, Long> {
    @Query(value = "UPDATE Holidays "
            + "SET startDate = ?1, endDate = ?2, comments = ?3,"
            + "WHERE holidaysId = ?4", nativeQuery = true)
    @Modifying
    @Transactional
    int updateExistingHolidays(Date startDate, Date endDate, String comments, int holidaysId);

    @Query(value = "DELETE FROM Holidays WHERE holidaysId = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    boolean deleteHolidaysById(int holidaysId);
}
