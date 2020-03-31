package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Menus;

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

    /**
     * Method to pop up campus map.
     * @param event Clicking on 'campus map'
     * @throws IOException
     */
    public void campusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addRole() {
        helper.addRole(rightPane, MainSceneController.getRole());
    }


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
            EmptyMenu();
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

    /**
     *
     * @param event
     * @throws IOException
     */
    public void paneUserProfile(Event event) throws IOException {
        helper.userProfile(mainScreen);
    }

    /**
     *
     * @param layoutX
     * @param layoutY
     * @param text
     * @param size
     */
    public void addLabelToScrollPane(double layoutX, double layoutY, String text, int size) {
        Label label = new Label(text);
        pane1.getChildren().add(label);
        label.setLayoutY(layoutY);
        label.setLayoutX(layoutX);
        label.setFont(Font.font("Arial Rounded MT Bold", size));
    }

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

    public void dishChosen(Event event) throws IOException {
        String str = event.getSource().toString();
        dishesName = getDishName(str);

        String buildingId = MainMenuController.getId().substring(1);

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/TimeSlotFood.fxml", mainScreen);
    }

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
     * @throws IOException
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainReservationMenu.fxml", mainScreen);
    }

    public void EmptyMenu() {
        Label noDishes = new Label("No dishes are available for this building");
        noDishes.setFont(Font.font("Arial Rounded MT Bold", 24));
        pane1.getChildren().add(noDishes);

        Label noDishes2 = new Label("Please choose a different building");
        noDishes2.setFont(Font.font("Arial Rounded MT Bold", 24));
        pane1.getChildren().add(noDishes2);
        noDishes2.setLayoutY(noDishes.layoutYProperty().getValue() + 40);
    }
}
