package nl.tudelft.oopp.demo.entities;


public class Menus {

    private int buildingNumber;

    private String dishName;

    public Menus() {
    }

    /**
     * Method to get the place to which a menu is delivered.
     *
     * @return Menu Delivery Place
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * Method to set the place at which the menu is delivered.
     *
     * @param dishName Menu Delivery Place
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * Method to get the building with which the menu is associated.
     *
     * @return Associated Building
     */
    public int getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * Method to set the building with which the menu is associated.
     *
     * @param buildingNumber Associated Building
     */
    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
