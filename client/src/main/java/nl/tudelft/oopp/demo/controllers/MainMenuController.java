package nl.tudelft.oopp.demo.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;



public class MainMenuController {
    public void CampusMap(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/CampusMap.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private javafx.scene.control.ScrollPane scene1;

    //@FXML
   // private ImageView B36;

    public void GoToMenuReservation(Event event) throws IOException {

        String str = event.getSource().toString();
        String[] temp = str.split(" ");
        String newTemp = "";
        for(int i = 0; i < temp.length; i++){
            if(temp[i].contains("id=")){
                newTemp = temp[i];
            }
        }
       // System.out.println(str);
        String ArrId[] = newTemp.split("=");
        String id = ArrId[1];
        id = id.substring(0, id.length()-1);
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
