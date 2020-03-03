package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainMenuController {
    private static String id;


    public static String getId() {
        return id;
    }

    @FXML
    private javafx.scene.control.ScrollPane scene1;

    public void CampusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void GoToMenuReservation(Event event) throws IOException {

        String str = event.getSource().toString();
        String[] temp = str.split(" ");
        String newTemp = "";
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("id=")) {
                newTemp = temp[i];
            }
        }
        // System.out.println(str);
        String[] ArrId = newTemp.split("=");
        this.id = ArrId[1];
        id = id.substring(0, id.length() - 1);
        System.out.println(id);

        Stage stage1 = (Stage) scene1.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainReservationMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
