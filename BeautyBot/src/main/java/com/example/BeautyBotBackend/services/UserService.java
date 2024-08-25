package com.example.BeautyBotBackend.services;

import com.example.BeautyBotBackend.model.User;
import com.example.BeautyBotBackend.persistence.UserPersistence;

import java.io.IOException;
import java.util.List;

public class UserService {

    private UserPersistence userPersistence;

    // Constructor que inicializa el UserPersistence.
    public UserService() {
        this.userPersistence = new UserPersistence();
    }

    // Método para agregar un nuevo usuario.
    public void addUser(User user) throws IOException {
        System.out.println("Añadiendo usuario: " + user.toFileString()); // Imprime información sobre el usuario.
        userPersistence.saveUser(user); // Guarda el usuario utilizando UserPersistence.
    }

    // Método para obtener todos los usuarios.
    public List<User> getAllUsers() throws IOException {
        return userPersistence.loadAllUsers(); // Retorna todos los usuarios almacenados.
    }

    // Método para obtener un usuario por su dirección de correo electrónico.
    public User getUserByEmail(String email) throws IOException {
        return userPersistence.findUserByEmail(email); // Busca y retorna un usuario por su correo electrónico.
    }

    // Método para actualizar la información de un usuario existente.
    public void updateUser(User user) throws IOException {
        userPersistence.updateUser(user); // Actualiza el usuario utilizando UserPersistence.
    }

    // Método para eliminar un usuario por su dirección de correo electrónico.
    public void deleteUser(String email) throws IOException {
        userPersistence.deleteUserByEmail(email); // Elimina el usuario utilizando UserPersistence.
    }

}
