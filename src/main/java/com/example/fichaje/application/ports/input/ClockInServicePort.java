package com.example.fichaje.application.ports.input;

import com.example.fichaje.domain.model.ClockInType;

import java.util.List;

public interface ClockInServicePort {

    List<ClockInType> getClockInTypes();
    void createClockInType(ClockInType clockInType);
    void updateClockInType(ClockInType clockInType);
    void deleteClockInType(String id);
}
