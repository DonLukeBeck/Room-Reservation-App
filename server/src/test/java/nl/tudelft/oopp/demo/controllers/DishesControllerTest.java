package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.repositories.DishesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.Null;

@ExtendWith(MockitoExtension.class)
public class DishesControllerTest {

    @Mock
    DishesRepository dishesRepository;

    @InjectMocks
    DishesController dishesController;

    private Dishes d1;
    private Dishes d2;

    @Test
    public void getAllDishesTest() {
        d1 = new Dishes();
        d2 = new Dishes();

        dishesRepository.save(d1);
        dishesRepository.save(d2);

        List<Dishes> repo = new ArrayList<>(List.of(d1,d2));

        when(dishesRepository.findAll()).thenReturn(repo);
        List<Dishes> actual = dishesController.getAllDishes();

        assertEquals(repo, actual);
    }

    @Test
    public void getMenusByBuildingTest() {
        d1 = new Dishes();
        dishesRepository.save(d1);

        List<Dishes> repo = new ArrayList<>(List.of(d1));

        when(dishesRepository.findAllByBuildingNumber(1)).thenReturn(repo);

        List<Dishes> actual = dishesController.getMenusByBuilding(1);
        assertEquals(repo, actual);
    }

    @Test
    public void addDishTrueTest() {
        d1 = new Dishes();
        assertTrue(dishesController.addDish(d1));
    }

    @Test
    public void addDishFalseTest() {
        d1 = new Dishes();
        d1.setName("Dish1");

        dishesRepository.save(d1);

        when(dishesRepository.findDishesByName(d1.getName())).thenReturn(d1);

        assertFalse(dishesController.addDish(d1));
    }

    @Test
    public void addDishFalseCase2Test() {
        d1 = new Dishes();
        d1.setName("");

        when(dishesRepository.findDishesByName(d1.getName())).thenReturn(d1);

        assertFalse(dishesController.addDish(d1));
    }

    @Test
    public void deleteDishTest() {
        d1 = new Dishes();
       assertFalse(dishesController.deleteDish(d1.getName()));
    }

    @Test
    public void editDishFalseTest() {
        d1 = new Dishes();
        assertFalse(dishesController.editDish(d1, "dish"));
    }

    @Test
    public void editDishFalseCase2Test() {
        d1 = new Dishes();

        when(dishesRepository.updateExistingDish(d1.getName(), d1.getPrice(), d1.getVegan(), d1.getName())).thenThrow(NullPointerException.class);

        assertFalse(dishesController.editDish(d1, d1.getName()));
    }

    @Test
    public void editDishTrueTest() {
        d1 = new Dishes();

        dishesRepository.save(d1);

        when(dishesRepository.updateExistingDish(d1.getName(), d1.getPrice(), d1.getVegan(), d1.getName())).thenReturn(1);
        assertTrue(dishesController.editDish(d1, d1.getName()));

    }

}
