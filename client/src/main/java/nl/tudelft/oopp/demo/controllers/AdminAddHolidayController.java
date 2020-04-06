package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.tudelft.oopp.demo.communication.AdminServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Dishes;

public class AdminAddHolidayController implements Initializable {
    AdminServerCommunication con = new AdminServerCommunication();

    @FXML
    private AnchorPane mainScreen;

    @FXML
    private javafx.scene.control.Button add;

    @FXML
    private javafx.scene.control.Button goBack;

    @FXML
    private TextArea comments;

    @FXML
    private ChoiceBox listBuildingID;

    @FXML
    private ChoiceBox dishName;

    @FXML
    private TextField dishName1;

    @FXML
    private TextField price;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private CheckBox vegan;


    /**
     * Method to go to admin holiday add view.
     * @param event Clicking on add
     * @throws IOException Exception if can't find admin edit view scene
     */
    public void goToAdminHolidayAdd(ActionEvent event) throws IOException {

        HelperController helper = new HelperController();
        helper.loadNextScene("/AdminAddHolidayView.fxml", mainScreen);
    }


    /**
     *Method to initialize.
     * @param location Location of the picture
     * @param resources Resource Bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Buildings> listGetBuildings = null;
        try {
            listGetBuildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] listAllBuildings = new String[listGetBuildings.size() + 1];
        listAllBuildings[0] = "Select building";
        int j = 1;
        for (Buildings t : listGetBuildings) {
            listAllBuildings[j] = "" + t.getBuilding_number();
            j++;
        }

        listBuildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        listBuildingID.setValue("Select building");

        String[] listAllDishes = new String[2];
        listAllDishes[0] = "Select dishes";
        listAllDishes[1] = "First select building";

        dishName.setItems(FXCollections.observableArrayList(listAllDishes));
        dishName.setValue("Select dishes");

        listBuildingID.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {
                        try {
                            Buildings selectedBuilding = con
                                    .getBuildingByName(Integer
                                            .parseInt(listAllBuildings[(int) newValue]));

                            List<Dishes> listGetDishes = null;
                            try {
                                listGetDishes = con.getDishes();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            String[] listAllDishes = new String[listGetDishes.size() + 1];
                            listAllDishes[0] = "Select Dish";
                            int j = 1;
                            for (Dishes d : listGetDishes) {
                                listAllDishes[j] = d.getName();
                                j++;
                            }

                            List<Dishes> dishes = null;
                            try {
                                dishes = con.getMenuByBuilding(
                                        selectedBuilding.getBuilding_number());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            String[] listDishesBuilding = new String[dishes.size() + 1];
                            listDishesBuilding[0] = "Select Dish";
                            j = 1;
                            for (Dishes d : dishes) {
                                listDishesBuilding[j] = d.getName();
                                j++;
                            }

                            String[] listAllDishes1 =
                                    new String[listGetDishes.size() - dishes.size() + 1];
                            listAllDishes1[0] = "Select Dish";

                            int i = 1;
                            boolean ok;
                            for (Dishes d : listGetDishes) {
                                ok = true;
                                for (Dishes di : dishes) {
                                    if (di.getName().equals(d.getName()) && ok) {
                                        ok = false;
                                    }
                                }
                                if (ok == true) {
                                    listAllDishes1[i] = "" + d.getName();
                                    i++;
                                }
                            }

                            if (listAllDishes1.length == 1) {
                                listAllDishes1[0] = "Menu is full";
                                dishName.setItems(FXCollections
                                        .observableArrayList(listAllDishes1));
                                dishName.setValue("Menu is full");
                            } else {
                                dishName.setItems(FXCollections
                                        .observableArrayList(listAllDishes1));
                                dishName.setValue("Select Dish");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("No selected building");
                        }
                    }
                });
    }



    /**
     *Method to go back to main admin scene.
     * @param event Clicking on go back
     * @throws IOException Exception if can't find main admin scene
     */
    public void goBack(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainAdminScene.fxml", mainScreen);
    }


    /**
     * Method for admin to Add holiday.
     * @param event Clicking on add
     * @throws IOException Exception if can't find admin holiday add view scene
     */
    public void addHoliday(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        String start = "";
        try {
            start = startDate.getValue().toString();
        } catch (Exception e) {
            addException(45, 120, "Please select start date!", exception);
            return;
        }

        String end = "";
        try {
            end = endDate.getValue().toString();
        } catch (Exception e) {
            addException(45, 120, "Please select end date!", exception);
            return;
        }

        if (comments.getText().isEmpty()) {
            addException(45,120,"Please add some comments!",exception);
            return;
        }

        con.addHolidayAdmin(start, end, comments.getText());

        HelperController h = new HelperController();
        h.loadNextScene("/AdminAddHolidayView.fxml", mainScreen);

    }

    /**
     * Method for admin to Add menu.
     * @param event Clicking on add
     * @throws IOException Exception if can't find admin holiday add view scene
     */

    public void addMenu(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }


        if (listBuildingID.getValue().toString().equals("Select building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(453);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (dishName.getValue().toString().equals("Select dishes")) {
            exception.setText("Please select a dish.");
            exception.setLayoutY(120);
            exception.setLayoutX(453);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        int build = Integer.parseInt(listBuildingID.getValue().toString());

        String name = dishName.getValue().toString();

        con.addMenuAdmin(build, name);

        HelperController h = new HelperController();
        h.loadNextScene("/AdminAddHolidayView.fxml", mainScreen);



    }

    /**
     * Method for admin to Add dish.
     * @param event Clicking on add
     * @throws IOException Exception if can't find admin holiday add view scene
     */
    public void addDish(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (dishName1.getText().isEmpty()) {
            exception.setText("Please enter dish name.");
            exception.setLayoutY(120);
            exception.setLayoutX(881);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (price.getText().isEmpty()) {
            exception.setText("Please enter price.");
            exception.setLayoutY(120);
            exception.setLayoutX(881);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;

        }

        int pr = 0;
        try {
            pr = Integer.parseInt(price.getText());
        } catch (Exception e) {
            exception.setText("Only numbers for price");
            exception.setLayoutY(120);
            exception.setLayoutX(881);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        int k = 0;
        if (vegan.isSelected()) {
            k = 1;
        }
        String dish = dishName1.getText();

        con.addDishAdmin(dish, pr, k);

        HelperController h = new HelperController();
        h.loadNextScene("/AdminAddHolidayView.fxml", mainScreen);

    }

    /**
     * Open exception when needed.
     * @param layoutX layout X
     * @param layoutY layout Y
     * @param text text for the exception
     * @param exception Label which text will be changed
     */
    public void addException(double layoutX, double layoutY, String text, Label exception) {
        exception.setText(text);
        exception.setLayoutY(layoutY);
        exception.setLayoutX(layoutX);
        exception.setTextFill(Color.valueOf("red"));
        exception.setFont(Font.font(20));
        exception.setId("Exception");
    }

}
