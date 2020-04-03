package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.tudelft.oopp.demo.communication.AdminServerCommunication;
import nl.tudelft.oopp.demo.entities.*;

public class AdminDeleteController implements Initializable {

    AdminServerCommunication con = new AdminServerCommunication();

    @FXML
    private javafx.scene.control.Button delete;

    @FXML
    private javafx.scene.control.Button goBack;

    @FXML
    private AnchorPane mainScreen;

    @FXML
    private ChoiceBox listBuildingID;

    @FXML
    private ChoiceBox listBuildingID1;

    @FXML
    private ChoiceBox listBuildingID2;

    @FXML
    private ChoiceBox listRoomID;

    @FXML
    private ChoiceBox listDishes;

    @FXML
    private ChoiceBox listHolidayID;

    @FXML
    private ChoiceBox listMenus;

    /**
     * Method to go to admin delete view.
     * @param event Clicking on delete
     * @throws IOException Exception if can't find admin edit view scene
     */

    public void goToAdminDelete(ActionEvent event) throws IOException{
        HelperController helper = new HelperController();
        helper.loadNextScene("/AdminDeleteView.fxml", mainScreen);
    }


    /**
     *Method to initialize.
     * @param location Location of the picture
     * @param resources Resource Bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){

        List<Buildings> listGetBuildings = null;
        int j = 0;
        try {
            listGetBuildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String[] listAllBuildings = new String[listGetBuildings.size() + 1];
        listAllBuildings[0] = "Select building";
        j = 1;
        for (Buildings t : listGetBuildings) {
            listAllBuildings[j] = "" + t.getBuilding_number();
            j++;
        }

        String[] listAllRooms = new String[2];
        listAllRooms[0] = "Select room";
        listAllRooms[1] = "First select building";

        List<Holidays> listGetHolidays = null;
        try{
            listGetHolidays = con.getHolidays();
        } catch (IOException e){
            e.printStackTrace();
        }

        String[] listHolidaysID = new String[listGetHolidays.size() + 1];
        listHolidaysID[0] = "Select holiday ID";

        j =  1;
        for(Holidays h : listGetHolidays) {
            listHolidaysID[j] = "" + h.getHolidaysID();
            j++;
        }

        String[] listAllMenus = new String[2];
        listAllMenus[0] = "Select menu";
        listAllMenus[1] = "First select building";

        String[] listAllDishes = new String[2];
        listAllDishes[0] = "Select dish";
        listAllDishes[1] = "First select building";



        listBuildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        listBuildingID1.setItems(FXCollections.observableArrayList(listAllBuildings));
        listBuildingID2.setItems(FXCollections.observableArrayList(listAllBuildings));
        listHolidayID.setItems(FXCollections.observableArrayList(listHolidayID));
        listRoomID.setItems(FXCollections.observableArrayList(listAllRooms));
        listDishes.setItems(FXCollections.observableArrayList(listAllDishes));
        listMenus.setItems(FXCollections.observableArrayList(listAllMenus));

        listBuildingID.setValue("Select Building");
        listBuildingID1.setValue("Select Building");
        listBuildingID2.setValue("Select Building");
        listHolidayID.setValue("Select Building");
        listRoomID.setValue("Select Room");
        listDishes.setValue("Select Dish");
        listMenus.setValue("Select Menu");
        listHolidayID.setValue("Select Holiday ID");


        listBuildingID2.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            Buildings selectedBuilding = con
                                    .getBuildingByName(Integer
                                            .parseInt(listAllBuildings[(int) newValue]));

                            List<Rooms> listGetRoomByBuilding = null;
                            try{
                                listGetRoomByBuilding = con.getRoomsByBuilding(selectedBuilding.getBuilding_number());
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            String[] listRoomsByBuilding = new String[listGetRoomByBuilding
                                    .size() + 1];
                            listRoomsByBuilding[0] = "Select room";
                            int j = 1;
                            for (Rooms r : listGetRoomByBuilding) {
                                listRoomsByBuilding[j] = "" + r.getRoomId();
                                j++;
                            }
                            listRoomID.setItems(FXCollections
                                    .observableArrayList(listRoomsByBuilding));
                            listRoomID.setValue("Select room");


                        } catch (IOException e){
                            e.printStackTrace();
                            System.out.println("No selected building");
                        }


                    }
                });

        listBuildingID1.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            Buildings selectedBuilding = con.getBuildingByName(Integer
                                    .parseInt(listAllBuildings[(int) newValue]));



                            List<Menus> listAllMenus = null;
                            try{
                                listAllMenus = con.getMenus();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            String[] listMenusByBuilding = new String[listAllMenus.size() + 1];
                            listMenusByBuilding[0] = "Select Menu";

                            int j = 1;
                            for(Menus m : listAllMenus){
                                if(m.getBuildingNumber() == selectedBuilding.getBuilding_number()){
                                    listMenusByBuilding[j] = m.getDishName();
                                    j++;
                                }

                            }
                            listMenus.setItems(FXCollections
                                    .observableArrayList(listMenusByBuilding));


                            List<Dishes> listAllDishes = null;
                            try{
                                listAllDishes = con.getDishes();
                            } catch (IOException e){
                                e.printStackTrace();
                            }

                            String[] listDishesByBuilding = new String[listAllDishes.size() + 1];
                            listDishesByBuilding[0] = "Select Dish";

                            int k = 1;
                            for(Dishes d : listAllDishes){
                                listDishesByBuilding[k] = d.getName();
                                k++;
                            }

                            listDishes.setItems(FXCollections.observableArrayList(listDishesByBuilding));






                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("No selected building");
                        }
                    }
                });
    }

    public void goBack(ActionEvent event) throws IOException {
        HelperController helperController = new HelperController();
        helperController.loadNextScene("/MainAdminScene.fxml", mainScreen);
    }

    public void deleteBuilding(Event event) throws IOException{

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if(listBuildingID.getValue().toString().equals("Select building")){
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        con.deleteBuildingAdmin(Integer.parseInt(listBuildingID.getValue().toString()));

        System.out.print("Building deleted");

        HelperController h = new HelperController();
        h.loadNextScene("/AdminDeleteView.fxml", mainScreen);
    }


    public void deleteRoom(Event event) throws IOException{

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if(listBuildingID2.getValue().toString().equals("Select building")){
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if(listRoomID.getValue().toString().equals("Select room")){
            exception.setText("Please select room.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        con.deleteRoomAdmin(listRoomID.getValue().toString());

        System.out.print("Room deleted");

        HelperController h = new HelperController();
        h.loadNextScene("/AdminDeleteView.fxml", mainScreen);

    }

    public void deleteHoliday(Event event) throws IOException{

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if(listHolidayID.getValue().toString().equals("Select holiday ID")){
            exception.setText("Please select holiday.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        con.deleteHolidaysAdmin(Integer.parseInt(listHolidayID.getValue().toString()));

        System.out.print("Holiday deleted");

        HelperController h = new HelperController();
        h.loadNextScene("/AdminDeleteView.fxml", mainScreen);
    }


    public void deleteDish(Event event) throws IOException{

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if(listDishes.getValue().toString().equals("Select dish")){
            exception.setText("Please select dish.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        con.deleteDishAdmin(listDishes.getValue().toString());

        System.out.print("Dish deleted");

        HelperController h = new HelperController();
        h.loadNextScene("/AdminDeleteView.fxml", mainScreen);


    }

    public void deleteMenu(Event event) throws IOException{

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if(listBuildingID1.getValue().toString().equals("Select building")){
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }
        if(listMenus.getValue().toString().equals("Select menu")){
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(670);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }


        con.deleteMenuAdmin(Integer.parseInt(listBuildingID1.getValue().toString()), listMenus.getValue().toString());

        System.out.print("Menu deleted");

        HelperController h = new HelperController();
        h.loadNextScene("/AdminDeleteView.fxml", mainScreen);

    }

}