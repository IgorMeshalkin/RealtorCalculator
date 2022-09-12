package com.example.realtorcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RealtorCalculatorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RealtorCalculatorApp.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 430, 515);
        stage.setTitle("Калькулятор риэлтора");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}