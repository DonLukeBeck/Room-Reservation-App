package nl.tudelft.oopp.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class Rooms {
    @Id
    private String roomId;

    private int capacity;

    private String type;

    private int associatedBuilding;

    public Rooms() {
    }


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAssociatedBuilding() { return associatedBuilding; }

    public void setAssociatedBuilding(int associatedBuilding) { this.associatedBuilding = associatedBuilding; }
}
