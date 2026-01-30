package com.example.fichaje.infrastructure.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClockInTypeResponse {
    private String id;
    private String description;
    private boolean io;
}
