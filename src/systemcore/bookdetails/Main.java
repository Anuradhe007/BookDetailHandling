package systemcore.bookdetails;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("BookDetailsView.fxml"));
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root,880,1500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
