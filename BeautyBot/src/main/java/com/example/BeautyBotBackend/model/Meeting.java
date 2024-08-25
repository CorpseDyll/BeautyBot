package com.example.BeautyBotBackend.model;

import java.util.ArrayList;

public class Meeting {

    private String nameUser, idUser, date, hour;
    private ArrayList<Services> services;

    public Meeting(String nameUser, String idUser, String date, String hour, ArrayList<Services> services) {
        this.nameUser = nameUser;
        this.idUser = idUser;
        this.date = date;
        this.hour = hour;
        this.services = services;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public ArrayList<Services> getServices() {
        return services;
    }

    public String toFileString() {
        return nameUser + "," + idUser + "," + date + "," + hour + "," + services;
    }

    public static Meeting fromFileString(String fileString) {
        String[] parts = fileString.split(",", 5); // Limita el split a 5 partes para evitar dividir dentro de la lista de servicios
        if (parts.length != 5) {
            throw new IllegalArgumentException("El formato del archivo no es válido, se esperaban 5 elementos pero se encontraron " + parts.length);
        }

        ArrayList<Services> servicesList = convertStringToServiceList(parts[4]);
        return new Meeting(parts[0], parts[1], parts[2], parts[3], servicesList);
    }

    private static ArrayList<Services> convertStringToServiceList(String servicesString) {
        // Eliminar "Servicios[" y "]"
        servicesString = servicesString.replace("Servicios[", "").replace("]", "");

        // Separar los nombres de servicios por comas
        String[] serviceNames = servicesString.split(",\\s*"); // Maneja espacios después de la coma

        // Convertir a ArrayList<Services>
        ArrayList<Services> servicesList = new ArrayList<>();
        for (String serviceName : serviceNames) {
            servicesList.add(Services.valueOf(serviceName));
        }

        return servicesList;
    }

}
