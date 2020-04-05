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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.tudelft.oopp.demo.communication.AdminServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Holidays;
import nl.tudelft.oopp.demo.entities.Menus;
import nl.tudelft.oopp.demo.entities.Rooms;

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
    private ChoiceBox listRoomsID;

    @FXML
    private ChoiceBox listDishes;

    @FXML
    private ChoiceBox listHolidayID;

    @FXML
    private ChoiceBox listMenus;

    private int selectedHoliday;

    /**
     * Method to go to admin delete view.
     * @param event Clicking on delete
     * @throws IOException Exception if can't find admin edit view scene
     */

    public void goToAdminDelete(ActionEvent event) throws IOException {
        HelperController helper = new HelperController();
        helper.loadNextScene("/AdminDeleteView.fxml", mainScreen);
    }


    /**
     *Method to initialize.
     * @param location Location of the picture
     * @param resources Resource Bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Buildings> listGetBuildings = null;
        int j = 0;
        try {
            listGetBuildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String[] listAllBuildings = new String[listGetBuildings.size() + 1];
        listAllBuildings[0] = "Select Building";
        j = 1;
        for (Buildings t : listGetBuildings) {
            listAllBuildings[j] = "" + t.getBuilding_number();
            j++;
        }

        String[] listAllRooms = new String[2];
        listAllRooms[0] = "Select Room";
        listAllRooms[1] = "First select building";

        List<Holidays> listGetHolidays = null;
        try {
            listGetHolidays = con.getHolidays();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] listHolidaysComments = new String[listGetHolidays.size() + 1];
        listHolidaysComments[0] = "Select Holiday";

        j =  1;
        for (Holidays h : listGetHolidays) {
            listHolidaysComments[j] = "" + h.getComments();
            j++;
        }

        String[] listAllMenus = new String[2];
        listAllMenus[0] = "Select Menu";
        listAllMenus[1] = "First select building";

        String[] listAllDishes = new String[2];
        listAllDishes[0] = "Select Dish";
        listAllDishes[1] = "First select building";



        listBuildingID.setItems(FXCollections.observableArrayList(listAllBuildings));
        listBuildingID1.setItems(FXCollections.observableArrayList(listAllBuildings));
        listBuildingID2.setItems(FXCollections.observableArrayList(listAllBuildings));
        listHolidayID.setItems(FXCollections.observableArrayList(listHolidaysComments));
        listRoomsID.setItems(FXCollections.observableArrayList(listAllRooms));
        listDishes.setItems(FXCollections.observableArrayList(listAllDishes));
        listMenus.setItems(FXCollections.observableArrayList(listAllMenus));

        listBuildingID.setValue("Select Building");
        listBuildingID1.setValue("Select Building");
        listBuildingID2.setValue("Select Building");
        listHolidayID.setValue("Select Holiday");
        listRoomsID.setValue("Select Room");
        listDishes.setValue("Select Dish");
        listMenus.setValue("Select Menu");


        listBuildingID2.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {
                        try {
                            Buildings selectedBuilding = con
                                    .getBuildingByName(Integer
                                            .parseInt(listAllBuildings[(int) newValue]));

                            List<Rooms> listGetRoomByBuilding = null;
                            try {
                                listGetRoomByBuilding = con
                                        .getRoomsByBuilding(selectedBuilding
                                                .getBuilding_number());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String[] listRoomsByBuilding = new String[listGetRoomByBuilding
                                    .size() + 1];
                            listRoomsByBuilding[0] = "Select Room";
                            int j = 1;
                            for (Rooms r : listGetRoomByBuilding) {
                                listRoomsByBuilding[j] = "" + r.getRoomId();
                                j++;
                            }
                            listRoomsID.setItems(FXCollections
                                    .observableArrayList(listRoomsByBuilding));
                            listRoomsID.setValue("Select Room");


                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("No selected building");
                        }


                    }
                });

        listBuildingID1.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {
                        try {
                            Buildings selectedBuilding = con.getBuildingByName(Integer
                                    .parseInt(listAllBuildings[(int) newValue]));



                            List<Menus> listAllMenus = null;
                            try {
                                listAllMenus = con.getMenus();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String[] listMenusByBuilding = new String[listAllMenus.size() + 1];
                            listMenusByBuilding[0] = "Select Menu";

                            int j = 1;
                            for (Menus m : listAllMenus) {
                                if (m.getBuildingNumber() == selectedBuilding
                                        .getBuilding_number()) {
                                    listMenusByBuilding[j] = m.getDishName();
                                    j++;
                                }

                            }
                            listMenus.setItems(FXCollections
                                    .observableArrayList(listMenusByBuilding));


                            List<Dishes> listAllDishes = null;
                            try {
                                listAllDishes = con.getDishes();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            String[] listDishesByBuilding = new String[listAllDishes.size() + 1];
                            listDishesByBuilding[0] = "Select Dish";

                            int k = 1;
                            for (Dishes d : listAllDishes) {
                                listDishesByBuilding[k] = d.getName();
                                k++;
                            }

                            listDishes
                                    .setItems(FXCollections
                                            .observableArrayList(listDishesByBuilding));
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("No selected building");
                        }
                    }
                });

        listHolidayID.getSelectionModel()
                .selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {
                        try {
                            selectedHoliday = (int)newValue - 1;
                            List<Holidays> listAllHolidays = con.getHolidays();
                            selectedHoliday = listAllHolidays.get(selectedHoliday).getHolidaysID();

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

    /**
     * Method for admin to delete a building.
     * @param event Clicking delete building
     * @throws IOException Exception if can't find admin edit view scene
     */
    public void deleteBuilding(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (listBuildingID.getValue().toString().equals("Select Building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(38);
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


    /**
     * Method for admin to delete a room.
     * @param event Clicking delete room
     * @throws IOException Exception if can't find admin delete view scene
     */
    public void deleteRoom(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (listBuildingID2.getValue().toString().equals("Select Building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(38);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        if (listRoomsID.getValue().toString().equals("Select Room")) {
            exception.setText("Please select room.");
            exception.setLayoutY(120);
            exception.setLayoutX(38);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        con.deleteRoomAdmin(listRoomsID.getValue().toString());

        System.out.print("Room deleted");

        HelperController h = new HelperController();
        h.loadNextScene("/AdminDeleteView.fxml", mainScreen);

    }

    /**
     * Method for admin to delete a holiday.
     * @param event Clicking delete holiday
     * @throws IOException Exception if can't find admin delete view
     */
    public void deleteHoliday(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (listHolidayID.getValue().toString().equals("Select Holiday")) {
            exception.setText("Please select holiday.");
            exception.setLayoutY(120);
            exception.setLayoutX(38);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }

        con.deleteHolidaysAdmin(selectedHoliday);

        HelperController h = new HelperController();
        h.loadNextScene("/AdminDeleteView.fxml", mainScreen);
    }


    /**
     * Method for admin to delete a dish.
     * @param event Clicking delete dish
     * @throws IOException Exception if can't find admin delete view
     */
    public void deleteDish(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (listDishes.getValue().toString().equals("Select Dish")) {
            exception.setText("Please select dish.");
            exception.setLayoutY(120);
            exception.setLayoutX(38);
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

    /**
     * Method for admin to delete a menu.
     * @param event Clicking delete menu
     * @throws IOException Exception if can't find admin delete view
     */
    public void deleteMenu(Event event) throws IOException {

        Label exception = new Label();

        mainScreen.getChildren().add(exception);
        for (Node e : mainScreen.getChildren()) {
            if ((e instanceof Label) && (e.getId() != null) && e.getId().equals("Exception")) {
                ((Label) e).setText(" ");
            }
        }

        if (listBuildingID1.getValue().toString().equals("Select Building")) {
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(38);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }
        if (listMenus.getValue().toString().equals("Select Menu")) {
            exception.setText("Please select building.");
            exception.setLayoutY(120);
            exception.setLayoutX(38);
            exception.setTextFill(Color.valueOf("red"));
            exception.setFont(Font.font(20));
            exception.setId("Exception");
            return;
        }


        con
                .deleteMenuAdmin(Integer
                        .parseInt(listBuildingID1
                                .getValue().toString()), listMenus
                        .getValue().toString());

        System.out.print("Menu deleted");

        HelperController h = new HelperController();
        h.loadNextScene("/AdminDeleteView.fxml", mainScreen);

    }

}
