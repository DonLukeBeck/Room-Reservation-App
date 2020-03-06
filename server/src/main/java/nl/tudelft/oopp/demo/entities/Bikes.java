package nl.tudelft.oopp.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class Bikes {
    @Id
    private int bikeId;

    private int bikeAvailability;

    private int bikeBuilding;

    public Bikes() {
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getBikeAvailability() {
        return bikeAvailability;
    }

    public void setBikeAvailability(int bikeAvailability) {
        this.bikeAvailability = bikeAvailability;
    }

    public int getBikeBuilding() {
        return bikeBuilding;
    }

    public void setBikeBuilding(int bikeBuilding) {
        this.bikeBuilding = bikeBuilding;
    }
}
