package com.example.BeautyBotBackend.enums;

public enum Schedule {

    HORAUNO("08:00 AM"),
    HORADOS("10:00 AM"),
    HORATRES("02:00 PM"),
    HORACUATRO("04:00 PM"),
    HORACINCO("06:00 PM");

    private String name;

    Schedule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
