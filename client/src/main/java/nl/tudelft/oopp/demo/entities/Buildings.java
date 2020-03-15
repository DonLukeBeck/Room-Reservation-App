package nl.tudelft.oopp.demo.entities;

import java.sql.Time;

public class Buildings {

    private int building_number;

    private String name;

    private Time opening_hours;

    private Time closing_hours;

    private int number_of_rooms;

    private int number_of_bikes;

    private String url;

    public Buildings() {
    }

    /**
     * Method to get Building Number
     * @return Building Number
     */
    public int getBuilding_number() {
        return building_number;
    }

    /**
     * Method to set Building Number
     * @param building_number Number to be set to the building
     */
    public void setBuilding_number(int building_number) {
        this.building_number = building_number;
    }

    /**
     * Method to get Name of a Building
     * @return Building Name
     */
    public String getName() {
        return name;
    }

    /**
     * Mehtod to set Building Name
     * @param name Buidling Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get Opening Hours of a Building
     * @return Building Opening Hours
     */
    public Time getOpening_hours() {
        return opening_hours;
    }

    /**
     * Method to set Building Opening Hours
     * @param opening_hours Building Opening Hours
     */
    public void setOpening_hours(Time opening_hours) {
        this.opening_hours = opening_hours;
    }

    /**
     * Method to get Building Closing Hours
     * @return Building Closing Hours
     */
    public Time getClosing_hours() {
        return closing_hours;
    }

    /**
     * Method to set Building Closing Hours
     * @param closing_hours Closing Hours of a Building
     */
    public void setClosing_hours(Time closing_hours) {
        this.closing_hours = closing_hours;
    }

    /**
     *Method to get the number of rooms in a Building
     * @return Number of rooms in Building
     */
    public int getNumber_of_rooms() {
        return number_of_rooms;
    }

    /**
     *Method to set Number of Rooms in a Building
     * @param number_of_rooms Number of rooms in a building
     */
    public void setNumber_of_rooms(int number_of_rooms) {
        this.number_of_rooms = number_of_rooms;
    }

    /**
     *Method to get the number of bikes associated with a Building
     * @return Number of Bikes associated with the Building
     */
    public int getNumber_of_bikes() {
        return number_of_bikes;
    }

    /**
     *Method to set the number of bikes asssociated with a Building
     * @param number_of_bikes Number of bikes associated to the building
     */
    public void setNumber_of_bikes(int number_of_bikes) {
        this.number_of_bikes = number_of_bikes;
    }

    /**
     *Method to get the URL of the Building Image
     * @return URL of Building Image
     */
    public String getUrl() {
        return url;
    }

    /**
     *Method to set the URL of the Building Image
     * @param url URL of building image
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
