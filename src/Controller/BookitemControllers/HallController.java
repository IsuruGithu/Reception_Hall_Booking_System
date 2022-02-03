package Controller.BookitemControllers;

import DB.DbConnection;
import model.Hall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallController {
    public List<String> getAllHallIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Hall").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }
    public Hall getHall(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Hall WHERE Hall_Id='" + id + "'").executeQuery();
        if (rst.next()){
            return new Hall(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }else {
            return null;
        }
    }
}
