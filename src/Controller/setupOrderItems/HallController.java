package Controller.setupOrderItems;

import DB.DbConnection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HallController implements Initializable {

    public TextField txthallName;
    public TextField txthallDes;
    public ComboBox cmbHid;
    public TextField txthallid;
    @FXML ImageView ImageView2;
    int count;

    public void slideshow(){
        ArrayList<Image> images =new ArrayList<Image>();
        images.add(new Image("/Assets/Hall/1.jpg"));
        images.add(new Image("/Assets/Hall/2.jpg"));


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            ImageView2.setImage(images.get(count));
            count++;
            if (count == 2){
                count = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slideshow();
    }

    public void hallSaveonAction(ActionEvent actionEvent) {
        String id = txthallid.getText();
        String name = txthallName.getText();
        String Des = txthallDes.getText();

        try {
            Connection con = DbConnection.getInstance().getConnection();
            Statement stm = con.createStatement();
            String query = "INSERT INTO Hall VALUES ( "+"'"+id+"'"+","+"'"+name+"'"+","+"'"+Des+"'"+")";

            if (stm.executeUpdate(query)>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void hallDeleteonAction(ActionEvent actionEvent) {
    }

    public void hallnewIdonAction(ActionEvent actionEvent) {
        txthallid.setText(String.valueOf(generateNewId()));
        txthallName.clear();
        txthallDes.clear();
    }

    private String generateNewId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT Hall_Id FROM Hall ORDER BY Hall_Id DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("Hall_Id");
                int newCarId = Integer.parseInt(id.replace("H", "")) + 1;
                return String.format("H%03d", newCarId);
            } else {
                return "H001";
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
