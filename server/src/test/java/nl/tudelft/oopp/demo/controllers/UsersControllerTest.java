package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.LoginUser;
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

    /*
    @Test
    public void registerUserTest() {

    }

     @Test
    public void loginUserTest() throws NoSuchAlgorithmException {

     }
     */

}
