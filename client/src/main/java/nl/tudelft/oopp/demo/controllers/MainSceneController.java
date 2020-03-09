package nl.tudelft.oopp.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import nl.tudelft.oopp.demo.communication.ServerCommunication;


public class MainSceneController {
    @FXML
    private javafx.scene.control.Button button1;

    private static String user;

    public static String getUser(){
        return user;
    }

    @FXML
    private javafx.scene.control.Button button2;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;

    ServerCommunication con = new ServerCommunication();

    public void logIn(ActionEvent event) throws IOException, InterruptedException {
        if(pass.getText().isBlank() || username.getText().isBlank()){
            return;
        }

        user = username.getText();
        String password = pass.getText();

        String logIn =  con.logIn(user, password);

        Stage stage1 = (Stage) button1.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
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
