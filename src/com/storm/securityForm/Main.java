package com.storm.securityForm;

import com.storm.securityForm.dataModel.FormData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Security Systems Login");
        primaryStage.setScene(new Scene(root, 750, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception{
        try {
            FormData.getInstance().saveUserData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try {
            FormData.getInstance().loadUserData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


