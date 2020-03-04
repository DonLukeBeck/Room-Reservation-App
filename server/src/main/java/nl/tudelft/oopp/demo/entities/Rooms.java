package nl.tudelft.oopp.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class Rooms {
    @Id
    private String room_id;

    private int capacity;

    private String type;

    private int associatedBuilding;

    public Rooms() {
    }


    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
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

    public int getAssociatedBuilding() {
        return associatedBuilding;
    }

    public void setAssociatedBuilding(int associatedBuilding) {
        this.associatedBuilding = associatedBuilding;
    }
}
