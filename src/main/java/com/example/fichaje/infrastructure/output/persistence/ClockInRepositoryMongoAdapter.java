package com.example.fichaje.infrastructure.output.persistence;


import com.example.fichaje.application.ports.output.ClockInRepositoryPort;
import com.example.fichaje.application.exceptions.ClockInTypeNotFoundException;
import com.example.fichaje.domain.model.ClockInEntry;
import com.example.fichaje.domain.model.ClockInType;
import com.example.fichaje.infrastructure.output.persistence.mapper.ClockInEntryRepositoryMapper;
import com.example.fichaje.infrastructure.output.persistence.mapper.ClockInTypeRepositoryMapper;
import com.example.fichaje.infrastructure.output.persistence.model.ClockInEntryModel;
import com.example.fichaje.infrastructure.output.persistence.model.ClockInTypeModel;
import com.example.fichaje.infrastructure.output.persistence.repository.ClockInEntryModelRespository;
import com.example.fichaje.infrastructure.output.persistence.repository.ClockInTypeModelRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClockInRepositoryMongoAdapter implements ClockInRepositoryPort {

    private final ClockInTypeModelRespository clockInTypeModelRespository;
    private final ClockInTypeRepositoryMapper clockInTypeRepositoryMapper;

    private final ClockInEntryModelRespository clockInEntryModelRespository;
    private final ClockInEntryRepositoryMapper clockInEntryRepositoryMapper;

    @Override
    public ClockInType findClockInTypeById(String id) {
        ClockInTypeModel clockInTypeModel = clockInTypeModelRespository.findById(id)
                .orElseThrow(ClockInTypeNotFoundException::new);

        return clockInTypeRepositoryMapper.toDomain(clockInTypeModel);
    }

    @Override
    public List<ClockInType> findAllTypes() {

        log.info("ClockInRepositoryMongoAdapter :: findAllTypes: Getting all clockInTypes");

        return clockInTypeRepositoryMapper.toDomainList(clockInTypeModelRespository.findAll());
    }

    @Override
    public void createClockInType(ClockInType clockInType) {
        ClockInTypeModel clockInTypeModel = clockInTypeRepositoryMapper.toModel(clockInType);
        clockInTypeModelRespository.save(clockInTypeModel);
    }

    @Override
    public void updateClockInType(ClockInType clockInType) {
        ClockInTypeModel clockInTypeModel = clockInTypeModelRespository.findById(clockInType.getId())
                .orElseThrow(ClockInTypeNotFoundException::new);

        clockInTypeModel.setDescription(clockInType.getDescription());
        clockInTypeModel.setIo(clockInType.isIo());

        clockInTypeModelRespository.save(clockInTypeModel);
    }

    @Override
    public void deleteClockInType(String id) {
        clockInTypeModelRespository.deleteById(id);
    }

    @Override
    public void createClockInEntry(ClockInEntry clockInEntry) {
        ClockInEntryModel model = clockInEntryRepositoryMapper.toModel(clockInEntry);
        clockInEntryModelRespository.save(model);
    }

    @Override
    public List<ClockInEntry> findEntries() {
        return clockInEntryModelRespository.findEntries();
    }
}
