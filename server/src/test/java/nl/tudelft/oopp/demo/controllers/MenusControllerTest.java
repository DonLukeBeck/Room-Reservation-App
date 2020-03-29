package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Menus;
import nl.tudelft.oopp.demo.repositories.MenusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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


}
