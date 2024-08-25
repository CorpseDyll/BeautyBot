package com.example.BeautyBotBackend.controller;

import com.example.BeautyBotBackend.model.SessionManager;
import com.example.BeautyBotBackend.services.UserService;
import com.example.BeautyBotBackend.model.User;
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
import java.net.URL;

public class LoginController {

    @FXML public TextField emailField;
    @FXML public PasswordField passwordField;
    @FXML public Button loginButton;
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
    protected void onLoginButtonClick() {
        try {
            String email = emailField.getText().trim(); // Obtener el correo electrónico ingresado.
            String password = passwordField.getText().trim(); // Obtener la contraseña ingresada.
            User user = userService.getUserByEmail(email); // Obtener el usuario por correo electrónico.

            if (user != null) {
                System.out.println("Usuario encontrado: " + user.getName());
                System.out.println("Contraseña ingresada: " + password);
                System.out.println("Contraseña almacenada: " + user.getPassword());

                // Verificar si la contraseña ingresada coincide con la almacenada.
                if (user.getPassword().equals(password)) {
                    System.out.println("Contraseña correcta");

                    // Verificar si el usuario es un administrador.
                    if (email.equals("admin@beautybot.com") && password.equals("admin123")) {
                        loadAdminDashboard(); // Cargar el dashboard del administrador.
                    } else {
                        // Mostrar un mensaje de éxito y cargar la página principal del usuario.
                        showAlert("Inicio de sesión exitoso", "Bienvenid@ " + user.getName(), Alert.AlertType.INFORMATION);
                        loadUserpageBoard(user); // Método para cargar la página del usuario.
                    }
                } else {
                    showAlert("Inicio de Sesión fallido", "Credenciales inválidas.", Alert.AlertType.ERROR);
                }
            } else {
                showAlert("Inicio de Sesión fallido", "Credenciales inválidas.", Alert.AlertType.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load user data", Alert.AlertType.ERROR);
        }
    }

    // Método para cargar la página principal del usuario.
    private void loadUserpageBoard(User user) throws IOException {
        String email = user.getEmail();
        SessionManager.setCurrentUserEmail(email); // Guarda el correo electrónico del usuario autenticado.
        URL url = getClass().getResource("/com/example/BeautyBotFrontend/userpage.fxml");
        System.out.println("Resource URL by getClass: " + url);
        if (url == null) {
            throw new IOException("Cannot find resource file");
        }
        try {
            FXMLLoader loader = new FXMLLoader(url);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Página del usuario");
            stage.show();
            stage.setResizable(false);
        } catch (Exception e) {
            System.err.println("Error loading the FXML file: " + e.getMessage());
            throw e;
        }
    }

    // Método para cargar el dashboard del administrador.
    private void loadAdminDashboard() throws IOException {
        URL url = getClass().getResource("/com/corpsedyll/BeautyBotFrontend/adminDashboard.fxml");
        System.out.println("Resource URL by getClass: " + url);
        if (url == null) {
            throw new IOException("Cannot find resource file");
        }
        try {
            FXMLLoader loader = new FXMLLoader(url);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Gestor de eventos");
            stage.show();
        } catch (Exception e) {
            System.err.println("Error loading the FXML file: " + e.getMessage());
            throw e;
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
