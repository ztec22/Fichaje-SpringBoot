package com.example.fichaje.infrastructure.rest;


import com.example.fichaje.application.ports.input.ClockInServicePort;
import com.example.fichaje.infrastructure.rest.dto.response.ClockInTypeResponse;
import com.example.fichaje.infrastructure.rest.mapper.ClockInTypeRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clockin")
@RequiredArgsConstructor
public class ClockInRestAdapter {

    private final ClockInServicePort clockInServicePort;
    private final ClockInTypeRestMapper clockInTypeRestMapper;

    @GetMapping("/types")
    public List<ClockInTypeResponse> getClockInTypes() {
        return clockInTypeRestMapper.toDtoList(clockInServicePort.getClockInTypes());
    }

}
