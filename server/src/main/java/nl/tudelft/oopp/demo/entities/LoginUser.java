package nl.tudelft.oopp.demo.entities;

public class LoginUser {
    //class for login page
    private String netid;

    private String password;

    public LoginUser() {
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
}
