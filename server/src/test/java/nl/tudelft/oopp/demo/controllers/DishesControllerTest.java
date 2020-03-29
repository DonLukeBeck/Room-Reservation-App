package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}
