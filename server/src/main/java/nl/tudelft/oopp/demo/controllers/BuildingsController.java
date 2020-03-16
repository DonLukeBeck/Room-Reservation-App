package nl.tudelft.oopp.demo.controllers;

import java.util.List;

import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.repositories.BuildingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class BuildingsController {

    @Autowired
    private BuildingsRepository buildingsRepository;

    @GetMapping("/allBuildings")
    public @ResponseBody
    List<Buildings> getAllBuildings() {
        // This returns a JSON or XML with the buildings
        return buildingsRepository.findAll();
    }

    @PostMapping("/addBuilding") // Map ONLY POST Requests
    public @ResponseBody
    boolean addBuilding(@RequestBody Buildings building) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            if (buildingsRepository.findBuildingsByBuildingNumber(building.getBuilding_number()).getBuilding_number() > 0) {
                return false;
            }
            return false;
        } catch (NullPointerException e) {
            Buildings newBuilding = new Buildings();
            newBuilding.setBuilding_number(building.getBuilding_number());
            newBuilding.setClosing_hours(building.getClosing_hours());
            newBuilding.setName(building.getName());
            newBuilding.setOpening_hours(building.getOpening_hours());
            newBuilding.setNumber_of_bikes(building.getNumber_of_bikes());
            newBuilding.setNumber_of_rooms(building.getNumber_of_rooms());
            newBuilding.setUrl(building.getUrl());
            buildingsRepository.save(newBuilding);
            return true;
        }

    }

}


