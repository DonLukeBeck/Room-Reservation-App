package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Menus;
import nl.tudelft.oopp.demo.repositories.MenusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

}


