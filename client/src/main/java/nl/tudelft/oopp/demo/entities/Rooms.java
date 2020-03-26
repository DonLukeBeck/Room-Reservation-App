package nl.tudelft.oopp.demo.entities;


public class Rooms {

    private String roomId;

    private int capacity;

    private String type;

    private int associatedBuilding;

    /**
     * Rooms Constructor.
     * @param roomId Unique Room ID
     * @param capacity Room capacity
     * @param type Type of Room
     * @param associatedBuilding Building in which room is located
     */
    public Rooms(String roomId, int capacity, String type, int associatedBuilding) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.type = type;
        this.associatedBuilding = associatedBuilding;
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
     * Method to get the capacity of a room.
     *
     * @return Room Capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Method to set the capacity of a room.
     *
     * @param capacity Room Capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
}
