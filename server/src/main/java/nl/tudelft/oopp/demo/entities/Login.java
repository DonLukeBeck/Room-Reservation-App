package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")

public class Login {
    @Id
    @Column(name = "Net_ID")
    private String net_ID;

    @Column(name = "Password")
    private String password;

    @Column(name = "Role")
    private String role;

    private String status="user is logged in";

    public Login() {
    }

    /**
     * Create a new Login instance.
     *
     * @param username= User username.
     * @param password User password.
     */
    public Login(String username, String password) {
        this.net_ID = username;
        this.password = password;
    }

    public String getStatus() {
        return status;
    }
}
