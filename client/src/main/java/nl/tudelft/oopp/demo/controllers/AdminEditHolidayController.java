package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Date;
import io.netty.channel.ChannelDuplexHandler;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import nl.tudelft.oopp.demo.communication.AdminServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Holidays;
import nl.tudelft.oopp.demo.entities.Rooms;

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

        List<Holidays> listGetHolidays = null;
        try {
            listGetHolidays = con.getHolidays();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] listAllHolidays = new String[listGetHolidays.size() + 1];
        listAllHolidays[0] = "Select Holiday";

        j=1;
        for(Holidays h : listGetHolidays){
            listAllHolidays[j] = "" + h.getComments();

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




        listBuildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        holidayID.setItems(FXCollections.observableArrayList(listAllHolidays));
        selectDish.setItems(FXCollections.observableArrayList(listAllDishes));
        selectNewDishName.setItems(FXCollections.observableArrayList(listAllDishes));

        listBuildingID.setValue("Select Building");
        holidayID.setValue("Select Holiday");
        selectDish.setValue("Select dish");
        selectNewDishName.setValue("Select dish");
        oldDishName.setValue("Select dish");


        holidayID.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {

                        List<Holidays> holi = null;
                        try {
                            holi = con.getHolidays();

                            for (Holidays hol : holi) {


                                if (hol.getHolidaysID() == (int) newValue) {
                                    startDate.setDayCellFactory((Callback<DatePicker,
                                            DateCell>) hol.getStartDate());
                                    endDate.setDayCellFactory((Callback<DatePicker,
                                            DateCell>) hol.getEndDate());

                                    comments.setText(hol.getComments());
                                }


                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.print("Select holiday");
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
                                dishes = con.getMenuByBuilding(selectedBuilding.getBuilding_number());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String[] listDishes = new String[dishes.size() + 1];
                            listDishes[0] = "Select dish";
                            int j = 1;
                            for (Dishes d : dishes) {
                                listDishes[j] = d.getName();
                                j++;
                            }

                            oldDishName.setItems(FXCollections.observableArrayList(listDishes));


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
                            Dishes dish = new Dishes();
                            for (Dishes d: list) {
                                if (d.getName().equals(newValue.toString())) {
                                    dish = d;
                                }
                            }
                            newDishName.setText(dish.getName());
                            price.setText(String.valueOf(dish.getPrice()));
                            boolean veg = true;
                            if (dish.getVegan() == 1) {
                                veg = false;
                            }
                            vegan.setSelected(veg);





                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("No selected building");
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
     * @param event admin clicking edit holidays
     * @throws IOException Exception if can't find admin edit holiday scene
     */
    public void editHoliday(Event event) throws  IOException {


        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (holidayID.getValue().equals("Select holiday")) {
            exception.setText("Please select holiday.");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        List<Holidays> list = null;
        try{
            list = con.getHolidays();
        }catch (IOException e) {
            e.printStackTrace();
        }
        Holidays hol = new Holidays();
        for(Holidays h : list){
            if(h.getComments().equals(holidayID.getValue().toString())){
                hol = h;
            }
        }

        int holID = hol.getHolidaysID();
        Date start = (Date) startDate.getDayCellFactory();
        Date end = (Date) endDate.getDayCellFactory();
        String comm = comments.getText();

        con.editHolidaysAdmin(start.toString(), end.toString(), comm, holID);

        HelperController h = new HelperController();
        h.loadNextScene("/AdminEditHoliday.fxml", mainScreen);
    }


    /**
     * Method for admin to edit menu.
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

        if (listBuildingID.getValue().equals("Select building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (selectNewDishName.getValue().equals("Select dish")) {
            exception.setText("Please select dish.");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (oldDishName.getValue().equals("Selecy dish")) {
            exception.setText("Please select dish.");
            exception.setLayoutY(120);
            exception.setLayoutX(45);
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
            exception.setLayoutY(120);
            exception.setLayoutX(45);
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
