package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.repositories.BuildingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public class BuildingsControllerTest {

    private MockMvc mvc;

    @Mock
    private BuildingsRepository buildingsRepository;

    @InjectMocks
    private BuildingsController buildingsController;

    private Buildings b1;
    private Buildings b2;
    private Time t1;
    private Time t2;


    @Test
    public void getAllBuildingsTest() {
        b1 = new Buildings();
        b2 = new Buildings();

        buildingsRepository.save(b1);
        buildingsRepository.save(b2);

        List<Buildings> repo = new ArrayList<>(List.of(b1,b2));
        when(buildingsRepository.findAll()).thenReturn(repo);

        List<Buildings> actual = buildingsController.getAllBuildings();

        assertEquals(repo, actual);
    }

    @Test
    public void addBuildingTrueTest() {
        b1 = new Buildings();

        assertTrue(buildingsController.addBuilding(b1));
    }
    /*
    @Test
    public void addBuildingFalseTest() {
        b2 = new Buildings();

        //when(buildingsRepository.save(b2)).thenReturn();

        buildingsController.addBuilding(b2);
        List<Buildings> actual = buildingsController.getAllBuildings();
        List<Buildings> expected = new ArrayList<>(List.of(b2));
        assertEquals(expected, actual);
        //buildingsRepository.save(b2);
        //assertFalse(buildingsController.addBuilding(b2));

    }

    @Test
    public void addBuildingContentTest() {
        b1 = new Buildings();
        b1.setBuildingNumber(1);
        b1.setClosingHours(t2);
        b1.setOpeningHours(t1);
        b1.setNumberOfRooms(10);
        b1.setNumberOfBikes(100);
        b1.setName("Building");
        b1.setUrl("building.org");

        buildingsController.addBuilding(b1);
        List<Buildings> actual = buildingsController.getAllBuildings();
        List<Buildings> expected = buildingsRepository.findAll();
        System.out.println(actual);
        System.out.println(expected);

    }
*/

}
