package Controller;

import DB.DbConnection;
import model.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookController {
    public boolean placeOrder(Book book){
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Book VALUES (?,?,?,?,?,?)");
            stm.setObject(1,book.getId());
            stm.setObject(2,book.getbDate());
            stm.setObject(3,book.getrDate());
            stm.setObject(4,book.getFullcost());
            stm.setObject(5,book.getCustId());
            stm.setObject(6,book.getPackId());

            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }
}
