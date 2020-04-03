package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.tudelft.oopp.demo.communication.AdminServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Rooms;

public class AdminHolidayAddController implements Initializable {
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
    private TextField dishName;

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
        helper.loadNextScene("/AdminHolidayAdd.fxml", mainScreen);
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
        listBuildingID.setValue("Select Building");
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


        String start =  startDate.getDayCellFactory().toString();
        String end = endDate.getDayCellFactory().toString();

        if (start.isEmpty()) {

            exception.setText("Please select start date.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;


        }

        if (end.isEmpty()) {

            exception.setText("Please select end date.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        String comm = comments.getText();

        con.addHolidayAdmin(start, end, comm);


        HelperController h = new HelperController();
        h.loadNextScene("/AdminHolidayAdd.fxml", mainScreen);


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
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (dishName.getText().isEmpty()) {
            exception.setText("Please enter dish name.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        int build = Integer.parseInt(listBuildingID.getValue().toString());

        String name = dishName.getText();

        con.addMenuAdmin(build, name);

        HelperController h = new HelperController();
        h.loadNextScene("/AdminHolidayAdd.fxml", mainScreen);



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
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (price.getText().isEmpty()) {
            exception.setText("Please enter price.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
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
            exception.setLayoutX(670);
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
        h.loadNextScene("/AdminHolidayAdd.fxml", mainScreen);

    }



}
