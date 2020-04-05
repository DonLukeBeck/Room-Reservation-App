package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
//import javafx.scene.layout.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.UserServerCommunication;

public class ChangePassword {

    @FXML
    private javafx.scene.control.PasswordField oldPasswordField;
    @FXML
    private javafx.scene.control.PasswordField newPasswordField;
    @FXML
    private javafx.scene.control.PasswordField confirmNewPasswordField;
    UserServerCommunication con = new UserServerCommunication();

    /**
     * Changes the password.
     */
    public void changePassword() {
        try {
            if (oldPasswordField.getText().isBlank() || newPasswordField.getText().isBlank()
                    || confirmNewPasswordField.getText().isBlank()) {
                return;
            }
        } catch (NullPointerException e) {
            return;
        }
        try {
            if (! newPasswordField.getText().equals(confirmNewPasswordField.getText())) {
                confirmNewPasswordField.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        new BorderWidths(0, 0, 2, 0))));
                return;
            }
            con.changePassword(oldPasswordField.getText(), newPasswordField.getText());
            System.out.println("Changing password");
            cancel();
        } catch (WrongPasswordException e) {
            oldPasswordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                    new BorderWidths(0, 0, 2, 0))));
        }
    }

    /**
     * Closes the stage.
     */
    public void cancel() {
        Stage stage1 = (Stage) oldPasswordField.getScene().getWindow();
        stage1.close();
    }
}
