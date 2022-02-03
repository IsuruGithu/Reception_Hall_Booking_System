package Controller;

import Controller.BookitemControllers.CustomerController;
import DB.DbConnection;
import View.TM.CusomrtTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerSaveController {

    public TableView<CusomrtTM> tblCustomer;
    public TableColumn clmid;
    public TableColumn clmname;
    public TableColumn clmTel;
    public TableColumn clmaddress;
    public TableColumn clmmail;

    public TextField txtCid;
    public TextField txtCName;
    public TextField txtCAddress;
    public TextField txtCTel;
    public TextField txtCEmail;
    public Label txtShowAction;


    public void customerSaveOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {


        Customer c1 = new Customer(
                txtCid.getText(),txtCName.getText(),txtCAddress.getText(),txtCEmail.getText(),txtCTel.getText()
        );
        if (saveCustomer(c1)){
            txtShowAction.setText("Saved ...");
            initialize();
            /*new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();*/
        }else {
            txtShowAction.setText("Try Again...");
        }

    }
    boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        return new CustomerController().saveCustomer(c);

    }
    private String generateNewId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT Customer_Id FROM Customer ORDER BY Customer_Id DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("Customer_Id");
                int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
                return String.format("C%03d", newCustomerId);
            } else {
                return "C001";
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void customerAddnew(ActionEvent actionEvent) {
        txtCid.setText(generateNewId());
        txtCName.clear();
        txtCEmail.clear();
        txtCTel.clear();
        txtCAddress.clear();
    }


    public void saveBtnclickOnAction(ActionEvent evt) throws SQLException, ClassNotFoundException {
        customerSaveOnAction(evt);
        initialize();
    }

    public void searchCustomer_onAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId=txtCid.getText();
        Customer c1 = new CustomerController().getCustomer(customerId);
        if (c1==null){
            txtShowAction.setText("Empty Result Set...");
            /*new Alert(Alert.AlertType.WARNING,"Empty Result Set...").show();*/
        }else {
            setData(c1);
        }
    }



    public void customerDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new CustomerController().deleteCustomer(txtCid.getText())){
            txtClear();
            initialize();
            txtShowAction.setText("Deleted ...");
        }else {
            txtShowAction.setText("Try Again...");
            /*new Alert(Alert.AlertType.WARNING,"Try Again...").show();*/
        }

    }

    public void customerUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtCid.getText(),
                txtCName.getText(),
                txtCAddress.getText(),
                txtCEmail.getText(),
                txtCTel.getText()
        );

        if (new CustomerController().updateCustomer(c1)){
            initialize();
            txtShowAction.setText("UPDATED ...");
        }else {
            txtShowAction.setText("Try Again");
        }
    }

    public void txtclear_onAction(ActionEvent actionEvent) {
        txtClear();
    }

    public void initialize(){
        try {
            clmid.setCellValueFactory(new PropertyValueFactory<>("Customer_Id"));
            clmname.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            clmaddress.setCellValueFactory(new PropertyValueFactory<>("Customer_Address"));
            clmmail.setCellValueFactory(new PropertyValueFactory<>("Customer_Email"));
            clmTel.setCellValueFactory(new PropertyValueFactory<>("Customer_Telephone"));

            setCustomersToTable(new CustomerController().getAllCustomer());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void setCustomersToTable(ArrayList<Customer> customers) {
        ObservableList<CusomrtTM> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new CusomrtTM(e.getCustomer_Id(),e.getCustomer_Name(),e.getCustomer_Address(),e.getCustomer_Email(),e.getCustomer_Telephone()));
        });
        tblCustomer.setItems(obList);
    }


    void setData(Customer c) {
        txtCid.setText(c.getCustomer_Id());
        txtCName.setText(c.getCustomer_Name());
        txtCAddress.setText(c.getCustomer_Address());
        txtCEmail.setText(c.getCustomer_Email());
        txtCTel.setText(c.getCustomer_Telephone());
    }
    private void txtClear(){
        txtCid.clear();
        txtCName.clear();
        txtCEmail.clear();
        txtCTel.clear();
        txtCAddress.clear();
    }


}
