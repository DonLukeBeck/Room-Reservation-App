package nl.tudelft.oopp.demo.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import java.io.IOException;
import java.net.URL;



public class SignUpController {
    @FXML
    public javafx.scene.control.Button button3;

//    @FXML
//    private TextField text;

    public void SignUP(ActionEvent event) throws IOException, InterruptedException {
        Stage stage1 = (Stage) button3.getScene().getWindow();
        stage1.close();
        //String k = text.getText();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}