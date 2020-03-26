package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginUserTest {

    private LoginUser lu1;

    @BeforeEach
    public void setup() {
        lu1 = new LoginUser();
        lu1.setNetid("user");
        lu1.setPassword("password");
    }

    @Test
    public void getNetIdTest() {
        assertEquals("user", lu1.getNetid());
    }

    @Test
    public void setNetIdTest() {
        lu1.setNetid("admin");
        assertEquals("admin", lu1.getNetid());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("password", lu1.getPassword());
    }

    @Test
    public void setPasswordTest() {
        lu1.setPassword("abcdefg");
        assertEquals("abcdefg", lu1.getPassword());
    }

}
