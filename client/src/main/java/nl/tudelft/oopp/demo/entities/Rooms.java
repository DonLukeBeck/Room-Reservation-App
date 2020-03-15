package nl.tudelft.oopp.demo.entities;


public class Rooms {

    private String room_id;

    private int capacity;

    private String type;

    private int associatedBuilding;

    public Rooms() {
    }

    /**
     *Method to get the Room ID
     * @return Room ID
     */
    public String getRoom_id() {
        return room_id;
    }

    /**
     *Method to set the Room ID
     * @param room_id Room ID
     */
    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    /**
     *Method to get the capacity of a room
     * @return Room Capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     *Method to set the capacity of a room
     * @param capacity Room Capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     *Method to get the type of room (Lecture Hall, Exam Hall etc.)
     * @return Room Type
     */
    public String getType() {
        return type;
    }

    /**
     *Method to set the type of room
     * @param type Room Type
     */
    public void setType(String type){
        this.type = type;

    }

    /**
     *Method to get the building the room is a part of
     * @return Room Building
     */
    public int getAssociatedBuilding() {
        return associatedBuilding;
    }

    /**
     *Method to set the building the room is a part of
     * @param associatedBuilding Room Building
     */
    public void setAssociatedBuilding(int associatedBuilding) {
        this.associatedBuilding = associatedBuilding;
    }
}
