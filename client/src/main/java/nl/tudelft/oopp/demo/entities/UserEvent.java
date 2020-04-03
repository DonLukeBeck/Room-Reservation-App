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
     * @param user User having the event
     * @param time Time of event
     * @param date Date of the event
     * @param description Description of the event
     */
    public UserEvent(String user, Time time, Date date, String description) {
        this.user = user;
        this.time = time;
        this.date = date;
        this.description = description;
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
}