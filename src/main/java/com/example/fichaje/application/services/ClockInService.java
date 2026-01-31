package com.example.fichaje.application.services;

import com.example.fichaje.application.ports.input.ClockInServicePort;
import com.example.fichaje.domain.model.ClockInType;
import com.example.fichaje.infrastructure.persistence.ClockInTypeRepositoryMongoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClockInService implements ClockInServicePort {

    private final ClockInTypeRepositoryMongoAdapter clockInTypeRepositoryMongoAdapter;

    @Override
    public List<ClockInType> getClockInTypes() {
        return clockInTypeRepositoryMongoAdapter.findAllTypes();
    }

    @Override
    public void createClockInType(ClockInType clockInType) {
        clockInTypeRepositoryMongoAdapter.createClockInType(clockInType);
    }

    @Override
    public void updateClockInType(ClockInType clockInType) {
        clockInTypeRepositoryMongoAdapter.updateClockInType(clockInType);
    }

    @Override
    public void deleteClockInType(String id) {
        clockInTypeRepositoryMongoAdapter.deleteClockInType(id);
    }

}
