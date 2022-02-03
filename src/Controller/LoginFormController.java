package Controller;

import DB.DbConnection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public AnchorPane welcomeContext;
    public Button btnLogin;
    @FXML private Button cnclbtn;
    @FXML Label errormessage;
    @FXML TextField txtUserName;
    @FXML PasswordField txtPassword;
    @FXML ImageView imageView;
    int count;




    public void slideshow(){
        ArrayList<Image> images =new ArrayList<Image>();
        images.add(new Image("/Assets/loginSlide/39409112_1972056842847027_3906080289637531648_n.jpg"));
        images.add(new Image("/Assets/loginSlide/122034356_3559107520808610_2126889588202341162_n.jpg"));
        images.add(new Image("/Assets/loginSlide/wedding-celebration-dinner-sea-side-hotel-near-swimming-pool-surrounded-palms-arrangement-178128989.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            imageView.setImage(images.get(count));
            count++;
            if (count == 3){
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
    public void OpenCreateAccount(ActionEvent event) throws IOException {
        Stage window = (Stage) welcomeContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/CreateAccount.fxml"))));
    }


    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if(txtUserName.getText().isEmpty() == false && txtPassword.getText().isEmpty() == false){
            if (txtUserName.getText().equals("3") && txtPassword.getText().equals("2")){
                Stage window = (Stage) welcomeContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoard.fxml"))));
            }
            /*Stage window = (Stage) welcomeContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoard.fxml"))));
            //validateLogin();*/

        }else{
            errormessage.setText("Please Enter UserName & Password...");
        }
    }

    public void canclbtnonAction(ActionEvent event) {
        Stage stage = (Stage) cnclbtn.getScene().getWindow();
        stage.close();
    }

    public void login_onAction(ActionEvent evt) throws IOException {
        loginButtonOnAction(evt);
    }
    public void validateLogin(String usrname) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User WHERE UsrName='" + usrname + "'");

    }

    public void createAccountForm(){

    }
}
