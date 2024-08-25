package com.example.BeautyBotBackend.persistence;

import com.example.BeautyBotBackend.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserPersistence {

    private static final String DIRECTORY_PATH = "./persistence/txt_persistence";
    private static final String USER_FILE = DIRECTORY_PATH + "/users.txt";

    // Constructor que inicializa el directorio y el archivo de usuarios
    public UserPersistence() {
        try {
            // Crear el directorio si no existe
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IOException("No se pudo crear el directorio: " + DIRECTORY_PATH);
            }
            // Crear el archivo si no existe
            File file = new File(USER_FILE);
            if (!file.exists() && !file.createNewFile()) {
                throw new IOException("No se pudo crear el archivo: " + USER_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void saveUser(User user) throws IOException {
        try (FileWriter fw = new FileWriter(USER_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(user.toFileString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error escribiendo el usuario en el archivo: " + e.getMessage());
            throw e;
        }
    }

    // Método para cargar todos los usuarios del archivo
    public List<User> loadAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                users.add(User.fromFileString(line));
            }
        }
        return users;
    }

    public User findUserByEmail(String email) throws IOException {
        List<User> users = loadAllUsers();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user) {
    }

    public void deleteUserByEmail(String email) {
    }
}
