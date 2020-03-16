package nl.tudelft.oopp.demo.entities;


public class Menus {

    private String deliveryPlace;

    private int building;

    public Menus() {
    }

    /**
     * Method to get the place to which a menu is delivered.
     * @return Menu Delivery Place
     */
    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    /**
     * Method to set the place at which the menu is delivered.
     * @param deliveryPlace Menu Delivery Place
     */
    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    /**
     *Method to get the building with which the menu is associated.
     * @return Associated Building
     */
    public int getBuilding() {
        return building;
    }

    /**
     *Method to set the building with which the menu is associated.
     * @param building Associated Building
     */
    public void setBuilding(int building) {
        this.building = building;
    }
}
