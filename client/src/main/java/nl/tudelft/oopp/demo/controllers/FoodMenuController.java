package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Menus;
import nl.tudelft.oopp.demo.entities.Reservations;

public class FoodMenuController implements Initializable {
    public static List<Dishes> dishes;
    private static String dishesName;

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



    ServerCommunication con = new ServerCommunication();

    /**
     * Method for getting dish
     *
     * @return dish
     */
    public static String getDishesName() {  return dishesName;  }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HelperController helper = new HelperController();
        helper.loadSidePane(sidePane);

        String buildingId = MainMenuController.getId();
        int builId = Integer.parseInt(buildingId);

        //List<Dishes> allDishes = new ArrayList<>();

        List<Menus> allMenus = new ArrayList<>();
        List<Dishes> menusByBuilding = new ArrayList<>();

        try {
            //allDishes = con.getDishes();

            dishes = con.getDishes();
            allMenus = con.getMenus();
            menusByBuilding = con.getMenusByBuilding(builId);
        }   catch (IOException e) {
            e.printStackTrace();
        }
/*
        dishes = new ArrayList<>();

        for (int i = 0; i < allDishes.size(); i++) {
            for (int j = 0; j < allMenus.size(); j++) {
                if (allDishes.get(i).getName().equals(allMenus.get(j).getDishName())) {
                    if (allMenus.get(j).getBuildingNumber() == builId) {
                        dishes.add(allDishes.get(i));
                    }
                }
            }
        }
*/
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
                Rectangle box = new Rectangle(240, 136);

                box.arcHeightProperty().setValue(30.0);
                box.arcWidthProperty().setValue(30.0);
                box.layoutXProperty().setValue(340);
                box.layoutYProperty().setValue(last.layoutYProperty().getValue());
                box.fillProperty().setValue(Color.valueOf("white"));
                box.setStroke(Color.valueOf("#00A6D6"));
                box.strokeWidthProperty().setValue(2);
                box.setVisible(true);
                box.setId("B" + j);
                box.setOnMouseClicked(event -> {
                    try {
                        dishChosen(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pane1.getChildren().add(box);

                Label dishId = new Label("Dish:");
                if (menusByBuilding.get(j).getVegan() == 1) {
                    dishId = new Label("(vegan) Dish:");
                }
                pane1.getChildren().add(dishId);
                dishId.setLayoutY(box.layoutYProperty().getValue() + 10);
                dishId.setLayoutX(350);
                dishId.setFont(Font.font("Arial Rounded MT Bold", 18));

                Label price = new Label("Price: ");
                pane1.getChildren().add(price);
                price.setLayoutY(box.layoutYProperty().getValue() + 70);
                price.setLayoutX(350);
                price.setFont(Font.font("Arial Rounded MT Bold", 18));

                Label dishId1 = new Label(menusByBuilding.get(j).getName());

                pane1.getChildren().add(dishId1);
                dishId1.setLayoutY(box.layoutYProperty().getValue() + 40);
                dishId1.setLayoutX(370);
                dishId1.setFont(Font.font("Arial Rounded MT Bold", 20));

                Label price1 = new Label( menusByBuilding.get(j).getPrice() + ".00");
                pane1.getChildren().add(price1);
                price1.setLayoutY(box.layoutYProperty().getValue() + 100);
                price1.setLayoutX(370);
                price1.setFont(Font.font("Arial Rounded MT Bold", 20));


            }
            if (id1.contains("B")) {
                Rectangle box = new Rectangle(240, 136);

                box.arcHeightProperty().setValue(30.0);
                box.arcWidthProperty().setValue(30.0);
                box.layoutXProperty().setValue(625);
                box.layoutYProperty().setValue(last.layoutYProperty().getValue());
                box.fillProperty().setValue(Color.valueOf("white"));
                box.setStroke(Color.valueOf("#00A6D6"));
                box.strokeWidthProperty().setValue(2);
                box.setVisible(true);
                box.setId("C" + j);
                box.setOnMouseClicked(event -> {
                    try {
                        dishChosen(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pane1.getChildren().add(box);

                Label dishId = new Label("Dish:");
                if (menusByBuilding.get(j).getVegan() == 1) {
                    dishId = new Label("(vegan) Dish:");
                }
                pane1.getChildren().add(dishId);
                dishId.setLayoutY(box.layoutYProperty().getValue() + 10);
                dishId.setLayoutX(640);
                dishId.setFont(Font.font("Arial Rounded MT Bold", 18));

                Label price = new Label("Price: ");
                pane1.getChildren().add(price);
                price.setLayoutY(box.layoutYProperty().getValue() + 70);
                price.setLayoutX(640);
                price.setFont(Font.font("Arial Rounded MT Bold", 18));

                Label dishId1 = new Label(menusByBuilding.get(j).getName());
                pane1.getChildren().add(dishId1);
                dishId1.setLayoutY(box.layoutYProperty().getValue() + 40);
                dishId1.setLayoutX(660);
                dishId1.setFont(Font.font("Arial Rounded MT Bold", 20));

                Label price1 = new Label(menusByBuilding.get(j).getPrice() + ".00");
                pane1.getChildren().add(price1);
                price1.setLayoutY(box.layoutYProperty().getValue() + 100);
                price1.setLayoutX(660);
                price1.setFont(Font.font("Arial Rounded MT Bold", 20));
            }
            if (id1.contains("C")) {
                Rectangle box = new Rectangle(240, 136);

                box.arcHeightProperty().setValue(30.0);
                box.arcWidthProperty().setValue(30.0);
                box.layoutXProperty().setValue(55);
                box.layoutYProperty().setValue(last.layoutYProperty().getValue() + 176);
                box.fillProperty().setValue(Color.valueOf("white"));
                box.setStroke(Color.valueOf("#00A6D6"));
                box.strokeWidthProperty().setValue(2);
                box.setVisible(true);
                box.setId("A" + j);
                box.setOnMouseClicked(event -> {
                    try {
                        dishChosen(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pane1.getChildren().add(box);

                Label dishId = new Label("Dish:");
                if (menusByBuilding.get(j).getVegan() == 1) {
                    dishId = new Label("(vegan) Dish:");
                }
                pane1.getChildren().add(dishId);
                dishId.setLayoutY(box.layoutYProperty().getValue() + 10);
                dishId.setLayoutX(65);
                dishId.setFont(Font.font("Arial Rounded MT Bold", 18));

                Label price = new Label("Price:");
                pane1.getChildren().add(price);
                price.setLayoutY(box.layoutYProperty().getValue() + 70);
                price.setLayoutX(65);
                price.setFont(Font.font("Arial Rounded MT Bold", 18));

                Label dishId1 = new Label(menusByBuilding.get(j).getName());
                pane1.getChildren().add(dishId1);
                dishId1.setLayoutY(box.layoutYProperty().getValue() + 40);
                dishId1.setLayoutX(85);
                dishId1.setFont(Font.font("Arial Rounded MT Bold", 20));

                Label price1 = new Label(menusByBuilding.get(j).getPrice() + ".00");
                pane1.getChildren().add(price1);
                price1.setLayoutY(box.layoutYProperty().getValue() + 100);
                price1.setLayoutX(85);
                price1.setFont(Font.font("Arial Rounded MT Bold", 20));
            }

        }
    }


    public void dishChosen(Event event) throws IOException {
        String str = event.getSource().toString();
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
        dishesName = dishes.get(dishIndex).getName();

        String buildingId = MainMenuController.getId().substring(1);

        HelperController helperController = new HelperController();
        helperController.loadNextScene("/TimeSlotFood.fxml", mainScreen);
    }

    /**
     * Method to go back to previous page.
     * @param event Clicking on 'Go Back"
     * @throws IOException
     */
    public void goBack(Event event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainReservationMenu.fxml", mainScreen);
    }
}
