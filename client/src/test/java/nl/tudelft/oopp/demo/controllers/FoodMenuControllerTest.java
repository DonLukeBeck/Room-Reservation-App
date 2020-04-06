package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
