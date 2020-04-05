package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.repositories.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // This means that this class is a Controller
public class DishesController {

    @Autowired
    private DishesRepository dishesRepository;

    @GetMapping("/allDishes")
    public @ResponseBody
    List<Dishes> getAllDishes() {
        // This returns a JSON or XML with the dishes
        return dishesRepository.findAll();
    }

    @GetMapping("/menusByBuilding")
    public @ResponseBody
    List<Dishes> getMenusByBuilding(@RequestParam int bnr) {
        // This returns a JSON or XML with the menus
        return dishesRepository.findAllByBuildingNumber(bnr);
    }

    /**
     * Adds a dish, inserted by the admin, to the database.
     *
     * @param dish - dish to be added to the database
     * @return true if dish successfully added, false otherwise
     */
    @PostMapping("/addDish") // Map ONLY POST Requests
    public @ResponseBody
    boolean addDish(@RequestBody Dishes dish) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            if (!dishesRepository.findDishesByName(dish.getName()).getName().isEmpty()) {
                return false;
            }
            return false;
        } catch (NullPointerException e) {
            Dishes newDish = new Dishes();
            newDish.setName(dish.getName());
            newDish.setPrice(dish.getPrice());
            newDish.setVegan(dish.getVegan());
            dishesRepository.save(newDish);
            return true;
        }
    }

    /**
     * Edits a dish, inserted by the admin, in the database.
     *
     * @param dish - dish to be edited to the database
     * @return true if dish successfully edited, false otherwise
     */
    @RequestMapping("/editDish") // Map ONLY POST Requests
    public @ResponseBody
    boolean editDish(@RequestBody Dishes dish, @RequestParam String oldDishName) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            if (dishesRepository.updateExistingDish(dish.getName(),
                    dish.getPrice(),
                    dish.getVegan(),
                    oldDishName) == 1) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }


    /**
     *  Deletes a dish.
     * @param name - name of dish
     * @return true if dish successfully deleted, false otherwise.
     */
    @GetMapping("/deleteDish")
    public @ResponseBody
    boolean deleteDish(@RequestParam String name) {
        try {
            if (dishesRepository.deleteDishByName(name) != 0) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }

}


