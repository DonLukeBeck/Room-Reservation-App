package nl.tudelft.oopp.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;

import java.io.IOException;
import java.net.URL;


public class MainSceneController {
    @FXML
    private javafx.scene.control.Button button1;

    @FXML
    private javafx.scene.control.Button button2;

    @FXML
    private TextField username;

    @FXML
    private TextField pass;



    public void buttonLogIn(ActionEvent event) throws IOException, InterruptedException {
//        String user = username.getText();
//        String pas = pass.getText();
//
//        boolean verifyUser = ServerCommunication.login(user, pas);
//        System.out.println(verifyUser);

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

    public void SignUp(ActionEvent event) throws IOException, InterruptedException {
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
