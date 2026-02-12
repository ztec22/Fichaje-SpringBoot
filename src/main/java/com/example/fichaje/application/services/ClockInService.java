package com.example.fichaje.application.services;

import com.example.fichaje.application.ports.input.ClockInServicePort;
import com.example.fichaje.domain.enums.DeviceEnum;
import com.example.fichaje.domain.model.ClockInEntry;
import com.example.fichaje.domain.model.ClockInType;
import com.example.fichaje.infrastructure.output.persistence.ClockInRepositoryMongoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClockInService implements ClockInServicePort {

    private final ClockInRepositoryMongoAdapter clockInRepositoryMongoAdapter;

    @Override
    public List<ClockInType> getClockInTypes() {
        return clockInRepositoryMongoAdapter.findAllTypes();
    }

    @Override
    public void createClockInType(ClockInType clockInType) {
        clockInRepositoryMongoAdapter.createClockInType(clockInType);
    }

    @Override
    public void updateClockInType(ClockInType clockInType) {
        clockInRepositoryMongoAdapter.updateClockInType(clockInType);
    }

    @Override
    public void deleteClockInType(String id) {
        clockInRepositoryMongoAdapter.deleteClockInType(id);
    }

    @Override
    public void createClockInEntry(ClockInEntry clockInEntry) {

        ClockInType clockInType = clockInRepositoryMongoAdapter.findClockInTypeById(clockInEntry.getClockInType().getId());

        String device = DeviceEnum.safeValueOf(clockInEntry.getDevice());
        clockInEntry.setDevice(device);

        LocalDateTime timestamp = LocalDateTime.now();
        clockInEntry.setDate(timestamp.toLocalDate());
        clockInEntry.setTime(timestamp.toLocalTime());


        clockInRepositoryMongoAdapter.createClockInEntry(clockInEntry);
    }

    @Override
    public List<ClockInEntry> getClockInEntries() {
        return clockInRepositoryMongoAdapter.findEntries();
    }

}
