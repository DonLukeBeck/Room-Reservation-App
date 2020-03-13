package nl.tudelft.oopp.demo.entities;

import java.sql.Blob;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.type.BlobType;

@Entity // This tells Hibernate to make a table out of this class

public class Buildings {
    @Id
    private int building_number;

    private String name;

    private Time opening_hours;

    private Time closing_hours;

    private int number_of_rooms;

    private int number_of_bikes;

    private String url;

    public Buildings() {
    }


    public int getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(int building_number) {
        this.building_number = building_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(Time opening_hours) {
        this.opening_hours = opening_hours;
    }

    public Time getClosing_hours() {
        return closing_hours;
    }

    public void setClosing_hours(Time closing_hours) {
        this.closing_hours = closing_hours;
    }

    public int getNumber_of_rooms() {
        return number_of_rooms;
    }

    public void setNumber_of_rooms(int number_of_rooms) {
        this.number_of_rooms = number_of_rooms;
    }

    public int getNumber_of_bikes() {
        return number_of_bikes;
    }

    public void setNumber_of_bikes(int number_of_bikes) {
        this.number_of_bikes = number_of_bikes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
