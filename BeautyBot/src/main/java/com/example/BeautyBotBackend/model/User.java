package com.example.BeautyBotBackend.model;

public class User {

    private String name, id, phoneNumber, email, password;

    public User(String name, String id, String phoneNumber, String email, String password) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static User fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("El formato del archivo no es válido, se esperaban 5 elementos pero se encontraron " + parts.length);
        }
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    public String toFileString() {
        return name + "," + id + "," + phoneNumber + "," + email + "," + password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
