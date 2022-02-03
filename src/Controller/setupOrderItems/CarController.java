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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarController implements Initializable {

    public TextField txtCname;
    public TextField txtCcost;
    public ComboBox cmbCid;
    public TextField txtpackageid;
    public TextField txtCarId;
    @FXML ImageView imageView4;
    int count;

    public void slideshow(){
        ArrayList<Image> images =new ArrayList<Image>();
        images.add(new Image("/Assets/Cars/1.jpg"));
        images.add(new Image("/Assets/Cars/2.jpg"));


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            imageView4.setImage(images.get(count));
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

    public void carSaveonAction(ActionEvent actionEvent) {
        String id = txtCarId.getText();
        String name = txtCname.getText();
        String pname = txtpackageid.getText();
        double cost = Double.parseDouble(txtCcost.getText());

        try {
            Connection con=DbConnection.getInstance().getConnection();
            Statement stm = con.createStatement();
            String query = "INSERT INTO Car VALUES ( "+"'"+id+"'"+","+"'"+pname+"'"+","+"'"+name+"'"+","+"'"+cost+"'"+")";

            if (stm.executeUpdate(query)>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private String generateNewId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT Car_Id FROM Car ORDER BY Car_Id DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("Car_Id");
                int newCarId = Integer.parseInt(id.replace("Cr", "")) + 1;
                return String.format("Cr%03d", newCarId);
            } else {
                return "Cr001";
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void carDeleteonAction(ActionEvent actionEvent) {
    }

    public void carnewIdonAction(ActionEvent actionEvent) {
        txtCarId.setText(generateNewId());
        txtpackageid.clear();
        txtCname.clear();
        txtCcost.clear();
    }

    public void CarSearchOnAction(ActionEvent actionEvent) {
    }
}
