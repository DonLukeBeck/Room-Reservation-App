package nl.tudelft.oopp.demo.controllers;

import java.util.List;

import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.repositories.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class DishesController {

    @Autowired
    private DishesRepository dishesRepository;

    @GetMapping("/allDishes")
    public @ResponseBody
    List<Dishes> getAllDishes() {
        // This returns a JSON or XML with the buildings
        return dishesRepository.findAll();
    }

}


