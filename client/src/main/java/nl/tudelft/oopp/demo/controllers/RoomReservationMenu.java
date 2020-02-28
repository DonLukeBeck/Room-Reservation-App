package nl.tudelft.oopp.demo.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

public class RoomReservationMenu {
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
        URL xmlUrl = getClass().getResource("/MainReservationMenu.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private AnchorPane Mon;

    @FXML
    private GridPane Grid;

    @FXML
    private ScrollPane Scroll;

    @FXML
    private void Calendarr(Event event) throws IOException {
        int i = 1;
        int flag = 0;
        //int col1 = 0;
       // int row1 = 0;

//        Integer column;
//        Integer roww;
//        AnchorPane[][] gridPaneNodes = new AnchorPane[6][4];
//        //System.out.println("Begin");
//        try {
//            for (Node e : Grid.getChildren()) {
//                //System.out.println("Begin2");
//                // System.out.print(child.toString());
//                column = GridPane.getColumnIndex(e);
//
//                if (column == null) col1 = 0;
//                else col1 = column.intValue();
//
//                //System.out.println(col1);
//                roww = GridPane.getRowIndex(e);
//                if (roww == null) row1 = 0;
//                else row1 = roww.intValue();
//
//                //System.out.println(col1 + "     " + row1);
//                gridPaneNodes[col1][row1] = (AnchorPane) e;
//
//            }
//        }
//        catch(Exception e){
//           System.out.println("ok1");
//        }
//        System.out.println("ok");
//        Calendar c = Calendar.getInstance();
//
//        for (int row = 0; row < 5; row++) {
//            for(int col = 0; col < 6; col++) {
//                //try {
//                    Node e = gridPaneNodes[col][row];
//                    e = (AnchorPane) e;
//                    c.set(Calendar.DAY_OF_MONTH, i);
//
//                    String time1[] = (c.getTime() + "").split(" ");
//                    String time = time1[0];
//                    //System.out.println(time);
//                    //System.out.println(e);
////                    if (flag == 0) {
////                        if (e.getId().equals(time)) {
////                            flag++;
////                            System.out.println(e.getId());
////                            Text text = new Text(time);
////                            text.setX(5);
////                            text.setY(10);
////
////                            i++;
////                            ((AnchorPane) e).getChildren().add(text);
////                        }
//                    //} else {
//                        System.out.println(e.getId());
//                        Text text = new Text(time);
//                        text.setX(5);
//                        text.setY(10);
//                        i++;
//                        ((AnchorPane) e).getChildren().add(text);
//                    //}
//                    //System.out.println(flag);
//                }
//                //catch (Exception e){
//                  //  System.out.print("ok");
//                //}
//            }
//        }
       // try {
            Calendar c = Calendar.getInstance();

            c.set(Calendar.MONTH, Calendar.MARCH);
            int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            int day = 1;
            for (Node e : Grid.getChildren()) {
                c.set(Calendar.DAY_OF_MONTH, i);

                String time1[] = (c.getTime() + "").split(" ");
                String time = time1[0];
                if (flag == 0) {
                    if (e.getId().equals(time)) {
                        flag++;
                        // System.out.println(c.getTime());
                        Text text = new Text(day+"");
                        text.setX(5);
                        text.setY(115);
                        text.setRotate(-90);

                        i++;
                        //e = (AnchorPane) e;
                        ((AnchorPane) e).getChildren().add(text);
                        day++;
                    }
                } else {
                   // System.out.println(c.getTime());
                    Text text = new Text(day+"");
                    text.setX(5);
                    text.setY(115);
                    text.setRotate(-90);
                    i++;
                    days--;
                    if(days == 0) return;
                    System.out.println(i);
                    //e = (AnchorPane) e;
                    ((AnchorPane) e).getChildren().add(text);
                    day++;
                }
            }
        }
//        catch (Exception e){
//            return;
//        }
    }


