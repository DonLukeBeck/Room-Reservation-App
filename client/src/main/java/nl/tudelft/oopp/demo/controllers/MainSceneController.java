package nl.tudelft.oopp.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;

public class MainSceneController {

     /**
     * Handles clicking the button.
     */

    private Stage primaryStage;
    private Stage loginStage;
    private Stage registerStage;

    public void buttonRegisterClicked(ActionEvent actionEvent) {
        try {
            //get event from starting application stage
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            this.primaryStage = stage;
            stage.close();



            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/RegisterScene.fxml")));
            this.registerStage = new Stage();
            this.registerStage.setWidth(600);
            this.registerStage.setHeight(600);
            this.registerStage.setScene(scene);
            this.registerStage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void buttonLoginClicked(ActionEvent actionEvent) throws IOException {
        try {
            //get event from starting application stage
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            this.primaryStage = stage;
            stage.close();



            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/LoginScene.fxml")));
            this.loginStage = new Stage();
            this.loginStage.setWidth(600);
            this.loginStage.setHeight(600);
            this.loginStage.setScene(scene);
            this.loginStage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void buttonReturnClicked(ActionEvent actionEvent) {
        try {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/mainApp29Scene.fxml")));
            stage.setWidth(300);
            stage.setHeight(300);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
