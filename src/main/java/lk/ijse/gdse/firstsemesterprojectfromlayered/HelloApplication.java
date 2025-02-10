package lk.ijse.gdse.firstsemesterprojectfromlayered;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginForm.fxml")));
        stage.setScene(new Scene(load));
        stage.setTitle("Login");
        stage.show();
    }
}