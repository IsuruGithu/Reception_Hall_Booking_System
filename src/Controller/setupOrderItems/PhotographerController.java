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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PhotographerController implements Initializable {
    public AnchorPane photographerContext;
    public TextField txtname;
    public TextField txtpid;
    public TextField txtpcost;
    public ComboBox cmbid;
    public TextField txtPhid;
    @FXML ImageView imageView3;
    int count;

    public void slideshow(){
        ArrayList<Image> images =new ArrayList<Image>();
        images.add(new Image("/Assets/Photographer/1.jpg"));
        images.add(new Image("/Assets/Photographer/2.jpg"));


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            imageView3.setImage(images.get(count));
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

    public void psaveOnAction(ActionEvent actionEvent) {
        String id =  txtPhid.getText();
        String name = txtname.getText();
        String pname = txtpid.getText();
        double cost = Double.parseDouble(txtpcost.getText());

        try {
            Connection con= DbConnection.getInstance().getConnection();
            Statement stm = con.createStatement();
            String qury = "INSERT INTO Photographer VALUES ( "+"'"+id+"'"+","+"'"+pname+"'"+","+"'"+name+"'"+","+"'"+cost+"'"+")";

            if (stm.executeUpdate(qury)>0){
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

    public void pdeleteOnAction(ActionEvent actionEvent) {
    }

    public void pnewIdOnAction(ActionEvent actionEvent) {
        txtPhid.setText(String.valueOf(generateNewId()));
        txtpid.clear();
        txtname.clear();
        txtpcost.clear();
    }
    private String generateNewId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT Photographer_Id FROM Photographer ORDER BY Photographer_Id DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("Photographer_Id");
                int newCarId = Integer.parseInt(id.replace("Ph", "")) + 1;
                return String.format("Ph%03d", newCarId);
            } else {
                return "Ph001";
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
