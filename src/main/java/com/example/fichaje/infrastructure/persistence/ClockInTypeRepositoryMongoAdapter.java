package com.example.fichaje.infrastructure.persistence;


import com.example.fichaje.application.ports.output.ClockInRepositoryPort;
import com.example.fichaje.domain.model.ClockInType;
import com.example.fichaje.infrastructure.persistence.mapper.ClockInTypeRepositoryMapper;
import com.example.fichaje.infrastructure.persistence.repository.ClockInTypeModelRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClockInTypeRepositoryMongoAdapter implements ClockInRepositoryPort {

    private final ClockInTypeModelRespository clockInTypeModelRespository;
    private final ClockInTypeRepositoryMapper clockInTypeRepositoryMapper;

    @Override
    public List<ClockInType> findAllTypes() {

        log.info("ClockInRepositoryMongoAdapter :: findAllTypes: Getting all clockInTypes");

        return clockInTypeRepositoryMapper.toDomainList(clockInTypeModelRespository.findAll());
    }
}
