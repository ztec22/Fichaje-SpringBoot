package com.example.fichaje.domain.exceptions;

public class InvalidDeviceException extends RuntimeException {
    public InvalidDeviceException() {
        super("Invalid Device");
    }
}
