package com.example.fichaje.infrastructure.persistence.mapper;

import com.example.fichaje.domain.model.ClockInType;
import com.example.fichaje.infrastructure.persistence.model.ClockInTypeModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClockInTypeRepositoryMapper {
    ClockInType toDomain(ClockInTypeModel clockInTypeModel);
    List<ClockInType> toDomainList(List<ClockInTypeModel> clockInTypes);
}
