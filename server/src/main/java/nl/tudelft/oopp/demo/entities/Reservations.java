package nl.tudelft.oopp.demo.entities;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class

public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userReserving;

    private Time timeslot;

    private Date date;


    private int buildingReserved;

    private String roomReserved;

    private int bikeReserved;

    private String dishOrdered;

    public Reservations() {
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


    public String getUser_reserving() {
        return userReserving;
    }

    public void setUser_reserving(String userReserving) {
        this.userReserving = userReserving;
    }

    public String getRoom_reserved() {
        return roomReserved;
    }

    public void setRoom_reserved(String roomReserved) {
        this.roomReserved = roomReserved;
    }

    public int getBike_reserved() {
        return bikeReserved;
    }

    public void setBike_reserved(int bikeReserved) {
        this.bikeReserved = bikeReserved;
    }

    public String getDish_ordered() {
        return dishOrdered;
    }

    public void setDish_ordered(String dishOrdered) {
        this.dishOrdered = dishOrdered;
    }

    public int getBuilding_reserved() {
        return buildingReserved;
    }

    public void setBuilding_reserved(int buildingReserved) {
        this.buildingReserved = buildingReserved;
    }
}
