package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void addBuildingFalseTest() {
        b1 = new Buildings();
        b1.setBuildingNumber(1);

        when(buildingsRepository.findBuildingsByBuildingNumber(1)).thenReturn(b1);
        assertFalse(buildingsController.addBuilding(b1));
    }

    @Test
    public void addBuildingFalseCase2Test() {
        b1 = new Buildings();
        b1.setBuildingNumber(0);

        when(buildingsRepository.findBuildingsByBuildingNumber(0)).thenReturn(b1);
        assertFalse(buildingsController.addBuilding(b1));
    }

    @Test
    public void deleteBuildingTrueTest() {
        b1 = new Buildings();
        buildingsRepository.save(b1);
        assertTrue(buildingsController.deleteBuilding(b1.getBuildingNumber()));
    }

    @Test
    public void deleteBuildingFalseTest() {
        b1 = new Buildings();
        when(buildingsRepository.deleteBuildingByBuildingNumber(b1.getBuildingNumber()))
                .thenThrow(NullPointerException.class);
        assertFalse(buildingsController.deleteBuilding(b1.getBuildingNumber()));
    }

    @Test
    public void editBuildingFalseTest() {
        b1 = new Buildings();
        assertFalse(buildingsController.editBuilding(b1));
    }

    @Test
    public void editBuildingTrueTest() {
        b1 = new Buildings();
        b1.setBuildingNumber(1);

        buildingsRepository.save(b1);
        when(buildingsRepository.findBuildingsByBuildingNumber(1)).thenReturn(b1);

        assertTrue(buildingsController.editBuilding(b1));
    }

    @Test
    public void getBuildingByNameTest() {
        b1 = new Buildings();

        when(buildingsRepository.findBuildingsByBuildingNumber(b1.getBuildingNumber()))
                .thenReturn(b1);

        assertEquals(b1, buildingsController.getBuildingByName(b1.getBuildingNumber()));
    }

}
