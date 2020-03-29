package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.repositories.BuildingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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
    public void addBuildingTest() {
       /* b1.setName("test");
        b1.setBuildingNumber(0);
        b1.setUrl("test.org");
        b1.setNumberOfBikes(100);
        b1.setNumberOfRooms(10);
        b1.setOpeningHours(t1);
        b1.setClosingHours(t1);
        */
        buildingsController.addBuilding(b1);
        List<Buildings> list = new ArrayList<>(buildingsController.getAllBuildings());

    }

}
