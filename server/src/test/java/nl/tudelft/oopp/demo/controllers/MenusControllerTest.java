package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Menus;
import nl.tudelft.oopp.demo.repositories.MenusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
public class MenusControllerTest {

    @Mock
    private MenusRepository menusRepository;

    @InjectMocks
    private MenusController menusController;

    private Menus m1;
    private Menus m2;

    @Test
    public void getAllMenusTest() {
        m1 = new Menus();
        m2 = new Menus();

        menusRepository.save(m1);
        menusRepository.save(m2);

        List<Menus> repo = new ArrayList<>(List.of(m1,m2));
        when(menusRepository.findAll()).thenReturn(repo);

        List<Menus> actual = menusController.getAllMenus();

        assertEquals(repo, actual);

    }

    @Test
    public void addMenuFalseTest() {
        m1 = new Menus();
        when(menusRepository.addMenu(m1.getBuildingNumber(),
                m1.getDishName())).thenThrow(DataIntegrityViolationException.class);
        assertFalse(menusController.addMenu(m1));
    }


    @Test
    public void addMenuTrueTest() {
        m1 = new Menus();
        assertTrue(menusController.addMenu(m1));
    }

    @Test
    public void editMenuTrueTest() {
        m1 = new Menus();
        assertTrue(menusController.editMenu(m1, m1.getDishName()));
    }

    @Test
    public void editMenuFalseTest() {
        m1 = new Menus();
        when(menusRepository.addMenu(m1.getBuildingNumber(),
                m1.getDishName())).thenThrow(NullPointerException.class);
        assertFalse(menusController.editMenu(m1, m1.getDishName()));
    }

    @Test
    public void deleteMenuTrueTest() {
        m1 = new Menus();

        when(menusRepository.deleteMenu(m1.getBuildingNumber(), m1.getDishName())).thenReturn(1);
        assertTrue(menusController.deleteMenu(m1.getBuildingNumber(), m1.getDishName()));
    }

    @Test
    public void deleteMenuFalseTest() {
        m1 = new Menus();

        when(menusRepository.deleteMenu(m1.getBuildingNumber(), m1.getDishName())).thenReturn(0);
        assertFalse(menusController.deleteMenu(m1.getBuildingNumber(), m1.getDishName()));
    }

    @Test
    public void deleteMenuFalseCase2Test() {
        m1 = new Menus();
        when(menusRepository.deleteMenu(m1.getBuildingNumber(),
                m1.getDishName())).thenThrow(NullPointerException.class);
        assertFalse(menusController.deleteMenu(m1.getBuildingNumber(), m1.getDishName()));
    }

}
