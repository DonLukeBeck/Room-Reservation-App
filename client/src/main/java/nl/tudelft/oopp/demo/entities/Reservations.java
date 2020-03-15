package nl.tudelft.oopp.demo.entities;

import java.sql.Date;
import java.sql.Time;

public class Reservations {

    private int id;

    private String user_reserving;

    private Time timeslot;

    private Date date;

    private int building_reserved;

    private String room_reserved;

    private int bike_reserved;

    private String dish_ordered;

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
        return user_reserving;
    }

    public void setUser_reserving(String user_reserving) {
        this.user_reserving = user_reserving;
    }

    public String getRoom_reserved() {
        return room_reserved;
    }

    public void setRoom_reserved(String room_reserved) {
        this.room_reserved = room_reserved;
    }

    public int getBike_reserved() {
        return bike_reserved;
    }

    public void setBike_reserved(int bike_reserved) {
        this.bike_reserved = bike_reserved;
    }

    public String getDish_ordered() {
        return dish_ordered;
    }

    public void setDish_ordered(String dish_ordered) {
        this.dish_ordered = dish_ordered;
    }

    public int getBuilding_reserved() {
        return building_reserved;
    }

    public void setBuilding_reserved(int building_reserved) {
        this.building_reserved = building_reserved;
    }
}
