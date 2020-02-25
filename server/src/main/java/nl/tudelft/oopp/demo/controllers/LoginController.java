package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Login;
import nl.tudelft.oopp.demo.entities.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class LoginController {
    /**
     * GET Endpoint to retrieve a random quote.
     *
     * @return randomly selected {@link Login}.
     */
    @GetMapping("login")
    @ResponseBody
    public Login loginUser(@RequestParam(name ="user") String user,@RequestParam(name ="pass") String pass) {
        Login credentials = new Login(user, pass);
        return credentials;
    }

}

