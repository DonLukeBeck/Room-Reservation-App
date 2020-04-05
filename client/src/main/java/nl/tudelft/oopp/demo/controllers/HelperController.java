package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;

public class HelperController {
    ServerCommunication con = new ServerCommunication();

    /**
     * Using Insertion sort to sort the dates in List of holidays.
     *
     * @param list list of holidays
     * @return sorted list of holidays
     */

    public static List<RoomReservationMenu.HolidayTuple>
            insertionSort(List<RoomReservationMenu.HolidayTuple> list) {

        int n = list.size();
        for (int i = 1; i < n; i++) {
            int key = list.get(i).getHolidayDay();
            RoomReservationMenu.HolidayTuple keyTuple = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getHolidayDay() > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, keyTuple);
        }
        List<RoomReservationMenu.HolidayTuple> newList = new ArrayList<>();
        for (RoomReservationMenu.HolidayTuple e : list) {
            if (!newList.contains(e)) {
                newList.add(e);
            }
        }
        return newList;
    }

    /**
     * Using Insertion sort to sort the dates in List of holidays for bikes.
     *
     * @param list list of holidays
     * @return sorted list of holidays
     */
    public static List<BikeReservationMenu.HolidayTuple>
            insertionSortBikes(List<BikeReservationMenu.HolidayTuple> list) {

        int n = list.size();
        for (int i = 1; i < n; i++) {
            int key = list.get(i).getHolidayDay();
            BikeReservationMenu.HolidayTuple keyTuple = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getHolidayDay() > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, keyTuple);
        }
        List<BikeReservationMenu.HolidayTuple> newList = new ArrayList<>();
        for (BikeReservationMenu.HolidayTuple e : list) {
            if (!newList.contains(e)) {
                newList.add(e);
            }
        }

        return list;
    }

    /**
     * Method to get all timeslots.
     *
     * @return List of all timeslots
     */
    public String[] getAllTimeSlots() {
        String[] list = new String[48];
        int j = 0;
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                list[j] = "0" + i + ":00";
                j++;
                list[j] = "0" + i + ":30";
                j++;
            } else {
                list[j] = i + ":00";
                j++;
                list[j] = i + ":30";
                j++;
            }
        }
        return list;
    }

    /**
     * Method to load next scene from a given scene.
     *
     * @param path   Path to take from current scene
     * @param scene1 Current scene
     * @throws IOException Exception if can't find next scene to load
     */
    public void loadNextScene(String path, AnchorPane scene1) throws IOException {
        Stage stage1 = (Stage) scene1.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource(path);
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage1.setScene(new Scene(root));
        stage1.setTitle("Room Reservation App");
        stage1.getIcons().add(new Image("images/favicon.png"));
        stage1.show();
    }

    public void exit(AnchorPane scene1) throws IOException {
        Stage stage1 = (Stage) scene1.getScene().getWindow();
        stage1.close();
    }

    /**
     * Method to logout user.
     *
     * @param scene1 Scene from which to log out
     * @throws IOException Exception if can't find main scene page
     */
    public void logOut(AnchorPane scene1) throws IOException {
        Stage stage1 = (Stage) scene1.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage1.setScene(new Scene(root));
        stage1.setTitle("Room Reservation App");
        stage1.getIcons().add(new Image("images/favicon.png"));
        stage1.show();
    }

    /**
     * Method to load user profile.
     *
     * @param scene1 Current scene
     * @throws IOException Exception if you can't find user page
     */
    public void userProfile(AnchorPane scene1) throws IOException {
        Stage stage1 = (Stage) scene1.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserPage.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage1.setScene(new Scene(root));
        stage1.setTitle("Room Reservation App");
        stage1.getIcons().add(new Image("images/favicon.png"));
        stage1.show();
    }

    /**
     * Method to add role.
     *
     * @param pane the pane where the Label should be added
     * @param role Role to be added
     */
    public void addRole(Pane pane, String role) {
        Label label = new Label(role);
        label.setLayoutX(88);
        label.setLayoutY(52);
        label.setFont(Font.font("System", FontWeight.BOLD, 16));
        pane.getChildren().add(label);
    }

    /**
     * Loading elements on chosen Pane.
     *
     * @param sidePane chosen Pane
     */
    public void loadSidePane(Pane sidePane) {
        List<Buildings> buildingsList = new ArrayList<>();
        int layoutY = 220;
        try {
            buildingsList = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Buildings e : buildingsList) {
            int capsCount = 0;
            int changeInPosition = 0;
            String buildingName = "";
            for (Character a : e.getName().toCharArray()) {
                if (a.equals('(')) {
                    break;
                }
                if (Character.isUpperCase(a)) {
                    capsCount++;
                    buildingName = buildingName + a;
                }
            }
            if (capsCount == 1 || capsCount == 0) {
                buildingName = e.getName();
                changeInPosition = 40;
            } else {
                buildingName = "Faculty of " + buildingName;
            }

            Label sideBuilding = new Label("-" + buildingName);
            sideBuilding.setLayoutX(56);
            sideBuilding.setLayoutY(layoutY + 28);
            sideBuilding.setFont(Font.font("System", FontWeight.BOLD, 14));
            sideBuilding.setTextFill(Color.valueOf("white"));
            layoutY = layoutY + 28;
            sidePane.getChildren().add(sideBuilding);
        }
    }
}
