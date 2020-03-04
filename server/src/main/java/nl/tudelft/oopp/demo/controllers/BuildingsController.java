package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.repositories.BuildingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class BuildingsController {

    @Autowired
    private BuildingsRepository buildingsRepository;

    @GetMapping("/allBuildings")
    public @ResponseBody
    Iterable<Buildings> getAllBuildings() {
        // This returns a JSON or XML with the buildings
        return buildingsRepository.findAll();
    }

}


