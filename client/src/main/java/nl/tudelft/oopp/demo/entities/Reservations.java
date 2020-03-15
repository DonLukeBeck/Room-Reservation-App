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


    /**
     *Method to get the Reservation ID
     * @return Reservation ID
     */
    public int getId() {
        return id;
    }

    /**
     *Method to set the Reservation ID
     * @param id Reservation ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Method to get the Timeslot of the reservation
     * @return Reservation timeslot
     */
    public Time getTimeslot() {
        return timeslot;
    }

    /**
     *Method to set the Timeslot of the reservation
     * @param timeslot Reservation timeslot
     */
    public void setTimeslot(Time timeslot) {
        this.timeslot = timeslot;
    }

    /**
     *Method to get the reservation Date
     * @return Reservation Date
     */
    public Date getDate() {
        return date;
    }

    /**
     *Method ot set the reservation Date
     * @param date Reservation Date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *Method to get the User making the reservation
     * @return User Reserving
     */
    public String getUser_reserving() {
        return user_reserving;
    }

    /**
     *Method to set the User making the reservation
     * @param user_reserving User Reserving
     */
    public void setUser_reserving(String user_reserving) {
        this.user_reserving = user_reserving;
    }

    /**
     *Method to get the room associated with the reservation
     * @return Room Reserved
     */
    public String getRoom_reserved() {
        return room_reserved;
    }

    /**
     *Method to set the room reserved by the reservation
     * @param room_reserved Room Reserved
     */
    public void setRoom_reserved(String room_reserved) {
        this.room_reserved = room_reserved;
    }

    /**
     *Method to get the bike that is associated with the reservation
     * @return
     */
    public int getBike_reserved() {
        return bike_reserved;
    }

    /**
     *Method to set the bike associated with the Reservation
     * @param bike_reserved Bike Reserved
     */
    public void setBike_reserved(int bike_reserved) {
        this.bike_reserved = bike_reserved;
    }

    /**
     *Method to get the Dish associated with the reservation
     * @return Dish Ordered
     */
    public String getDish_ordered() {
        return dish_ordered;
    }

    /**
     *Method to set the Dish associated with the reservation
     * @param dish_ordered Dish Ordered
     */
    public void setDish_ordered(String dish_ordered) {
        this.dish_ordered = dish_ordered;
    }

    /**
     *Method to get the Building associated with the reservation
     * @return Associated Building
     */
    public int getBuilding_reserved() {
        return building_reserved;
    }

    /**
     *Method to set the Building associated with the reservation
     * @param building_reserved Associated Building
     */
    public void setBuilding_reserved(int building_reserved) {
        this.building_reserved = building_reserved;
    }
}
