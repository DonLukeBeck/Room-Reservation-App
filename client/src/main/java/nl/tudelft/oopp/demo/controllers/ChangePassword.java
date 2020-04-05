package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ChangePassword {

    @FXML
    PasswordField oldPasswordField;
    @FXML
    PasswordField newPasswordField;
    @FXML
    PasswordField confirmNewPasswordField;

    /**
     * Changes the password.
     */
    public void changePassword() {
        if (oldPasswordField.getText().isBlank() || oldPasswordField.getText().isBlank()
            || oldPasswordField.getText().isBlank()) {
            return;
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
