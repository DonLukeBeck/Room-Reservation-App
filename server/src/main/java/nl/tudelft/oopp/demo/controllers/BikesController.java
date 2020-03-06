package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Bikes;
import nl.tudelft.oopp.demo.repositories.BikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class BikesController {

    @Autowired
    private BikesRepository bikesRepository;

    @GetMapping("/allBikes")
    public @ResponseBody
    List<Bikes> getAllBikes() {
        // This returns a JSON or XML with the buildings
        return bikesRepository.findAll();
    }

}


