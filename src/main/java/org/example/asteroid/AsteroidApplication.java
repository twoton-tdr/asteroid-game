package org.example.asteroid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AsteroidApplication extends Application {
    public static int WIDHT = 600;
    public static int HEIGHT = 400;
    @Override
    public void start(Stage stage) throws IOException {

        Pane pane = new Pane();
        pane.setPrefSize(WIDHT,HEIGHT);
        stage.setTitle("Asteroid Game");



        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
