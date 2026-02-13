package com.example.fichaje.infrastructure.input.rest;


import com.example.fichaje.application.ports.input.ClockInServicePort;
import com.example.fichaje.domain.model.ClockInEntry;
import com.example.fichaje.domain.model.ClockInType;
import com.example.fichaje.infrastructure.input.rest.dto.common.ApiResponse;
import com.example.fichaje.infrastructure.input.rest.dto.request.ClockInEntryRequest;
import com.example.fichaje.infrastructure.input.rest.dto.request.ClockInTypeRequest;
import com.example.fichaje.infrastructure.input.rest.dto.response.ClockInEntryResponse;
import com.example.fichaje.infrastructure.input.rest.dto.response.ClockInTypeResponse;
import com.example.fichaje.infrastructure.input.rest.mapper.ClockInEntryRestMapper;
import com.example.fichaje.infrastructure.input.rest.mapper.ClockInTypeRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clockin")
@RequiredArgsConstructor
public class ClockInRestAdapter {

    private final ClockInServicePort clockInServicePort;

    private final ClockInTypeRestMapper clockInTypeRestMapper;
    private final ClockInEntryRestMapper clockInEntryRestMapper;


    @GetMapping
    public ResponseEntity<List<ClockInEntryResponse>> getClockInEntries() {

        List<ClockInEntryResponse> entries = clockInEntryRestMapper.toDtoList(
                clockInServicePort.getClockInEntries()
        );

        return ResponseEntity.ok().body(entries);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createClockInEntry(
            @RequestBody @Valid ClockInEntryRequest clockInEntryRequest
    ) {

        ClockInEntry clockInEntry = clockInEntryRestMapper.toDomain(clockInEntryRequest);
        clockInServicePort.createClockInEntry(clockInEntry);

        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("Clock in entry created successfully")
                        .build()
        );
    }

    @GetMapping("/types")
    public ResponseEntity<List<ClockInTypeResponse>> getClockInTypes() {

        List<ClockInTypeResponse> types = clockInTypeRestMapper.toDtoList(
                clockInServicePort.getClockInTypes()
        );

        return ResponseEntity.ok().body(types);
    }

    @PostMapping("/types")
    public ResponseEntity<ApiResponse> createClockInType(
            @RequestBody @Valid ClockInTypeRequest clockInTypeRequest
    ) {

        ClockInType clockInType = clockInTypeRestMapper.toDomain(clockInTypeRequest);
        clockInServicePort.createClockInType(clockInType);

        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("Clock in type created successfully")
                        .build()
        );
    }

    @PutMapping("/types/{id}")
    public ResponseEntity<ApiResponse> updateClockInType(
            @PathVariable String id,
            @RequestBody @Valid ClockInTypeRequest clockInTypeRequest
    ) {
        ClockInType clockInType = clockInTypeRestMapper.toDomain(clockInTypeRequest);
        clockInType.setId(id);

        clockInServicePort.updateClockInType(clockInType);

        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("Clock in type updated successfully")
                        .build()
        );
    }


    @DeleteMapping("/types/{id}")
    public ResponseEntity<ApiResponse> deleteClockInType(
            @PathVariable String id
    ) {
        clockInServicePort.deleteClockInType(id);

        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("Clock in type with id: " + id + " was deleted")
                        .build()
        );
    }

}
