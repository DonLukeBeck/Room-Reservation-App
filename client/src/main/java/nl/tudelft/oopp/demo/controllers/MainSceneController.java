package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Users;


public class MainSceneController {
    private static String user;
    ServerCommunication con = new ServerCommunication();
    @FXML
    private javafx.scene.control.Button button1;
    @FXML
    private javafx.scene.control.Button button2;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;

    public static String getUser() {
        return user;
    }

    public void logIn(ActionEvent event) throws IOException, InterruptedException {
        if (pass.getText().isBlank() || username.getText().isBlank()) {
            return;
        }

        user = username.getText();
        String password = pass.getText();

        Users userLogged = con.logIn(user, password);

        // verify if user is logged in
        if (!userLogged.getNetid().isEmpty()) {
            //if he s logged in, verify what role he has

            if (userLogged.getRole().equals("admin")) {
                //redirect to admin page

                Stage stage1 = (Stage) button1.getScene().getWindow();
                stage1.close();

                FXMLLoader loader = new FXMLLoader();
                URL xmlUrl = getClass().getResource("/MainAdminScene.fxml");
                loader.setLocation(xmlUrl);
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                return;
            } else if (userLogged.getRole().equals("student")) {
                //redirect to student page
                Stage stage1 = (Stage) button1.getScene().getWindow();
                stage1.close();

                FXMLLoader loader = new FXMLLoader();
                URL xmlUrl = getClass().getResource("/MainMenu.fxml");
                loader.setLocation(xmlUrl);
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                return;
            } else {
                //redirect to teacher page
                return;
            }
        } else {
            //else show error message - user not in db
            return;
        }

    }

    public void register(ActionEvent event) throws IOException, InterruptedException {
        Stage stage1 = (Stage) button2.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/SignUpScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
