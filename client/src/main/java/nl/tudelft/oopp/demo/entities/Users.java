package nl.tudelft.oopp.demo.entities;


public class Users {

    private String netid = "";

    private String role = "";

    public static Users user;


    /**
     * Constructor for Users.
     * @param netid Unique user netID
     * @param role User Role
     */
    public Users(String netid, String role) {
        this.netid = netid;
        this.role = role;
    }

    public Users() {
    }

    /**
     * Method to get the NetID of a User.
     *
     * @return User NetID
     */
    public String getNetid() {
        return netid;
    }

    /**
     * Method to set the NetID of a User.
     *
     * @param netid User NetID
     */
    public void setNetid(String netid) {
        this.netid = netid;
    }

    /**
     * Method to get the role of the User.
     *
     * @return User Role
     */
    public String getRole() {
        return role;
    }

    /**
     * Method to set the Role of a User.
     *
     * @param role User Role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
