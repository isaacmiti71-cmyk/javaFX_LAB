package com.example.javafx_lab;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage){
        Label label = new Label("Welcome ISAAC MITI");
        Button startButton = new Button("START");
        startButton.setOnAction(e -> label.setText("Button clicked!"));
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> label.setText("welcome"));
        VBox root = new VBox(10, label, startButton, resetButton    );
        root.setAlignment(Pos.CENTER);

        stage.setTitle("JavaFX Lab -STUDENT ID: 202509238");
        stage.setScene(new Scene(root, 320,200));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}