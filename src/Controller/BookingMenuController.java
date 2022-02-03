package Controller;

import Controller.BookitemControllers.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;
import model.*;
import model.Package;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingMenuController {
    public Label lblDate;
    public Label lblTime;
    public ComboBox<String> cmbCustomerIds;
    public ComboBox<String> cmbPackageids;

    public Label txtName;
    public Label txtAddress;
    public Label txtEmail;
    public Label txtTele;
    public Label txtPackageName;
    public Label txtPackageCost;
    public ComboBox cmbCarIds;
    public Label txtcarName;
    public Label txtcarCost;
    public Label txtPhotographerName;
    public Label txtPhotographerCost;
    public ComboBox cmbPhotographerIds;
    public ComboBox cmbBandIds;
    public Label txtBandName;
    public Label txtBandCost;
    public Label txtFullTotalAmount;
    public TextField txtReserveDate;
    public Label txtbookId;

    public void initialize(){

        loadDateAndTimer();
        try {
            loadCustomerIds();
            loadPackageids();
            loadCarIds();
            loadPhotographerIds();
            loadBandIds();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            try {
                setCustomerData(newValue);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbPackageids.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPackageData(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbCarIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCarData((String) newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbPhotographerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPhotographerData((String) newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbBandIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setEntertainmentData((String) newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setEntertainmentData(String id) throws SQLException, ClassNotFoundException {
        Entertainment e1 = new EntertainmentController().getBand(id);
        if (e1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set...");
        }else {
            txtBandName.setText(e1.getName());
            txtBandCost.setText(String.valueOf(e1.getCost()));
        }

    }


    private void setPhotographerData(String id) throws SQLException, ClassNotFoundException {
        Photographer ph1 = new PhotographerController().getPhtographer(id);
        if (ph1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set...");
        }else {
            txtPhotographerName.setText(ph1.getName());
            txtPhotographerCost.setText(String.valueOf(ph1.getCost()));
        }
    }
    private void setCarData(String id) throws SQLException, ClassNotFoundException {
        Car c1 = new CarController().getCar(id);
        if (c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set...");
        }else {
            txtcarName.setText(c1.getName());
            txtcarCost.setText(String.valueOf(c1.getCost()));
        }
    }

    private void setPackageData(String id) throws SQLException, ClassNotFoundException {
        Package p1 = new PackageController().getPackage(id);
        if (p1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set...");
        }else {
            txtPackageName.setText(p1.getName());
            txtPackageCost.setText(String.valueOf(p1.getCost()));
        }
    }

    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(customerId);
        if (c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set...");
        }else{
            txtName.setText(c1.getCustomer_Name());
            txtAddress.setText(c1.getCustomer_Address());
            txtEmail.setText(c1.getCustomer_Email());
            txtTele.setText(c1.getCustomer_Telephone()) ;
        }
    }

    private void loadBandIds() throws SQLException, ClassNotFoundException {
        List<String> bandIds = new EntertainmentController().getAllBandIds();
        cmbBandIds.getItems().addAll(bandIds);
    }

    private void loadCarIds() throws SQLException, ClassNotFoundException {
        List<String> carIds = new CarController().getAllCarIds();
        cmbCarIds.getItems().addAll(carIds);
    }
    private void loadPhotographerIds() throws SQLException, ClassNotFoundException {
        List<String> photographerIds = new PhotographerController().getAllPhotographerIds();
        cmbPhotographerIds.getItems().addAll(photographerIds);
    }

    private void loadPackageids() throws SQLException, ClassNotFoundException {
        List<String> PackageIds = new PackageController().getAllPackageIds();
        cmbPackageids.getItems().addAll(PackageIds);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerIds.getItems().addAll(customerIds);
    }


    private void loadDateAndTimer(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour()+" : "+currentTime.getMinute()+" : "+currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
                );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void addtoOrderOnAction(ActionEvent actionEvent) {
        double packagecost = Double.parseDouble(txtPackageCost.getText());
        double bandcost = Double.parseDouble(txtBandCost.getText());
        double photographercost = Double.parseDouble(txtPhotographerCost.getText());
        double carcost = Double.parseDouble(txtcarCost.getText());

        double total = packagecost+bandcost+photographercost+carcost;

        txtFullTotalAmount.setText(String.valueOf(total)+"0 /=");

    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
        
    }
}
