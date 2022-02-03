package Controller.setupOrderItems;

import DB.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EntertainmentController {
    public TextField txtbandid;
    public TextField txtbandName;
    public TextField txtbandDes;
    public TextField packaged;
    public ComboBox cmbBandid;

    public void bandSaveonAction(ActionEvent actionEvent) {
        String id = txtbandid.getText();
        String name = txtbandName.getText();
        String pname = packaged.getText();
        double cost = Double.parseDouble(txtbandDes.getText());

        try {
            Connection con=DbConnection.getInstance().getConnection();
            Statement stm = con.createStatement();
            String query = "INSERT INTO Band VALUES ( "+"'"+id+"'"+","+"'"+pname+"'"+","+"'"+name+"'"+","+"'"+cost+"'"+")";

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

    public void bandDeleteonAction(ActionEvent actionEvent) {
    }

    public void bandnewIdonAction(ActionEvent actionEvent) {
        txtbandid.setText(String.valueOf(generateNewId()));
        txtbandName.clear();
        packaged.clear();
        txtbandDes.clear();
    }
    private String generateNewId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT Band_Id FROM Band ORDER BY Band_Id DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("Band_Id");
                int newCarId = Integer.parseInt(id.replace("B", "")) + 1;
                return String.format("B%03d", newCarId);
            } else {
                return "B001";
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
