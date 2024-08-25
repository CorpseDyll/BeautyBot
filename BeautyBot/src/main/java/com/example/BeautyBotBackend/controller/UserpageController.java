package com.example.BeautyBotBackend.controller;

import com.example.BeautyBotBackend.enums.Schedule;
import com.example.BeautyBotBackend.model.Meeting;
import com.example.BeautyBotBackend.model.Services;
import com.example.BeautyBotBackend.model.SessionManager;
import com.example.BeautyBotBackend.persistence.UserPersistence;
import com.example.BeautyBotBackend.model.User;
import com.example.BeautyBotBackend.services.MeetingService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserpageController implements Initializable {

    @FXML public TextField nameField, idField, emailField, phoneField;
    @FXML public CheckBox manicureCheckBox, pedicureCheckBox, haircutCheckBox, makeupCheckBox;
    @FXML public DatePicker datePicker;
    @FXML public ComboBox<String> hourComboBox;

    private final UserPersistence userPersistence = new UserPersistence();
    private final MeetingService meetingService = new MeetingService();
    public ArrayList<Services> selectedServices = new ArrayList<>();
    public String date, hour;
    User currentUser;
    private LocalDate localDate = LocalDate.now();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        String userEmail = SessionManager.getCurrentUserEmail(); // Obtener el correo electrónico del usuario actual
        try {
            currentUser = userPersistence.findUserByEmail(userEmail); // Buscar al usuario actual por correo electrónico
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (currentUser != null) {
            nameField.setText(currentUser.getName()); // Establecer el nombre del usuario en el campo correspondiente
            emailField.setText(currentUser.getEmail()); // Establecer el correo electrónico del usuario en el campo correspondiente
            idField.setText(currentUser.getId());
            phoneField.setText(currentUser.getPhoneNumber());
        }
    }

    public void onGenerateButtonClick() throws IOException {
        addDateSelected();
    }

    private void addDateSelected() throws IOException {
        // Verificar si se seleccionó una fecha.
        if (datePicker.getValue() == null || hourComboBox.getValue() == null) {
            showAlert("ERROR", "No hay ninguna fecha u hora seleccionada.", Alert.AlertType.ERROR);
        } else {
            // Si ambos campos están seleccionados, proceder con la obtención de valores.
            if (datePicker.getValue().isBefore(localDate)){
                showAlert("ERROR", "No se puede seleccionar una fecha anterior a la actual.", Alert.AlertType.ERROR);
            } else {
                date = datePicker.getValue().toString();
                hour = hourComboBox.getValue().toString(); // Aquí es importante obtener el valor correcto.
                addSelectedServices();
            }
        }
    }

    private void addSelectedServices() throws IOException {
        if (manicureCheckBox.isSelected() && !selectedServices.contains(Services.MANICURE)) {
            selectedServices.add(Services.MANICURE);
        }
        if (pedicureCheckBox.isSelected() && !selectedServices.contains(Services.PEDICURE)) {
            selectedServices.add(Services.PEDICURE);
        }
        if (makeupCheckBox.isSelected() && !selectedServices.contains(Services.MAKEUP)) {
            selectedServices.add(Services.MAKEUP);
        }
        if (haircutCheckBox.isSelected() && !selectedServices.contains(Services.HAIRCUT)) {
            selectedServices.add(Services.HAIRCUT);
        }

        if (!selectedServices.isEmpty()) {
            Meeting meeting = new Meeting(currentUser.getName(), currentUser.getId(), date, hour, selectedServices);
            meetingService.addMeeting(meeting);
        } else {
            showAlert("ERROR", "No hay ningún servicio seleccionado.", Alert.AlertType.ERROR);
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

    private void initializeComboBoxes() {
        // Carga los nombres de los valores de Schedule en el ComboBox
        for (Schedule schedule : Schedule.values()) {
            hourComboBox.getItems().add(schedule.getName());
        }
    }

}
