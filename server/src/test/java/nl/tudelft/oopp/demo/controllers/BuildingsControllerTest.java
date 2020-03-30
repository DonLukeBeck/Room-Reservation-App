package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

@ExtendWith(MockitoExtension.class)
public class BuildingsControllerTest {

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

}
