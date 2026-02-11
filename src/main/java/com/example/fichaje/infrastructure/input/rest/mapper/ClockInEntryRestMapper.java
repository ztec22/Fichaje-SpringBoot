package com.example.fichaje.infrastructure.input.rest.mapper;

import com.example.fichaje.domain.model.ClockInEntry;
import com.example.fichaje.infrastructure.input.rest.dto.request.ClockInEntryRequest;
import com.example.fichaje.infrastructure.input.rest.dto.response.ClockInEntryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClockInEntryRestMapper {
    ClockInEntryResponse toDto(ClockInEntry clockInEntry);
    List<ClockInEntryResponse> toDtoList(List<ClockInEntry> clockInEntries);

    @Mapping(target = "clockInType.id", source = "clockInType")
    ClockInEntry toDomain(ClockInEntryRequest clockInEntryRequest);
}
