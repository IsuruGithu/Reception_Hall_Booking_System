package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class setupOrderItemsController {
    public AnchorPane orderitemContext;

    public void openpackage(ActionEvent event) throws IOException {
        opensUi("Package");
    }

    public void openHall(ActionEvent event) throws IOException {
        opensUi("Hall");
    }

    public void openPhotographer(ActionEvent event) throws IOException {
        opensUi("Photographer");
    }

    public void opencar(ActionEvent event) throws IOException {
        opensUi("Car");
    }
    public void openOtherServices(ActionEvent actionEvent) throws IOException {
        opensUi("OtherServices");
    }
    public void openEntertainmentUi(ActionEvent actionEvent) throws IOException {
        opensUi("Entertainment");
    }
    void opensUi(String filName) throws IOException {
        URL resource = getClass().getResource("../View/setupOrderItems/" + filName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        orderitemContext.getChildren().clear();
        orderitemContext.getChildren().add(load);
    }

}
