package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
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
        m1.setDishName("m1");
        when(menusRepository.findMenu(m1.getBuildingNumber(), m1.getDishName())).thenReturn(m1);
        assertFalse(menusController.addMenu(m1));
    }

    @Test
    public void addMenuFalseCase2Test() {
        m1 = new Menus();
        m1.setDishName("");
        when(menusRepository.findMenu(m1.getBuildingNumber(), m1.getDishName())).thenReturn(m1);
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

        when(menusRepository.updateExistingMenu(m1.getBuildingNumber(), m1.getDishName(), "d1")).thenReturn(1);
        assertTrue(menusController.editMenu(m1, "d1"));
    }

    @Test
    public void editMenuFalseTest() {
        m1 = new Menus();

        when(menusRepository.updateExistingMenu(m1.getBuildingNumber(), m1.getDishName(), "d1")).thenReturn(2);
        assertFalse(menusController.editMenu(m1, "d1"));
    }

    @Test
    public void editMenuFalseCase2Test() {
        m1 = new Menus();

        when(menusRepository.updateExistingMenu(m1.getBuildingNumber(), m1.getDishName(), "d1")).thenThrow(NullPointerException.class);
        assertFalse(menusController.editMenu(m1, "d1"));
    }

    @Test
    public void deleteMenuTest() {
        m1 = new Menus();

        when(menusRepository.deleteMenu(m1.getBuildingNumber(), m1.getDishName())).thenReturn(true);
        assertTrue(menusController.deleteMenu(m1.getBuildingNumber(), m1.getDishName()));
    }

}
