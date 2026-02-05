package com.example.fichaje.infrastructure.input.rest.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClockInTypeRequest {
    @NotBlank
    private String description;
    @NotNull
    private Boolean io;
}
