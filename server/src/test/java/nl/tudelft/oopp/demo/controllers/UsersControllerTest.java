package nl.tudelft.oopp.demo.controllers;

import static nl.tudelft.oopp.demo.controllers.UsersController.hashPassword;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.LoginUser;
import nl.tudelft.oopp.demo.entities.RegisterNewUser;
import nl.tudelft.oopp.demo.entities.Users;
import nl.tudelft.oopp.demo.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersController usersController;

    private Users u1;
    private Users u2;
    private LoginUser lu1;
    private RegisterNewUser ru1;

    @Test
    public void getAllUsersTest() {
        u1 = new Users();
        u2 = new Users();

        usersRepository.save(u1);
        usersRepository.save(u2);

        List<Users> repo = new ArrayList<>(List.of(u1,u2));
        when(usersRepository.findAll()).thenReturn(repo);

        List<Users> actual = usersController.getAllUsers();

        assertEquals(repo, actual);

    }

    @Test
    public void registerFalseTest() throws NoSuchAlgorithmException {
        u1 = new Users();
        u1.setNetid("NetID");
        ru1 = new RegisterNewUser();
        ru1.setNetid("NetID");

        when(usersRepository.findUserByNetid(ru1.getNetid())).thenReturn(u1);

        assertFalse(usersController.register(ru1));
    }

    @Test
    public void registerFalseCase2Test() throws NoSuchAlgorithmException {
        u1 = new Users();
        u1.setNetid("");
        ru1 = new RegisterNewUser();
        ru1.setNetid("NetID");

        when(usersRepository.findUserByNetid(ru1.getNetid())).thenReturn(u1);

        assertFalse(usersController.register(ru1));
    }

    @Test
    public void registerTrueTest() throws NoSuchAlgorithmException {
        ru1 = new RegisterNewUser();
        ru1.setNetid("NetID");
        ru1.setRole("Role");
        ru1.setPassword("Password");

        when(usersRepository.findUserByNetid(ru1.getNetid())).thenThrow(NullPointerException.class);
        assertTrue(usersController.register(ru1));
    }

    @Test
    public void loginTest() throws NoSuchAlgorithmException {
        u1 = new Users();
        u1.setPassword("Password");
        u1.setNetid("NetID");
        u1.setRole("Role");

        lu1 = new LoginUser();
        lu1.setNetid("NetID");
        lu1.setPassword("Password");

        when(usersRepository.findUserByNetidAndPass(lu1.getNetid(), hashPassword(lu1.getPassword()))).thenReturn(u1);
        assertEquals("{\"netid\":\"NetID\",\"role\":\"Role\"}", usersController.login(lu1));
    }

    @Test
    public void loginOtherCaseTest() throws NoSuchAlgorithmException {
        u1 = new Users();
        u1.setPassword("Password");
        u1.setNetid("NetID");
        u1.setRole("Role");

        lu1 = new LoginUser();
        lu1.setNetid("NetID");
        lu1.setPassword("Password");

        when(usersRepository.findUserByNetidAndPass(lu1.getNetid(),
                hashPassword(lu1.getPassword()))).thenThrow(NullPointerException.class);
        assertEquals("", usersController.login(lu1));
    }

}
