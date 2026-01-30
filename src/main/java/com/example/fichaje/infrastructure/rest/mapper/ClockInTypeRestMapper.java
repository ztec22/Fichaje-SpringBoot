package com.example.fichaje.infrastructure.rest.mapper;

import com.example.fichaje.domain.model.ClockInType;
import com.example.fichaje.infrastructure.rest.dto.response.ClockInTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClockInTypeRestMapper {
    ClockInTypeResponse toDto(ClockInType clockInType);
    List<ClockInTypeResponse> toDtoList(List<ClockInType> clockInTypes);
}
