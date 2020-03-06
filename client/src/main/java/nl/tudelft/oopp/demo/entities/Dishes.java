package nl.tudelft.oopp.demo.entities;


public class Dishes {

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
