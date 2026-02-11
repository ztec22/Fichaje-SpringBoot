package com.example.fichaje.application.ports.output;

import com.example.fichaje.domain.model.ClockInEntry;
import com.example.fichaje.domain.model.ClockInType;

import java.util.List;

public interface ClockInRepositoryPort {

    ClockInType findClockInTypeById(String id);
    List<ClockInType> findAllTypes();
    void createClockInType(ClockInType clockInType);
    void updateClockInType(ClockInType clockInType);
    void deleteClockInType(String id);

    void createClockInEntry(ClockInEntry clockInEntry);
    List<ClockInEntry> findEntries();
}
