package nl.tudelft.oopp.demo.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Holidays;
import nl.tudelft.oopp.demo.repositories.HolidaysRepository;
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
public class HolidaysController {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @GetMapping("/allHolidays")
    public @ResponseBody
    List<Holidays> getAllHolidays() {
        // This returns a JSON or XML with the holidays
        return holidaysRepository.findAll();
    }

    /**
     * Adds a holiday, inserted by the admin, to the database.
     *
     * @param holidays - holiday to be added to the database
     * @return true if holiday successfully added, false otherwise
     */
    @PostMapping("/addHolidays") // Map ONLY POST Requests
    public @ResponseBody
    boolean addHolidays(@RequestBody Holidays holidays) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestBody means it is a parameter from the GET or POST request
        try {
            holidaysRepository.save(holidays);
            return true;
        }catch (DataIntegrityViolationException e){
            return false;
        }
    }

    /**
     * Edits a holiday, inserted by the admin, in the database.
     *
     * @param holidays - holidays to be edited in the database
     * @return true if holiday successfully edited, false otherwise
     */
    @RequestMapping("/editHolidays") // Map ONLY POST Requests
    public @ResponseBody
    boolean editHolidays(@RequestBody Holidays holidays) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            if (holidaysRepository.updateExistingHolidays(holidays.getStartDate(),
                    holidays.getEndDate(),
                    holidays.getComments(),
                    holidays.getHolidaysID()) == 1) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @GetMapping("/deleteHolidays")
    public @ResponseBody
    boolean deleteHolidays(@RequestParam int holidaysId) {
        return holidaysRepository.deleteHolidaysById(holidaysId);
    }
}


