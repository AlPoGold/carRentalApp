package org.example.desktop;


import org.example.service.CarRentalService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.desktop.controller.CarRentalDesktopController;

public class Main extends Application {

    private CarRentalService carRentalService = new CarRentalService(); // Initialize with your actual service implementation

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("javafx.fxml.allowLambda", "true");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/CarRentalDesktop.fxml"));
        loader.setController(new CarRentalDesktopController(carRentalService));

        Parent root = loader.load();
        primaryStage.setTitle("Car Rental System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
