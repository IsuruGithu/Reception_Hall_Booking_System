package Controller.BookitemControllers;

import Controller.CustomerService;
import DB.DbConnection;
import View.TM.CusomrtTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService {
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return  ids;
    }

    @Override
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query ="INSERT INTO Customer VALUES (?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getCustomer_Id());
        stm.setObject(2,c.getCustomer_Name());
        stm.setObject(3,c.getCustomer_Address());
        stm.setObject(4,c.getCustomer_Email());
        stm.setObject(5,c.getCustomer_Telephone());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET Customer_Name=?, Customer_Address=?, Customer_Email=?, Customer_Telephone=? WHERE Customer_Id=?");
        stm.setObject(1,c.getCustomer_Name());
        stm.setObject(2,c.getCustomer_Address());
        stm.setObject(3,c.getCustomer_Email());
        stm.setObject(4,c.getCustomer_Telephone());
        stm.setObject(5,c.getCustomer_Id());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE Customer_Id='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE Customer_Id=?");
        stm.setObject(1,id);
        ResultSet set =  stm.executeQuery();

        if (set.next()){
            return new Customer(
                set.getString(1),
                set.getString(2),
                set.getString(3),
                set.getString(4),
                set.getString(5)
            );

        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();

        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()){
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return customers;
    }
}
