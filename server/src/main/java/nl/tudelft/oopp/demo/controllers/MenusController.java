package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Menus;
import nl.tudelft.oopp.demo.repositories.MenusRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            if (!menusRepository.findMenu(menu
                    .getBuildingNumber(), menu
                    .getDishName())
                    .getDishName().isEmpty()) {
                return false;
            }
            return false;
        } catch (NullPointerException e) {
            menusRepository.save(menu);
            return true;
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
            if (menusRepository.updateExistingMenu(menu.getBuildingNumber(),
                    menu.getDishName(),
                    oldDishName) == 1) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @GetMapping("/deleteMenu")
    public @ResponseBody
    boolean deleteMenu(@RequestParam int buildinNumber, String dishName) {
        return menusRepository.deleteMenu(buildinNumber, dishName);
    }


}


