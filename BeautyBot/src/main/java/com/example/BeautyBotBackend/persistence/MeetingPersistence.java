package com.example.BeautyBotBackend.persistence;

import com.example.BeautyBotBackend.model.Meeting;
import java.io.*;

public class MeetingPersistence {

    private static final String DIRECTORY_PATH = "./persistence/txt_persistence";
    private static final String MEETING_FILE = DIRECTORY_PATH + "/meetings.txt";

    // Constructor que inicializa el directorio y el archivo de usuarios
    public MeetingPersistence() {
        try {
            // Crear el directorio si no existe
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IOException("No se pudo crear el directorio: " + DIRECTORY_PATH);
            }
            // Crear el archivo si no existe
            File file = new File(MEETING_FILE);
            if (!file.exists() && !file.createNewFile()) {
                throw new IOException("No se pudo crear el archivo: " + MEETING_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void saveMeeting(Meeting meeting) throws IOException {
        try (FileWriter fw = new FileWriter(MEETING_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
             bw.write(meeting.toFileString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error escribiendo la cita en el archivo: " + e.getMessage());
            throw e;
        }
    }

}
