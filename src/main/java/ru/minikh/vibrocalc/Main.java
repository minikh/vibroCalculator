package ru.minikh.vibrocalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main-form.fxml"));
        primaryStage.setTitle("Виброкалькулятор");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/icon.jpg"));
        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
