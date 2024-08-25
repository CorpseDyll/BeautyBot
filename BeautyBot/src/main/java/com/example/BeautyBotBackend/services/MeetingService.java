package com.example.BeautyBotBackend.services;

import com.example.BeautyBotBackend.model.Meeting;
import com.example.BeautyBotBackend.persistence.MeetingPersistence;

import java.io.IOException;

public class MeetingService {

    private MeetingPersistence meetingPersistence;

    public MeetingService() {
        this.meetingPersistence = new MeetingPersistence();
    }

    // Método para agregar una nueva cita.
    public void addMeeting(Meeting meeting) throws IOException {
        System.out.println("Añadiendo cita: " + meeting.toFileString());
        meetingPersistence.saveMeeting(meeting); // Guarda la cita utilizando MeetingPersistence.
    }

}
