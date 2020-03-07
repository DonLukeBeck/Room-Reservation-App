package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.entities.Rooms;


public class RoomMenuController implements Initializable {
    ServerCommunication con = new ServerCommunication();

    private static String room_id;

    public static List<Rooms> rooms;

    public static String getId() {
        return room_id;
    }
    
    @FXML
    private AnchorPane pane1;
    @FXML
    private Rectangle rect1;
    @FXML
    private javafx.scene.control.ScrollPane scene1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String buildingId = MainMenuController.getId();
        int builId = Integer.parseInt(buildingId);

        List<Rooms> allrooms = null;
        try {
            allrooms = con.getRooms();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rooms = new ArrayList<>();
        for (int i = 0; i < allrooms.size(); i++) {
            if(allrooms.get(i).getAssociatedBuilding() == builId){
                rooms.add(allrooms.get(i));
            }
        }

        System.out.println("running");
        for (int j = 0; j < rooms.size(); j++) {
            Rectangle last = null;
            String id1 = "";
            for (Node e : pane1.getChildren()) {
                if (e instanceof Rectangle) {
                    id1 = e.getId();
                    last = (Rectangle) e;
                }
            }

            if (id1.contains("A")) {
                Rectangle box = new Rectangle(188, 136);

                box.arcHeightProperty().setValue(30.0);
                box.arcWidthProperty().setValue(30.0);
                box.layoutXProperty().setValue(344);
                box.layoutYProperty().setValue(last.layoutYProperty().getValue());
                box.fillProperty().setValue(Color.valueOf("white"));
                box.setStroke(Color.valueOf("#00A6D6"));
                box.strokeWidthProperty().setValue(2);
                box.setVisible(true);
                box.setId("B"+j);
                box.setOnMouseClicked(event -> {
                    try {
                        roomChosen(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pane1.getChildren().add(box);

                Label roomId = new Label("Room ID:");
                pane1.getChildren().add(roomId);
                roomId.setLayoutY(box.layoutYProperty().getValue() + 11);
                roomId.setLayoutX(394);
                roomId.setFont(Font.font("Arial Rounded MT Bold", 20));

                Label cap = new Label("Capacity:");
                pane1.getChildren().add(cap);
                cap.setLayoutY(box.layoutYProperty().getValue() + 70);
                cap.setLayoutX(390);
                cap.setFont(Font.font("Arial Rounded MT Bold", 18));

                Label roomId1 = new Label(rooms.get(j).getRoom_id());
                pane1.getChildren().add(roomId1);
                roomId1.setLayoutY(box.layoutYProperty().getValue() + 40);
                roomId1.setLayoutX(390);
                roomId1.setFont(Font.font("Arial Rounded MT Bold", 20));

                Label cap1 = new Label(rooms.get(j).getCapacity()+"");
                pane1.getChildren().add(cap1);
                cap1.setLayoutY(box.layoutYProperty().getValue() + 100);
                cap1.setLayoutX(422);
                cap1.setFont(Font.font("Arial Rounded MT Bold", 18));


            }
            if (id1.contains("B")) {
                Rectangle box = new Rectangle(188, 136);

                box.arcHeightProperty().setValue(30.0);
                box.arcWidthProperty().setValue(30.0);
                box.layoutXProperty().setValue(630);
                box.layoutYProperty().setValue(last.layoutYProperty().getValue());
                box.fillProperty().setValue(Color.valueOf("white"));
                box.setStroke(Color.valueOf("#00A6D6"));
                box.strokeWidthProperty().setValue(2);
                box.setVisible(true);
                box.setId("C"+j);
                box.setOnMouseClicked(event -> {
                    try {
                        roomChosen(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pane1.getChildren().add(box);

                Label roomId = new Label("Room ID:");
                pane1.getChildren().add(roomId);
                roomId.setLayoutY(box.layoutYProperty().getValue() + 11);
                roomId.setLayoutX(680);
                roomId.setFont(Font.font("Arial Rounded MT Bold", 20));

                Label cap = new Label("Capacity:");
                pane1.getChildren().add(cap);
                cap.setLayoutY(box.layoutYProperty().getValue() + 70);
                cap.setLayoutX(676);
                cap.setFont(Font.font("Arial Rounded MT Bold", 20));

                Label roomId1 = new Label(rooms.get(j).getRoom_id());
                pane1.getChildren().add(roomId1);
                roomId1.setLayoutY(box.layoutYProperty().getValue() + 40);
                roomId1.setLayoutX(676);
                roomId1.setFont(Font.font("Arial Rounded MT Bold", 18));

                Label cap1 = new Label(rooms.get(j).getCapacity()+"");
                pane1.getChildren().add(cap1);
                cap1.setLayoutY(box.layoutYProperty().getValue() + 100);
                cap1.setLayoutX(708);
                cap1.setFont(Font.font("Arial Rounded MT Bold", 18));
            }
            if (id1.contains("C")) {
                Rectangle box = new Rectangle(188, 136);

                box.arcHeightProperty().setValue(30.0);
                box.arcWidthProperty().setValue(30.0);
                box.layoutXProperty().setValue(58);
                box.layoutYProperty().setValue(last.layoutYProperty().getValue() + 176);
                box.fillProperty().setValue(Color.valueOf("white"));
                box.setStroke(Color.valueOf("#00A6D6"));
                box.strokeWidthProperty().setValue(2);
                box.setVisible(true);
                box.setId("A"+j);
                box.setOnMouseClicked(event -> {
                    try {
                        roomChosen(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pane1.getChildren().add(box);

                Label roomId = new Label("Room ID:");
                pane1.getChildren().add(roomId);
                roomId.setLayoutY(box.layoutYProperty().getValue() + 11);
                roomId.setLayoutX(108.0);
                roomId.setFont(Font.font("Arial Rounded MT Bold", 20));

                Label cap = new Label("Capacity:");
                pane1.getChildren().add(cap);
                cap.setLayoutY(box.layoutYProperty().getValue() + 70);
                cap.setLayoutX(104);
                cap.setFont(Font.font("Arial Rounded MT Bold", 20));

                Label roomId1 = new Label(rooms.get(j).getRoom_id());
                pane1.getChildren().add(roomId1);
                roomId1.setLayoutY(box.layoutYProperty().getValue() + 40);
                roomId1.setLayoutX(104);
                roomId1.setFont(Font.font("Arial Rounded MT Bold", 18));

                Label cap1 = new Label(rooms.get(j).getCapacity()+"");
                pane1.getChildren().add(cap1);
                cap1.setLayoutY(box.layoutYProperty().getValue() + 100);
                cap1.setLayoutX(136);
                cap1.setFont(Font.font("Arial Rounded MT Bold", 18));
            }

        }

    }

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
    private javafx.scene.control.Button ReserveScene;

    public void GoBack(Event event) throws IOException {
        Stage stage1 = (Stage) ReserveScene.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void roomChosen(Event event) throws IOException {
        String str = event.getSource().toString();
        String[] temp = str.split(" ");
        String newTemp = "";
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("id=")) {
                newTemp = temp[i];
            }
        }
        String[] ArrId = newTemp.split("=");
        String temp2 = ArrId[1];
        temp2 = temp2.substring(1,temp2.length() - 1);

        int roomIndex = Integer.parseInt(temp2);

        this.room_id = this.rooms.get(roomIndex).getRoom_id();

        String buildingId = MainMenuController.getId().substring(1);

        Stage stage1 = (Stage) ReserveScene.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/ReservationRoom.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
