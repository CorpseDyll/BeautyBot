package com.example.BeautyBotBackend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {

    @FXML public Button loginButton;
    @FXML public Button registerButton;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/BeautyBotFrontend/login.fxml")); // Cargar el archivo FXML de inicio de sesión.
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        Stage newStage = new Stage();
        newStage.setTitle("Iniciar Sesión");
        newStage.setScene(scene);
        newStage.show();
        newStage.setResizable(false);
        // Obtener la ventana actual y cerrarla.
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/BeautyBotFrontend/register.fxml")); // Cargar el archivo FXML de inicio de sesión.
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        Stage newStage = new Stage();
        newStage.setTitle("Registrarse");
        newStage.setScene(scene);
        newStage.show();
        newStage.setResizable(false);
        // Obtener la ventana actual y cerrarla.
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

}