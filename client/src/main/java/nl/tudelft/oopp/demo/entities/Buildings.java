package nl.tudelft.oopp.demo.entities;

import java.sql.Time;

public class Buildings {

    private int buildingNumber;

    private String name;

    private Time openingHours;

    private Time closingHours;

    private int numberOfRooms;

    private int numberOfBikes;

    private String url;

    /**
     * Buildings Constructor
     * @param buildingNumber Unique number of the building
     * @param name Building Name
     * @param openingHours Opening Hour for the building
     * @param closingHours Closing Hour of the building
     * @param numberOfRooms Number of rooms in the building
     * @param numberOfBikes Number of bikes associated with the building
     * @param url URL of the building picture
     */
    public Buildings(int buildingNumber, String name, Time openingHours, Time closingHours, int numberOfRooms, int numberOfBikes, String url) {
        this.buildingNumber = buildingNumber;
        this.name = name;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBikes = numberOfBikes;
        this.url = url;
    }


    /**
     * Method to get Building Number.
     *
     * @return Building Number
     */
    public int getBuilding_number() {
        return buildingNumber;
    }

    /**
     * Method to set Building Number.
     *
     * @param buildingNumber Number to be set to the building
     */
    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    /**
     * Method to get Name of a Building.
     *
     * @return Building Name
     */
    public String getName() {
        return name;
    }

    /**
     * Mehtod to set Building Name.
     *
     * @param name Buidling Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get Opening Hours of a Building.
     *
     * @return Building Opening Hours
     */
    public Time getOpeningHours() {
        return openingHours;
    }

    /**
     * Method to set Building Opening Hours.
     *
     * @param openingHours Building Opening Hours
     */
    public void setOpeningHours(Time openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * Method to get Building Closing Hours.
     *
     * @return Building Closing Hours
     */
    public Time getClosingHours() {
        return closingHours;
    }

    /**
     * Method to set Building Closing Hours.
     *
     * @param closingHours Closing Hours of a Building
     */
    public void setClosingHours(Time closingHours) {
        this.closingHours = closingHours;
    }

    /**
     * Method to get the number of rooms in a Building.
     *
     * @return Number of rooms in Building
     */
    public int getNumberOf_Rooms() {
        return numberOfRooms;
    }

    /**
     * Method to set Number of Rooms in a Building.
     *
     * @param numberOfRooms Number of rooms in a building
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Method to get the number of bikes associated with a Building.
     *
     * @return Number of Bikes associated with the Building
     */
    public int getNumber_of_bikes() {
        return numberOfBikes;
    }

    /**
     * Method to set the number of bikes asssociated with a Building.
     *
     * @param numberOfBikes Number of bikes associated to the building
     */
    public void setNumberOfBikes(int numberOfBikes) {
        this.numberOfBikes = numberOfBikes;
    }

    /**
     * Method to get the URL of the Building Image.
     *
     * @return URL of Building Image
     */
    public String getUrl() {
        return url;
    }

    /**
     * Method to set the URL of the Building Image.
     *
     * @param url URL of building image
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
