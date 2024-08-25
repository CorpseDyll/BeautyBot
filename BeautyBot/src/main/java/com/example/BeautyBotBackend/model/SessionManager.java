package com.example.BeautyBotBackend.model;

public class SessionManager {

    private static String currentUserEmail; // Correo electrónico del usuario actual

    // Método estático para establecer el correo electrónico del usuario actual
    public static void setCurrentUserEmail(String email) {
        currentUserEmail = email;
    }

    // Método estático para obtener el correo electrónico del usuario actual
    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }

}
