package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Holidays;
import nl.tudelft.oopp.demo.repositories.HolidaysRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
public class HolidaysControllerTest {

    @Mock
    HolidaysRepository holidaysRepository;

    @InjectMocks
    HolidaysController holidaysController;

    private Holidays h1;
    private Holidays h2;
    private Date d1;

    @Test
    public void getAllHolidaysTest() {
        h1 = new Holidays();
        h2 = new Holidays();

        holidaysRepository.save(h1);
        holidaysRepository.save(h2);

        List<Holidays> repo = new ArrayList<>(List.of(h1,h2));

        when(holidaysRepository.findAll()).thenReturn(repo);

        List<Holidays> actual = holidaysController.getAllHolidays();

        assertEquals(repo, actual);

    }

    @Test
    public void editHolidaysTrueTest() {
        h1 = new Holidays();
        when(holidaysRepository.updateExistingHolidays(h1.getStartDate(), h1.getEndDate(),
                h1.getComments(), h1.getHolidaysID())).thenReturn(1);
        assertTrue(holidaysController.editHolidays(h1));
    }

    @Test
    public void editHolidaysFalseTest() {
        h1 = new Holidays();
        assertFalse(holidaysController.editHolidays(h1));
    }

    @Test
    public void editHolidaysFalseCase2Test() {
        h1 = new Holidays();
        when(holidaysRepository.updateExistingHolidays(h1.getStartDate(), h1.getEndDate(),
                h1.getComments(), h1.getHolidaysID())).thenThrow(NullPointerException.class);
        assertFalse(holidaysController.editHolidays(h1));
    }

    @Test
    public void deleteHolidaysTest() {
        h1 = new Holidays();
        when(holidaysRepository.deleteHolidaysById(h1.getHolidaysID())).thenReturn(1);
        assertTrue(holidaysController.deleteHolidays(h1.getHolidaysID()));
    }

    @Test
    public void deleteHolidaysFalseTest() {
        h1 = new Holidays();
        when(holidaysRepository.deleteHolidaysById(h1.getHolidaysID())).thenReturn(0);
        assertFalse(holidaysController.deleteHolidays(h1.getHolidaysID()));
    }

    @Test
    public void deleteHolidaysFalseCase2Test() {
        h1 = new Holidays();
        when(holidaysRepository.deleteHolidaysById(
                h1.getHolidaysID())).thenThrow(NullPointerException.class);
        assertFalse(holidaysController.deleteHolidays(h1.getHolidaysID()));
    }



    @Test
    public void addHolidaysTrueTest() {
        h1 = new Holidays();
        assertTrue(holidaysController.addHolidays(h1));
    }

    @Test
    public void addHolidaysFalseTest() {
        h1 = new Holidays();
        when(holidaysRepository.save(h1)).thenThrow(DataIntegrityViolationException.class);
        assertFalse(holidaysController.addHolidays(h1));
    }

}
