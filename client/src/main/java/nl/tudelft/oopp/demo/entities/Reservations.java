package nl.tudelft.oopp.demo.entities;

import java.sql.Date;
import java.sql.Time;

public class Reservations {

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


    /**
     *Method to get the Reservation ID.
     * @return Reservation ID
     */
    public int getId() {
        return id;
    }

    /**
     *Method to set the Reservation ID.
     * @param id Reservation ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Method to get the Timeslot of the reservation.
     * @return Reservation timeslot
     */
    public Time getTimeslot() {
        return timeslot;
    }

    /**
     *Method to set the Timeslot of the reservation.
     * @param timeslot Reservation timeslot
     */
    public void setTimeslot(Time timeslot) {
        this.timeslot = timeslot;
    }

    /**
     *Method to get the reservation Date.
     * @return Reservation Date
     */
    public Date getDate() {
        return date;
    }

    /**
     *Method ot set the reservation Date.
     * @param date Reservation Date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *Method to get the User making the reservation.
     * @return User Reserving
     */
    public String getUserReserving() {
        return userReserving;
    }

    /**
     *Method to set the User making the reservation.
     * @param userReserving User Reserving
     */
    public void setUser_reserving(String userReserving) {
        this.userReserving = userReserving;
    }

    /**
     *Method to get the room associated with the reservation.
     * @return Room Reserved
     */
    public String getRoomReserved() {
        return roomReserved;
    }

    /**
     *Method to set the room reserved by the reservation.
     * @param roomReserved Room Reserved
     */
    public void setRoomReserved(String roomReserved) {
        this.roomReserved = roomReserved;
    }

    /**
     *Method to get the bike that is associated with the reservation.
     * @return
     */
    public int getBikeReserved() {
        return bikeReserved;
    }

    /**
     *Method to set the bike associated with the Reservation.
     * @param bikeReserved Bike Reserved
     */
    public void setBikeReserved(int bikeReserved) {
        this.bikeReserved = bikeReserved;
    }

    /**
     *Method to get the Dish associated with the reservation.
     * @return Dish Ordered
     */
    public String getDishOrdered() {
        return dishOrdered;
    }

    /**
     *Method to set the Dish associated with the reservation.
     * @param dishOrdered Dish Ordered
     */
    public void setDishOrdered(String dishOrdered) {
        this.dishOrdered = dishOrdered;
    }

    /**
     *Method to get the Building associated with the reservation.
     * @return Associated Building
     */
    public int getBuilding_reserved() {
        return buildingReserved;
    }

    /**
     *Method to set the Building associated with the reservation.
     * @param buildingReserved Associated Building
     */
    public void setBuildingReserved(int buildingReserved) {
        this.buildingReserved = buildingReserved;
    }
}
