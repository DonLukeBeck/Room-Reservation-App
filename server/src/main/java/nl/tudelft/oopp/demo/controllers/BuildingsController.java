package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import javax.validation.constraints.Null;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.repositories.BuildingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * Adds a building, inserted by the admin, in the database.
     *
     * @param building - building to be inserted in database
     * @return true if it's added, false if it's already in the database or it has an invalid id
     */
    @PostMapping("/addBuilding") // Map ONLY POST Requests
    public @ResponseBody
    boolean addBuilding(@RequestBody Buildings building) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            if (buildingsRepository.findBuildingsByBuildingNumber(building.getBuildingNumber())
                    .getBuildingNumber() > 0) {
                return false;
            }
            return false;
        } catch (NullPointerException e) {
            Buildings newBuilding = new Buildings();
            newBuilding.setBuildingNumber(building.getBuildingNumber());
            newBuilding.setClosingHours(building.getClosingHours());
            newBuilding.setName(building.getName());
            newBuilding.setOpeningHours(building.getOpeningHours());
            newBuilding.setNumberOfBikes(building.getNumberOfBikes());
            newBuilding.setNumberOfRooms(building.getNumberOfRooms());
            newBuilding.setUrl(building.getUrl());
            buildingsRepository.save(newBuilding);
            return true;
        }
    }

    /**
     * Edits a building from the database.
     *
     * @param building - building to be edited in database
     * @return true if it's edited, false if it has an invalid id
     */
    @PostMapping("/editBuilding") // Map ONLY POST Requests
    public @ResponseBody
    boolean editBuilding(@RequestBody Buildings building) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            Buildings existingBuilding = buildingsRepository
                    .findBuildingsByBuildingNumber(building
                            .getBuildingNumber());
            existingBuilding.setName(building.getName());
            existingBuilding.setOpeningHours(building.getOpeningHours());
            existingBuilding.setClosingHours(building.getClosingHours());
            existingBuilding.setNumberOfBikes(building.getNumberOfBikes());
            existingBuilding.setUrl(building.getUrl());
            buildingsRepository.save(existingBuilding);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Deletes a building from the database.
     *
     * @param buildingNumber - building to be deleted from database
     * @return true if it's deleted, false if it has an invalid id
     */
    @GetMapping("/deleteBuilding")
    public @ResponseBody
    boolean deleteBuilding(@RequestParam int buildingNumber) {
        try {
            buildingsRepository.deleteBuildingByBuildingNumber(buildingNumber);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}


