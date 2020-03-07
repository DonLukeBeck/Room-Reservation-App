package nl.tudelft.oopp.demo.entities;

public class RegisterNewUser {

    private String netid;

    private String password;

    private String role;

    public RegisterNewUser() {
    }


    public String getNetid() {
        return netid;
    }

    public void setNetid(String netid) {
        this.netid = netid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
