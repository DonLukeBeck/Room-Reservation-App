package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;


public class SignUpController {
    @FXML
    public javafx.scene.control.Button button3;

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

    @SuppressWarnings({"checkstyle:WhitespaceAround", "checkstyle:MethodName", "checkstyle:MissingJavadocMethod"})
    public void signUp(ActionEvent event) throws IOException, InterruptedException {
        String firstPass = pass.getText();
        String secondPass = coPass.getText();
        String username = user.getText();
        String role = "student";
        if(checkBox.isSelected()){
            role = "teacher";
        }


        boolean check = false;

        if (firstPass.equals(secondPass)) {
            check = true;
        }
        System.out.println(check);

        if (check) {
           boolean server =  ServerCommunication.signUp(username, firstPass, role);
        } else {
            System.out.println("Pass do not match!");
            Label text = new Label("Passwords do not match!");
            text.setTextFill(Color.web("red"));
            text.setVisible(true);
            text.setLayoutX(coPass.getLayoutX());
            text.setLayoutY(coPass.getLayoutY()+28);
            pane.getChildren().add(text);

            return;
        }

        Stage stage1 = (Stage) button3.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}