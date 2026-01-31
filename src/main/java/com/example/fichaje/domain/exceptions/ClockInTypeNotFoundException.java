package com.example.fichaje.domain.exceptions;

public class ClockInTypeNotFoundException extends RuntimeException {
    public ClockInTypeNotFoundException() {
        super("ClockIn Type Not Found");
    }
}
