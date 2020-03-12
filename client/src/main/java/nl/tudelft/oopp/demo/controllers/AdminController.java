package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Buildings;

public class AdminController implements Initializable {
    ServerCommunication con = new ServerCommunication();

    @FXML
    private javafx.scene.control.Button add;

    @FXML
    private javafx.scene.control.Button goBack;

    @FXML
    private ComboBox listOpen;

    @FXML
    private ComboBox listClose;

    @FXML
    private ChoiceBox listBuilding;

    public void goToAdminAdd(ActionEvent event) throws IOException {

        Stage stage1 = (Stage) add.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/AdminView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] list = new String[48];
        int j = 0;
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                list[j] = "0" + i + ":00";
                j++;
                list[j] = "0" + i + ":30";
                j++;
            } else {
                list[j] = i + ":00";
                j++;
                list[j] = i + ":30";
                j++;
            }
        }
        for (String e : list) {
            System.out.println(e);
        }
        List<Buildings> listGetBuildings = null;
        try {
            listGetBuildings = con.getBuildings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        j = 0;
        String[] listAllBuildings = new String[listGetBuildings.size()];
        for (Buildings t : listGetBuildings) {
            listAllBuildings[j] = "" + t.getBuilding_number();
            j++;
        }
        listOpen.setItems(FXCollections.observableArrayList(list));
        listClose.setItems(FXCollections.observableArrayList(list));
        listBuilding.setItems(FXCollections.observableArrayList(listAllBuildings));
    }

    public void goBack(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) goBack.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainAdminScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}