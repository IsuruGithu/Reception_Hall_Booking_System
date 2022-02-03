package Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    //public ImageView DashBoardImageview;
    public Button btnlogout;
    public AnchorPane dashboardContext;
    public AnchorPane context;
    //int count;

    /*public void slideshow(){
        ArrayList<Image> images =new ArrayList<Image>();
        images.add(new Image("/Assets/DashBoard/1.jpg"));
        images.add(new Image("/Assets/DashBoard/2.jpg"));
        images.add(new Image("/Assets/DashBoard/3.jpg"));
        images.add(new Image("/Assets/DashBoard/4.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            DashBoardImageview.setImage(images.get(count));
            count++;
            if (count == 4){
                count = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //slideshow();
    }

    public void logoutfromDash(ActionEvent event) throws IOException {
        Stage window = (Stage) dashboardContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"))));
    }

    public void openSaveCustomer(ActionEvent event) throws IOException {
        openUi("CustomerSave");
    }

    public void openBookingMenu(ActionEvent event) throws IOException {
        openUi("BookingMenu");
    }
    public void opensetuporderItems(ActionEvent event) throws IOException {
        openUi("setupOrderItems");
    }

    void openUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../View/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }



}
