module com.example.beautybot {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.BeautyBotBackend to javafx.fxml;
    exports com.example.BeautyBotBackend;
    exports com.example.BeautyBotBackend.controller;
    opens com.example.BeautyBotBackend.controller to javafx.fxml;
}