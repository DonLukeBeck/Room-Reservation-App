package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsersTest {

    private Users u1;

    @BeforeEach
    public void setup() {
        u1 = new Users();
        u1.setNetid("fezard");
        u1.setRole("Student");
        u1.setPassword("Password");
    }

    @Test
    public void constructorTest() {
        assertNotNull(u1);
    }

    @Test
    public void getNetIdTest() {
        assertEquals("fezard", u1.getNetid());
    }

    @Test
    public void setNetIdTest() {
        u1.setNetid("testId");
        assertEquals("testId", u1.getNetid());
    }

    @Test
    public void getRoleTest() {
        assertEquals("Student", u1.getRole());
    }

    @Test
    public void setRoleTest() {
        u1.setRole("Teacher");
        assertEquals("Teacher", u1.getRole());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("Password", u1.getPassword());
    }

    @Test
    public void setPasswordTest() {
        u1.setPassword("123");
        assertEquals("123", u1.getPassword());
    }


}

