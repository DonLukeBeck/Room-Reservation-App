package nl.tudelft.oopp.demo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangePasswordTest {

    private ChangePassword cp;

    @BeforeEach
    public void setup() {
        cp = new ChangePassword();
        cp.setNetId("NetID");
        cp.setNewPassword("New Password");
        cp.setOldPassword("Old Password");
    }

    @Test
    public void getNetIdTest() {
        assertEquals("NetID", cp.getNetId());
    }

    @Test
    public void setNetIdTest() {
        cp.setNetId("test");
        assertEquals("test", cp.getNetId());
    }

    @Test
    public void getNewPasswordTest() {
        assertEquals("New Password", cp.getNewPassword());
    }

    @Test
    public void setNewPasswordTest() {
        cp.setNewPassword("test");
        assertEquals("test", cp.getNewPassword());
    }

    @Test
    public void getOldPasswordTest() {
        assertEquals("Old Password", cp.getOldPassword());
    }

    @Test
    public void setOldPasswordTest() {
        cp.setOldPassword("test");
        assertEquals("test", cp.getOldPassword());
    }

}
