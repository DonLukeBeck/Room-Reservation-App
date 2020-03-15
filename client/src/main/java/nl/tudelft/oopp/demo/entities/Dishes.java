package nl.tudelft.oopp.demo.entities;


public class Dishes {

    private String name;

    private int price;

    private int vegan;

    private String menuAssociated;

    public Dishes() {
    }

    /**
     *Method to return Dish Name.
     * @return Dish Name
     */
    public String getName() {
        return name;
    }

    /**
     *Method to set Dish Name.
     * @param name Dish Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *Method to get the price of a dish.
     * @return Dish Price
     */
    public int getPrice() {
        return price;
    }

    /**
     *Method to set the price of a dish.
     * @param price Dish Price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *Method to get Vegan boolean.
     * @return Vegan Boolean
     */
    public int getVegan() {
        return vegan;
    }

    /**
     *Method to set Vegan Boolean.
     * @param vegan Vegan Boolean
     */
    public void setVegan(int vegan) {
        this.vegan = vegan;
    }

    /**
     *Method to get the Menu the dish is part of.
     * @return Associated Menu
     */
    public String getMenuAssociated() {
        return menuAssociated;
    }

    /**
     *Method to set the menu a dish is part of.
     * @param menuAssociated Associated Menu
     */
    public void setMenuAssociated(String menuAssociated) {
        this.menuAssociated = menuAssociated;
    }
}
