package nl.tudelft.oopp.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class Dishes {
    @Id
    private String name;

    private int price;

    private int vegan;

    private String menuAssociated;

    public Dishes() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVegan() {
        return vegan;
    }

    public void setVegan(int vegan) {
        this.vegan = vegan;
    }

    public String getMenuAssociated() {
        return menuAssociated;
    }

    public void setMenuAssociated(String menuAssociated) {
        this.menuAssociated = menuAssociated;
    }
}
