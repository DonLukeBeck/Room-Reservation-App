package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.tudelft.oopp.demo.communication.AdminServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Holidays;

public class AdminEditHolidayController implements Initializable {

    AdminServerCommunication con = new AdminServerCommunication();

    @FXML
    private javafx.scene.control.Button edit;

    @FXML
    private javafx.scene.control.Button goBack;

    @FXML
    private javafx.scene.control.ChoiceBox listBuildingID;

    @FXML
    private javafx.scene.control.TextArea comments;

    @FXML
    private javafx.scene.control.ChoiceBox selectNewDishName;

    @FXML
    private javafx.scene.control.TextField newDishName;

    @FXML
    private javafx.scene.control.TextField price;

    @FXML
    private javafx.scene.control.DatePicker startDate;

    @FXML
    private javafx.scene.control.DatePicker endDate;

    @FXML
    private javafx.scene.control.ChoiceBox holidayID;

    @FXML
    private javafx.scene.control.CheckBox vegan;

    @FXML
    private javafx.scene.control.ChoiceBox oldDishName;

    @FXML
    private javafx.scene.control.ChoiceBox selectDish;

    @FXML
    private AnchorPane mainScreen;

    private int selectedHoliday;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Buildings> listGetBuildings = null;
        try {
            listGetBuildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] listAllBuildings = new String[listGetBuildings.size() + 1];
        listAllBuildings[0] = "Select Building";
        int j = 1;
        for (Buildings t : listGetBuildings) {
            listAllBuildings[j] = "" + t.getBuilding_number();
            j++;
        }

        List<Holidays> listGetHolidays = null;
        try {
            listGetHolidays = con.getHolidays();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] listAllHolidays = new String[listGetHolidays.size() + 1];
        listAllHolidays[0] = "Select Holiday";

        j = 1;
        for (Holidays h : listGetHolidays) {
            listAllHolidays[j] = "" + h.getComments();
            j++;
        }

        List<Dishes> listGetDishes = null;
        try {
            listGetDishes = con.getDishes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] listAllDishes = new String[listGetDishes.size() + 1];
        listAllDishes[0] = "Select Dish";
        j = 1;
        for (Dishes d : listGetDishes) {
            listAllDishes[j] = "" + d.getName();
            j++;
        }

        String[] listAllDishesByBuilding = new String[2];
        listAllDishesByBuilding[0] = "Select Dish";
        listAllDishesByBuilding[1] = "First select building";

        listBuildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        holidayID.setItems(FXCollections.observableArrayList(listAllHolidays));
        selectDish.setItems(FXCollections.observableArrayList(listAllDishes));
        selectNewDishName.setItems(FXCollections.observableArrayList(listAllDishesByBuilding));
        oldDishName.setItems(FXCollections.observableArrayList(listAllDishesByBuilding));

        listBuildingID.setValue("Select Building");
        holidayID.setValue("Select Holiday");
        selectDish.setValue("Select Dish");
        selectNewDishName.setValue("Select Dish");
        oldDishName.setValue("Select Dish");

        holidayID.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {
                        try {
                            selectedHoliday = (int) newValue - 1;
                            List<Holidays> listAllHolidays = con.getHolidays();
                            Holidays holiday = listAllHolidays.get(selectedHoliday);
                            selectedHoliday = holiday.getHolidaysID();
                            startDate
                                    .setValue(holiday.getStartDate()
                                            .toInstant().atZone(ZoneId
                                                    .systemDefault()).toLocalDate());
                            endDate
                                    .setValue(holiday.getEndDate()
                                            .toInstant().atZone(ZoneId
                                                    .systemDefault()).toLocalDate());
                            comments.setText(holiday.getComments());
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.print("Select Holiday");
                        }
                    }
                });

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

                            List<Dishes> dishes = null;
                            try {
                                dishes = con.getMenuByBuilding(
                                        selectedBuilding.getBuilding_number());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String[] listDishes = new String[dishes.size() + 1];
                            listDishes[0] = "Select Dish";
                            int j = 1;
                            for (Dishes d : dishes) {
                                listDishes[j] = d.getName();
                                j++;
                            }

                            List<Dishes> listGetDishes = null;
                            try {
                                listGetDishes = con.getDishes();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            listGetDishes.removeAll(dishes);

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

                            oldDishName.setItems(FXCollections.observableArrayList(listDishes));
                            selectNewDishName.setItems(FXCollections
                                    .observableArrayList(listAllDishes1));
                            oldDishName.setValue("Select Dish");
                            selectNewDishName.setValue("Select Dish");

                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("No selected building");
                        }
                    }
                });

        selectDish.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {
                        try {
                            List<Dishes> list = con.getDishes();
                            Dishes dish = list.get((int) newValue - 1);
                            for (Dishes d : list) {
                                if (d.getName().equals(newValue.toString())) {
                                    dish = d;
                                }
                            }
                            newDishName.setText(dish.getName());
                            price.setText(String.valueOf(dish.getPrice()));
                            boolean veg = false;
                            if (dish.getVegan() == 1) {
                                veg = true;
                            }
                            vegan.setSelected(veg);

                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("No selected dish");
                        }
                    }
                });

    }


    public void goBack(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/EditSelectionAdmin.fxml", mainScreen);
    }

    /**
     * Method for admin to edit holidays.
     *
     * @param event admin clicking edit holidays
     * @throws IOException Exception if can't find admin edit holiday scene
     */
    public void editHoliday(Event event) throws IOException {


        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (holidayID.getValue().equals("Select Holiday")) {
            exception.setText("Please select holiday.");
            exception.setLayoutY(110);
            exception.setLayoutX(14);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }


        Date start = java.sql.Date.valueOf(startDate.getValue());
        Date end = java.sql.Date.valueOf(endDate.getValue());
        String comm = comments.getText();

        con.editHolidaysAdmin(start.toString(), end.toString(), comm, selectedHoliday);

        HelperController h = new HelperController();
        h.loadNextScene("/AdminEditHoliday.fxml", mainScreen);
    }


    /**
     * Method for admin to edit menu.
     *
     * @param event Admin clicking edit menu
     * @throws IOException Exception if can't find scene
     */
    public void editMenu(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (listBuildingID.getValue().equals("Select Building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(110);
            exception.setLayoutX(14);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (selectNewDishName.getValue().equals("Select Dish")) {
            exception.setText("Please select dish.");
            exception.setLayoutY(110);
            exception.setLayoutX(14);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (oldDishName.getValue().equals("Select Dish")) {
            exception.setText("Please select dish.");
            exception.setLayoutY(110);
            exception.setLayoutX(14);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        con.editMenuAdmin(Integer.parseInt(listBuildingID.getValue().toString()),
                selectNewDishName.getValue().toString(), oldDishName.getValue().toString());

        HelperController h = new HelperController();
        h.loadNextScene("/AdminEditHoliday.fxml", mainScreen);
    }

    /**
     * Method for admin to edit a dish.
     *
     * @param event Admin clicking edit dish
     * @throws IOException Exception if can't find scene
     */
    public void editDish(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (selectDish.getValue().equals("Select Dish")) {
            exception.setText("Please select dish.");
            exception.setLayoutY(110);
            exception.setLayoutX(14);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        String old = selectDish.getValue().toString();
        int pr = Integer.parseInt(price.getText());
        int veg = 0;

        if (vegan.isSelected() == true) {
            veg = 1;
        }

        con.editDishAdmin(newDishName.getText(), pr, veg, old);


        HelperController h = new HelperController();
        h.loadNextScene("/AdminEditHoliday.fxml", mainScreen);

    }


}
