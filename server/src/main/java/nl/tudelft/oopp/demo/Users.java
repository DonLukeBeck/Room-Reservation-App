package nl.tudelft.oopp.demo;

        import javax.persistence.Entity;
        import javax.persistence.Id;
        import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
//@Table(name = "Users")
public class Users {
    @Id
    private String netID;

    private String password;

    private String role;

    public Users() {
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
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
