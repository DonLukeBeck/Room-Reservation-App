package nl.tudelft.oopp.demo.entities;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class

public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userReserving;

    private Time timeslot;

    private Date date;

    private String roomReserved;

    private int bikeReserved;

    private String dishOrdered;

    public Reservations() {
    }

    public String getUserReserving() {
        return userReserving;
    }

    public void setUserReserving(String userReserving) {
        this.userReserving = userReserving;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Time timeslot) {
        this.timeslot = timeslot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoomReserved() {
        return roomReserved;
    }

    public void setRoomReserved(String roomReserved) {
        this.roomReserved = roomReserved;
    }

    public int getBikeReserved() {
        return bikeReserved;
    }

    public void setBikeReserved(int bikeReserved) {
        this.bikeReserved = bikeReserved;
    }

    public String getDishOrdered() {
        return dishOrdered;
    }

    public void setDishOrdered(String dishOrdered) {
        this.dishOrdered = dishOrdered;
    }
}
