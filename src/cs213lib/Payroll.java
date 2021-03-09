package cs213lib;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Payroll extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("View.fxml"));
            primaryStage.setTitle("Payroll Processing");
            primaryStage.setScene(new Scene(root, 590, 475));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
