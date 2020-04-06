package nl.tudelft.oopp.demo.entities;

import java.sql.Date;
import java.sql.Time;

public class UserEvent implements Comparable<UserEvent> {

    private int id;
    private Date date;
    private Time time;
    private String user;
    private String description;

    /**
     * Constructor for a user event.
     */
    public UserEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(UserEvent o) {
        return (int) Math.signum(date.getTime() - o.date.getTime());
    }

    /**
     * Method to convert user even into a nice string.
     * @return Nice string
     */
    public String getNiceString() {
        String res = "";
        res += time + ": ";
        res += description;
        return res;
    }
}
