package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.Rooms;


public class SignUpController {

    @FXML
    public javafx.scene.control.Button button3;
    UserServerCommunication con = new UserServerCommunication();
    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField coPass;

    @FXML
    private AnchorPane pane;

    @FXML
    private CheckBox checkBox;

    /**
     *Method to sign up.
     * @param event Clicking sign up
     * @throws IOException Exception if can'f find main scene
     * @throws InterruptedException Exception if execution is interrupted
     */
    public void signUp(ActionEvent event) throws IOException, InterruptedException {
        if (pass.getText().isBlank() || coPass.getText().isBlank() || user.getText().isBlank()) {
            return;
        }

        String firstPass = pass.getText();
        String secondPass = coPass.getText();
        String username = user.getText();
        String role = "student";
        if (checkBox.isSelected()) {
            role = "teacher";
        }


        boolean check = false;

        if (firstPass.equals(secondPass)) {
            check = true;
        }
        System.out.println(check);

        //Printing all buildings from database
        List<Buildings> buildings = con.getBuildings();
        for (int i = 0; i < buildings.size(); i++) {
            System.out.println(buildings.get(i).getBuilding_number());
            System.out.println(buildings.get(i).getName());
            System.out.println(buildings.get(i).getOpeningHours());
            System.out.println(buildings.get(i).getClosingHours());
        }
        System.out.println("******************Rooms");
        //Printing all rooms from database
        List<Rooms> rooms = con.getRooms();
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println(rooms.get(i).getRoomId());
            System.out.println(rooms.get(i).getChairs());
            System.out.println(rooms.get(i).getType());
            System.out.println(rooms.get(i).getAssociatedBuilding());
        }
        System.out.println("******************");
        //Printing all reservations from database
        List<Reservations> reservations = con.getReservations();
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println(reservations.get(i).getId());
            System.out.println(reservations.get(i).getUserReserving());
            System.out.println(reservations.get(i).getTimeslot());
            System.out.println(reservations.get(i).getDate());
            System.out.println(reservations.get(i).getRoomReserved());
            System.out.println(reservations.get(i).getBikeReserved());
            System.out.println(reservations.get(i).getDishOrdered());
        }

        if (check) {
            boolean signUp = con.signUp(username, firstPass, role);
        } else {
            System.out.println("Pass do not match!");
            Label text = new Label("Passwords do not match!");
            text.setTextFill(Color.web("red"));
            text.setVisible(true);
            text.setLayoutX(coPass.getLayoutX());
            text.setLayoutY(coPass.getLayoutY() + 28);
            pane.getChildren().add(text);

            return;
        }

        Stage stage1 = (Stage) button3.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("favicon.png"));
        stage.show();
    }
}
