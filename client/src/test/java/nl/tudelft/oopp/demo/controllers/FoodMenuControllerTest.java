package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Menus;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodMenuControllerTest {

    @Test
    public void getDishesNameNullTest() {
        assertEquals(null, FoodMenuController.getDishesName());
    }

    @Test
    public void getDishesNameTest() {
        String dish = "burger";
        FoodMenuController.setDishesName(dish);
        assertEquals(dish, FoodMenuController.getDishesName());
    }
}
