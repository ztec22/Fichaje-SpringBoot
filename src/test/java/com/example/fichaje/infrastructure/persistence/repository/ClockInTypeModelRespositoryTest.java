package com.example.fichaje.infrastructure.persistence.repository;

import com.example.fichaje.infrastructure.persistence.model.ClockInTypeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@DataMongoTest
class ClockInTypeModelRespositoryTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongodb = new MongoDBContainer("mongo:8.0");

    @Autowired
    ClockInTypeModelRespository clockInTypeModelRespository;

    private static final List<ClockInTypeModel> clockTypes = List.of(
            ClockInTypeModel.builder().description("Start Work").io(true).build(),
            ClockInTypeModel.builder().description("Work Break").io(false).build(),
            ClockInTypeModel.builder().description("Return to Work").io(true).build(),
            ClockInTypeModel.builder().description("Leave Work").io(false).build()
    );

    @BeforeEach
    void setup() {
        clockInTypeModelRespository.saveAll(clockTypes);
    }

    @Test
    @DisplayName("Find all Clock in types")
    void findAll() {
        assertEquals(4, clockInTypeModelRespository.findAll().size());
    }
}