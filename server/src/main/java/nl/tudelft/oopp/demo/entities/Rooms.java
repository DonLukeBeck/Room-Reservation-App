package nl.tudelft.oopp.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class Rooms {
    @Id
    private String roomId;

    private String type;

    private int chairs;

    private int whiteboards;

    private int tables;

    private int computers;

    private int associatedBuilding;

    public Rooms() {
    }


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int capacity) {
        this.chairs = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAssociatedBuilding() {
        return associatedBuilding;
    }

    public void setAssociatedBuilding(int associatedBuilding) {
        this.associatedBuilding = associatedBuilding;
    }

    public int getWhiteboards() {
        return whiteboards;
    }

    public void setWhiteboards(int whiteboards) {
        this.whiteboards = whiteboards;
    }

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public int getComputers() {
        return computers;
    }

    public void setComputers(int computers) {
        this.computers = computers;
    }
}
