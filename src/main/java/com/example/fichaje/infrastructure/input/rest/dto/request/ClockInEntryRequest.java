package com.example.fichaje.infrastructure.input.rest.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClockInEntryRequest {
    @NotBlank
    private String clockInType;
    @NotBlank
    private String device;
    private String comment;
}
