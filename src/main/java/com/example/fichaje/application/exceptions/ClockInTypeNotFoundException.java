package com.example.fichaje.application.exceptions;

public class ClockInTypeNotFoundException extends RuntimeException {
    public ClockInTypeNotFoundException() {
        super("ClockIn Type Not Found");
    }
}
