package com.example.fichaje.infrastructure.output.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "clock_in_entries")
public class ClockInEntryModel {
    @Id
    private String id;
    @Field(name = "type_id")
    private String clockInType;
    private LocalDate date;
    private LocalTime time;
    private String device;
    private String comment;
}
