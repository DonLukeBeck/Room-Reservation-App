package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Holidays;
import nl.tudelft.oopp.demo.repositories.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class HolidaysController {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @GetMapping("/allHolidays")
    public @ResponseBody
    List<Holidays> getAllHolidays() {
        // This returns a JSON or XML with the holidays
        return holidaysRepository.findAll();
    }

}


