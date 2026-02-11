package com.example.fichaje.domain.enums;

import com.example.fichaje.domain.exceptions.InvalidDeviceException;

public enum DeviceEnum {
    PC,
    MOBILE;

    public static String safeValueOf(String device) {
        try {
            return valueOf(device.toUpperCase()).toString();
        } catch (IllegalArgumentException e) {
            throw new InvalidDeviceException();
        }
    }
}
