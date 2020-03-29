package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Holidays;
import nl.tudelft.oopp.demo.repositories.HolidaysRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HolidaysControllerTest {

    @Mock
    HolidaysRepository holidaysRepository;

    @InjectMocks
    HolidaysController holidaysController;

    private Holidays h1;
    private Holidays h2;

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


}
