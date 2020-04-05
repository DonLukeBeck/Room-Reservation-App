package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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


public class SignUpController implements Initializable {
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


    private static Label text;
    /**
     * Method to sign up.
     *
     * @param event Clicking sign up
     * @throws IOException          Exception if can'f find main scene
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

        if (check) {
            boolean signUp = con.signUp(username, firstPass, role);
            if (!signUp) {
                text.setText("User is already registered with this NetID!");
                text.setVisible(true);
                return;
            }
        } else {
            System.out.println("Pass do not match!");
            text.setText("Passwords do not match!");
            text.setVisible(true);
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
        stage.getIcons().add(new Image("images/favicon.png"));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text = new Label(" ");
        text.setTextFill(Color.web("red"));
        text.setVisible(false);
        text.setLayoutX(coPass.getLayoutX());
        text.setLayoutY(coPass.getLayoutY() + 35);
        pane.getChildren().add(text);
    }
}
