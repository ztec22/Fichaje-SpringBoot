package com.example.fichaje.domain.model;


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
public class ClockInEntry {
    private String id;
    private ClockInType clockInType;
    private LocalDate date;
    private LocalTime time;
    private String device;
    private String comment;
}
