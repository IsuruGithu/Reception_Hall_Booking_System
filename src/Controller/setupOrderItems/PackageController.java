package Controller.setupOrderItems;

import Controller.BookitemControllers.HallController;
import DB.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Hall;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PackageController {
    public TextField txtid;
    public TextField txtname;
    public TextField txtcost;
    public TextField txtDesc;
    public ComboBox cmbid;
    public TextField txtFoodprice;
    public TextField txtFoodnoofpeople;
    public Label lblFoodTotal;
    public TextField txtDesertsprice;
    public TextField txtDesertsnoofpeople;
    public Label lblDesertTotal;
    public TextField txtBarprice;
    public TextField txtBarnoofpeople;
    public Label lblBarTotal;
    public Label lblNotification;
    public ComboBox cmbHallids;
    public Label txtHallDesc;
    public Label txtHallname;

    public void initialize(){
        try {
            loadHallIds();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbHallids.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setHallData((String) newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setHallData(String id) throws SQLException, ClassNotFoundException {
        Hall h1 = new HallController().getHall(id);
        if (h1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set...");
        }else {
            txtHallname.setText(h1.getName());
            txtHallDesc.setText((h1.getDesc()));
        }
    }

    private void loadHallIds() throws SQLException, ClassNotFoundException {
        List<String> hallIds = new HallController().getAllHallIds();
        cmbHallids.getItems().addAll(hallIds);
    }


    public void psaveOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();
        String name = txtname.getText();
        double cost = Double.parseDouble(txtcost.getText());
        String desc = txtDesc.getText();

        try {
            Connection con = DbConnection.getInstance().getConnection();
            Statement stm = con.createStatement();
            String query = "INSERT INTO Package VALUES ("+"'"+id+"'"+","+"'"+name+"'"+","+"'"+cost+"'"+","+"'"+desc+"'"+")";

            if (stm.executeUpdate(query)>0){
                lblNotification.setText("Saved ...");
                /*new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();*/
            }else{
                lblNotification.setText("Please. Try Again...");
                /*new Alert(Alert.AlertType.WARNING,"Try Again...").show();*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private String generateNewId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT Package_Id FROM Package ORDER BY Package_Id DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("Package_Id");
                int newPackageId = Integer.parseInt(id.replace("P", "")) + 1;
                return String.format("P%03d", newPackageId);
            } else {
                return "P001";
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void newPackageAddOnAction(ActionEvent actionEvent) {
        txtid.setText(generateNewId());
        txtname.clear();
        txtcost.clear();
        txtDesc.clear();
        txtFoodprice.clear();
        txtFoodnoofpeople.clear();
        txtDesertsprice.clear();
        txtDesertsnoofpeople.clear();
        txtBarprice.clear();
        txtBarnoofpeople.clear();
    }

    public void pdeleteOnAction(ActionEvent actionEvent) {
    }

    public void SetPackageCost(ActionEvent actionEvent) {
        double foodPlateprice = Double.parseDouble(txtFoodprice.getText());
        double foodnoofPlates = Double.parseDouble(txtFoodnoofpeople.getText());
        double foodTotalPrice = foodPlateprice * foodnoofPlates;

        double desertsPlateprice = Double.parseDouble(txtDesertsprice.getText());
        double desertsnoofplates = Double.parseDouble(txtDesertsnoofpeople.getText());
        double desertsTotalprice = desertsPlateprice * desertsnoofplates;

        double barBottleprice = Double.parseDouble(txtBarprice.getText());
        double barnoofpeople = Double.parseDouble(txtBarnoofpeople.getText());
        double barTotalprice = barBottleprice * barnoofpeople;

        lblFoodTotal.setText(String.valueOf(foodTotalPrice));
        lblDesertTotal.setText(String.valueOf(desertsTotalprice));
        lblBarTotal.setText(String.valueOf(barTotalprice));

        double Total = foodTotalPrice+desertsTotalprice+barTotalprice;

        txtcost.setText(String.valueOf(Total));
    }
    /*private void waiterServiceTotal(){
        double Total = ;
    }*/


    public void pClearOnAction(ActionEvent actionEvent) {
        txtid.clear();
        txtname.clear();
        txtcost.clear();
        txtDesc.clear();
        txtFoodprice.clear();
        txtFoodnoofpeople.clear();
        txtDesertsprice.clear();
        txtDesertsnoofpeople.clear();
        txtBarprice.clear();
        txtBarnoofpeople.clear();
    }
}
