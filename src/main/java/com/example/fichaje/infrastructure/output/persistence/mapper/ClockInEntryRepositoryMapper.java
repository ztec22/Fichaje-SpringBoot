package com.example.fichaje.infrastructure.output.persistence.mapper;

import com.example.fichaje.domain.model.ClockInEntry;
import com.example.fichaje.infrastructure.output.persistence.model.ClockInEntryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClockInEntryRepositoryMapper {

    @Mapping(target = "clockInType.id", source = "clockInType")
    ClockInEntry toDomain(ClockInEntryModel clockInEntryModel);
    List<ClockInEntry> toDomainList(List<ClockInEntryModel> clockInEntries);

    @Mapping(target = "clockInType", source = "clockInType.id")
    ClockInEntryModel toModel(ClockInEntry clockInEntry);
}
