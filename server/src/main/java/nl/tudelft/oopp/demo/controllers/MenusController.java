package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Menus;
import nl.tudelft.oopp.demo.repositories.MenusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class MenusController {

    @Autowired
    private MenusRepository menusRepository;

    @GetMapping("/allMenus")
    public @ResponseBody
    List<Menus> getAllMenus() {
        // This returns a JSON or XML with the menus
        return menusRepository.findAll();
    }

    /**
     * Adds a menu, inserted by the admin, to the database.
     *
     * @param menu - menu to be added to the database
     * @return true if menu successfully added, false otherwise
     */
    @PostMapping("/addMenu") // Map ONLY POST Requests
    public @ResponseBody
    boolean addMenu(@RequestBody Menus menu) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestBody means it is a parameter from the GET or POST request
        try {
            menusRepository.addMenu(menu.getBuildingNumber(),menu.getDishName());
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    /**
     * Edits a menu, inserted by the admin, in the database.
     *
     * @param menu - menu to be edited to the database
     * @return true if menu successfully edited, false otherwise
     */
    @RequestMapping("/editMenu") // Map ONLY POST Requests
    public @ResponseBody
    boolean editMenu(@RequestBody Menus menu, @RequestParam String oldDishName) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            menusRepository.deleteMenu(menu.getBuildingNumber(),oldDishName);
            menusRepository.addMenu(menu.getBuildingNumber(),menu.getDishName());
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  Deletes a menu.
     * @param buildingNumber - building id
     * @param dishName - name of dish
     * @return true if menu successfully deleted, false otherwise.
     */
    @GetMapping("/deleteMenu")
    public @ResponseBody
    boolean deleteMenu(@RequestParam int buildingNumber, String dishName) {
        try {
            if (menusRepository.deleteMenu(buildingNumber, dishName) != 0) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }


}


