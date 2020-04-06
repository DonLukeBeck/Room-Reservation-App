package nl.tudelft.oopp.demo.entities;


public class Rooms {

    private String roomId;

    private String type;

    private int chairs;

    private int whiteboards;

    private int tables;

    private int computers;

    private int associatedBuilding;

    public Rooms() {
    }

    /**
     * Method to get the Room ID.
     *
     * @return Room ID
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Method to set the Room ID.
     *
     * @param roomId Room ID
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * Method to get the chairs of a room.
     *
     * @return Room Capacity
     */
    public int getChairs() {
        return chairs;
    }

    /**
     * Method to set the chairs of a room.
     *
     * @param chairs Room Capacity
     */
    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    /**
     * Method to get the type of room (Lecture Hall, Exam Hall etc).
     *
     * @return Room Type
     */
    public String getType() {
        return type;
    }

    /**
     * Method to set the type of room.
     *
     * @param type Room Type
     */
    public void setType(String type) {
        this.type = type;

    }

    /**
     * Method to get the building the room is a part of.
     *
     * @return Room Building
     */
    public int getAssociatedBuilding() {
        return associatedBuilding;
    }

    /**
     * Method to set the building the room is a part of.
     *
     * @param associatedBuilding Room Building
     */
    public void setAssociatedBuilding(int associatedBuilding) {
        this.associatedBuilding = associatedBuilding;
    }

    /**
     * Method to get the number of whiteboards.
     *
     * @return number of whiteboards
     */
    public int getWhiteboards() {
        return whiteboards;
    }

    /**
     * Method to set the number of whiteboards.
     *
     * @param whiteboards number of whiteboards
     */
    public void setWhiteboards(int whiteboards) {
        this.whiteboards = whiteboards;
    }

    /**
     * Method to get the number of tables.
     *
     * @return number of tables
     */
    public int getTables() {
        return tables;
    }

    /**
     * Method to set the number of tables.
     *
     * @param tables number of tables
     */
    public void setTables(int tables) {
        this.tables = tables;
    }

    /**
     * Method to get the number of computers.
     *
     * @return number of computers
     */
    public int getComputers() {
        return computers;
    }

    /**
     * Method to set the number of computers.
     *
     * @param computers number of computers
     */
    public void setComputers(int computers) {
        this.computers = computers;
    }
}
