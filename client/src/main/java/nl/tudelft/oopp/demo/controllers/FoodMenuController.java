package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Dishes;

public class FoodMenuController implements Initializable {
    public static List<Dishes> dishes;
    private static String dishesName;
    public static List<Dishes> menusByBuilding;

    ServerCommunication con = new ServerCommunication();
    HelperController helper = new HelperController();

    @FXML
    private javafx.scene.control.ScrollPane scene1;
    @FXML
    private javafx.scene.control.Button reserveScene;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Rectangle rect1;
    @FXML
    private Pane sidePane;
    @FXML
    private Pane rightPane;

    /**
     * Method for getting dish.
     * @return dish
     */
    public static String getDishesName() {
        return dishesName;
    }

    public static void setDishesName(String dishName) {
        dishesName = dishName;
    }

    /**
     * Method to pop up campus map.
     * @param event Clicking on 'campus map'
     * @throws IOException Exception if can't find campus map page
     */
    public void campusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Room Reservation App");
        stage.getIcons().add(new Image("images/favicon.png"));
        stage.show();
    }

    public void contactsOpen(Event event) throws IOException {
        helper.openContacts();
    }

    public void openResources(Event event) throws IOException {
        helper.openResources();
    }

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }

    /**
     * Method for initializing pane1, displaying all dishes from the right building
     * in rectangles/boxes.
     * @param location The location used to resolve relative paths for the root object,
     *                or null if the location is not known
     * @param resources The resources used to localize the root object,
     *                  or null if the root object was not localized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HelperController helper = new HelperController();
        helper.loadSidePane(sidePane);
        addRole();
        String buildingId = MainMenuController.getId();
        int builId = Integer.parseInt(buildingId);

        try {
            dishes = con.getDishes();
            menusByBuilding = con.getMenuByBuilding(builId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (menusByBuilding.isEmpty()) {
            emptyMenu();
            return;
        }

        for (int j = 0; j < menusByBuilding.size(); j++) {
            Rectangle last = null;
            String id1 = "";
            for (Node e : pane1.getChildren()) {
                if (e instanceof Rectangle) {
                    id1 = e.getId();
                    last = (Rectangle) e;
                }
            }

            if (id1.contains("A")) {
                Rectangle box = addBoxToScrollPane(340, last.layoutYProperty().getValue(), "B" + j);

                if (menusByBuilding.get(j).getVegan() == 1) {
                    addLabelToScrollPane(350, box.layoutYProperty().getValue()
                            + 10, "(vegan) Dish:", 18);
                } else {
                    addLabelToScrollPane(350, box.layoutYProperty().getValue()
                            + 10, "Dish:", 18);
                }

                addLabelToScrollPane(350, box.layoutYProperty().getValue()
                        + 70, "Price: ", 18);

                addLabelToScrollPane(370, box.layoutYProperty().getValue()
                        + 40, menusByBuilding.get(j).getName(), 20);

                addLabelToScrollPane(370, box.layoutYProperty().getValue()
                        + 100, menusByBuilding.get(j).getPrice() + ".00", 20);
            }
            if (id1.contains("B")) {
                Rectangle box = addBoxToScrollPane(625, last.layoutYProperty().getValue(),
                        "C" + j);

                if (menusByBuilding.get(j).getVegan() == 1) {
                    addLabelToScrollPane(640, box.layoutYProperty().getValue()
                            + 10, "(vegan) Dish:", 18);
                } else {
                    addLabelToScrollPane(640, box.layoutYProperty().getValue()
                            + 10, "Dish:", 18);
                }

                addLabelToScrollPane(640, box.layoutYProperty().getValue()
                        + 70, "Price: ", 18);

                addLabelToScrollPane(660, box.layoutYProperty().getValue()
                        + 40, menusByBuilding.get(j).getName(), 20);

                addLabelToScrollPane(660, box.layoutYProperty().getValue()
                        + 100, menusByBuilding.get(j).getPrice() + ".00", 20);
            }
            if (id1.contains("C")) {
                Rectangle box = addBoxToScrollPane(55,
                        last.layoutYProperty().getValue() + 176, "A" + j);

                if (menusByBuilding.get(j).getVegan() == 1) {
                    addLabelToScrollPane(65, box.layoutYProperty().getValue()
                            + 10, "(vegan) Dish:", 18);
                } else {
                    addLabelToScrollPane(65, box.layoutYProperty().getValue()
                            + 10, "Dish:", 18);
                }

                addLabelToScrollPane(65, box.layoutYProperty().getValue()
                        + 70, "Price: ", 18);

                addLabelToScrollPane(85, box.layoutYProperty().getValue()
                        + 40, menusByBuilding.get(j).getName(), 20);

                addLabelToScrollPane(85, box.layoutYProperty().getValue()
                        + 100, menusByBuilding.get(j).getPrice() + ".00", 20);
            }
        }
    }


    public void paneExit(Event event) throws IOException {
        helper.exit(mainScreen);
    }

    public void paneLogOut(Event event) throws  IOException {
        helper.logOut(mainScreen);
    }

    public void paneUserProfile(Event event) throws IOException {
        helper.userProfile(mainScreen);
    }

    /**
     * Method that adds a label to the scrollpane.
     * @param layoutX the x-value where the label will be at
     * @param layoutY the y-value where the label will be at
     * @param text String that is displayed in the label
     * @param size Size of font
     */
    public void addLabelToScrollPane(double layoutX, double layoutY, String text, int size) {
        Label label = new Label(text);
        pane1.getChildren().add(label);
        label.setLayoutY(layoutY);
        label.setLayoutX(layoutX);
        label.setFont(Font.font("Arial Rounded MT Bold", size));
    }

    /**
     * Method that adds rectangles to the scrollpane
     * used for clickable boxes.
     * @param layoutX the x-value where the rectangle will be at
     * @param layoutY the y-value where the rectangle will be at
     * @param id id of the rectangle
     * @return a rectangle
     */
    public Rectangle addBoxToScrollPane(double layoutX, double layoutY, String id) {
        Rectangle box = new Rectangle(240, 136);

        box.arcHeightProperty().setValue(30.0);
        box.arcWidthProperty().setValue(30.0);
        box.layoutXProperty().setValue(layoutX);
        box.layoutYProperty().setValue(layoutY);
        box.fillProperty().setValue(Color.valueOf("white"));
        box.setStroke(Color.valueOf("#00A6D6"));
        box.strokeWidthProperty().setValue(2);
        box.setVisible(true);
        box.setId(id);
        box.setOnMouseClicked(event -> {
            try {
                dishChosen(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pane1.getChildren().add(box);
        return box;

    }

    /**
     * Method that saves the chosen dish and moves you to next scene.
     * @param event clicking on a rectangle
     * @throws IOException Exception if can't find timeslot food scene
     */
    public void dishChosen(Event event) throws IOException {
        String str = event.getSource().toString();
        dishesName = getDishName(str);

        String buildingId = MainMenuController.getId().substring(1);

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/TimeSlotFood.fxml", mainScreen);
    }

    /**
     * Method for getting name of the selected dish.
     * @param str string used for getting the right name
     * @return name of the dish
     */
    public String getDishName(String str) {
        String[] temp = str.split(" ");
        String newTemp = "";
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("id=")) {
                newTemp = temp[i];
            }
        }
        String[] arrId = newTemp.split("=");
        String temp2 = arrId[1];
        temp2 = temp2.substring(1, temp2.length() - 1);

        int dishIndex = Integer.parseInt(temp2);
        return menusByBuilding.get(dishIndex).getName();
    }

    /**
     * Method to go back to previous page.
     *
     * @param event Clicking on 'Go Back"
     * @throws IOException Exception if can't find main reservation menu page
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainReservationMenu.fxml", mainScreen);
    }

    /**
     * Method for when there are no
     * available dishes for a certain building.
     */
    public void emptyMenu() {
        Label noDishes = new Label("No dishes are available for this building");
        noDishes.setFont(Font.font("Arial Rounded MT Bold", 24));
        pane1.getChildren().add(noDishes);

        Label noDishes2 = new Label("Please choose a different building");
        noDishes2.setFont(Font.font("Arial Rounded MT Bold", 24));
        pane1.getChildren().add(noDishes2);
        noDishes2.setLayoutY(noDishes.layoutYProperty().getValue() + 40);
    }
}
