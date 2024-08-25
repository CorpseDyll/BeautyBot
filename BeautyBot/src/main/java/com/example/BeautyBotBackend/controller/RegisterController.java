package com.example.BeautyBotBackend.controller;

import com.example.BeautyBotBackend.model.User;
import com.example.BeautyBotBackend.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML public TextField nameField, idField, phoneField, emailField;
    @FXML public PasswordField passwordField;
    @FXML public Button registerButton;
    @FXML public Button backButton;

    private final UserService userService = new UserService();

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/BeautyBotFrontend/homepage.fxml")); // Cargar el archivo FXML de inicio de sesión.
        Scene scene = new Scene(fxmlLoader.load(), 1300, 700);
        Stage newStage = new Stage();
        newStage.setTitle("Homepage");
        newStage.setScene(scene);
        newStage.show();
        newStage.setResizable(false);
        // Obtener la ventana actual y cerrarla.
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    protected void onRegisterButtonClick() {
        String nombre = nameField.getText();
        String cedula = idField.getText();
        String telefono = phoneField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Crear un nuevo usuario con los datos ingresados.
        User newUser = new User(nombre, cedula, telefono, email, password);
        try {
            userService.addUser(newUser); // Agregar el nuevo usuario al servicio.
        } catch (IOException e) {
            e.printStackTrace();
            // Mostrar una alerta de error si no se puede registrar el usuario.
            showAlert("Error de registro", "No se pudo registrar el usuario", Alert.AlertType.ERROR);
        }
    }

    // Método para mostrar una alerta.
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
