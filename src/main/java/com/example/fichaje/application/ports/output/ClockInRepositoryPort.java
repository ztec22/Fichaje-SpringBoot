package com.example.fichaje.application.ports.output;

import com.example.fichaje.domain.model.ClockInType;

import java.util.List;

public interface ClockInRepositoryPort {

    List<ClockInType> findAllTypes();
}
