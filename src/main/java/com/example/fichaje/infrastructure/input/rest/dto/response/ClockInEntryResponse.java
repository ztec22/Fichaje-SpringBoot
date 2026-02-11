package com.example.fichaje.infrastructure.input.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClockInEntryResponse {
    private String id;
    private ClockInTypeResponse clockInType;
    private LocalDate date;
    private LocalTime time;
    private String device;
    private String comment;
}
