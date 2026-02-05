package com.example.fichaje.infrastructure.input.rest;

import com.example.fichaje.infrastructure.output.persistence.model.ClockInTypeModel;
import com.example.fichaje.infrastructure.output.persistence.repository.ClockInTypeModelRespository;
import com.example.fichaje.infrastructure.input.rest.dto.common.ApiResponse;
import com.example.fichaje.infrastructure.input.rest.dto.request.ClockInTypeRequest;
import com.example.fichaje.infrastructure.input.rest.dto.response.ClockInTypeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClockInTypeRestAdapterIntegrationTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongodb = new MongoDBContainer("mongo:8.0");

    @Autowired
    private WebApplicationContext context;

    private RestTestClient client;

    @Autowired
    ClockInTypeModelRespository clockInTypeModelRespository;

    @BeforeEach
    public void setup(WebApplicationContext context) {
        client = RestTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void createClockInType() {
        ClockInTypeRequest clockInTypeRequest = ClockInTypeRequest.builder()
                .description("New Entry")
                .io(true)
                .build();

        ApiResponse response = client.post()
                .uri("/api/v1/clockin/types")
                .body(clockInTypeRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {})
                .returnResult()
                .getResponseBody();

        assertNotNull(response);
        assertNotNull(response.getMessage());
    }

    @Test
    void getClockInTypes() {

        List<ClockInTypeResponse> types = client.get()
                .uri("/api/v1/clockin/types")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<ClockInTypeResponse>>() {})
                .returnResult()
                .getResponseBody();

        assertNotNull(types);
        assertEquals(0, types.size());
    }

    @Test
    void updateClockInType() {

        ClockInTypeModel clockInTypeModel = ClockInTypeModel.builder().description("Start Work").io(true).build();
        clockInTypeModel = clockInTypeModelRespository.save(clockInTypeModel);

        ClockInTypeRequest clockInTypeRequest = ClockInTypeRequest.builder()
                .description("Modified")
                .io(false)
                .build();

        ApiResponse response = client.put()
                .uri("/api/v1/clockin/types/" + clockInTypeModel.getId())
                .body(clockInTypeRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {})
                .returnResult()
                .getResponseBody();

        assertNotNull(response);
        assertNotNull(response.getMessage());
    }

    @Test
    void deleteClockInType() {
        String id = UUID.randomUUID().toString();

        ApiResponse response = client.delete()
                .uri("/api/v1/clockin/types/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {})
                .returnResult()
                .getResponseBody();

        assertNotNull(response);
        assertNotNull(response.getMessage());
    }
}