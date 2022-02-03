package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CreateAccountController {
    public Pane createAccountContext;

    public void openLoginForm(ActionEvent event) throws IOException {
        loadUi1("LoginForm");
    }

    public void openLoginForm2(ActionEvent event) throws IOException {
        loadUi1("LoginForm");
    }
    void loadUi1(String fileName) throws IOException {
        Stage window = (Stage) createAccountContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/"+ fileName +".fxml"))));
    }
}
