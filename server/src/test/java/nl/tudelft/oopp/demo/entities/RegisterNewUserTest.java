package nl.tudelft.oopp.demo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterNewUserTest {

    private RegisterNewUser ru1;

    @BeforeEach
    public void setup() {
        ru1 = new RegisterNewUser();
        ru1.setNetid("NetId");
        ru1.setPassword("password123");
        ru1.setRole("Student");
    }

    @Test
    public void getNetIdTest() {
        assertEquals("NetId", ru1.getNetid());
    }

    @Test
    public void setNetIdTest() {
        ru1.setNetid("fezard");
        assertEquals("fezard", ru1.getNetid());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("password123", ru1.getPassword());
    }

    @Test
    public void setPasswordTest() {
        ru1.setPassword("PaSsWoRd");
        assertEquals("PaSsWoRd", ru1.getPassword());
    }

    @Test
    public void getRoleTest() {
        assertEquals("Student", ru1.getRole());
    }

    @Test
    public void setRoleTest() {
        ru1.setRole("Teacher");
        assertEquals("Teacher", ru1.getRole());
    }

}
