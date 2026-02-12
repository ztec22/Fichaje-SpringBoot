package com.example.fichaje.infrastructure.input.rest;

import com.example.fichaje.domain.enums.DeviceEnum;
import com.example.fichaje.infrastructure.input.rest.dto.request.ClockInEntryRequest;
import com.example.fichaje.infrastructure.input.rest.dto.response.ClockInEntryResponse;
import com.example.fichaje.infrastructure.output.persistence.model.ClockInTypeModel;
import com.example.fichaje.infrastructure.output.persistence.repository.ClockInEntryModelRespository;
import com.example.fichaje.infrastructure.output.persistence.repository.ClockInTypeModelRespository;
import com.example.fichaje.infrastructure.input.rest.dto.common.ApiResponse;
import com.example.fichaje.infrastructure.input.rest.dto.request.ClockInTypeRequest;
import com.example.fichaje.infrastructure.input.rest.dto.response.ClockInTypeResponse;
import org.bson.types.ObjectId;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClockInRestAdapterIntegrationTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongodb = new MongoDBContainer("mongo:8.0");

    @Autowired
    private WebApplicationContext context;

    private RestTestClient client;

    @Autowired
    ClockInTypeModelRespository clockInTypeModelRespository;

    @Autowired
    ClockInEntryModelRespository clockInEntryModelRespository;

    private static final List<ClockInTypeModel> clockTypes = List.of(
            ClockInTypeModel.builder().id(new ObjectId().toHexString()).description("Start Work").io(true).build(),
            ClockInTypeModel.builder().id(new ObjectId().toHexString()).description("Work Break").io(false).build(),
            ClockInTypeModel.builder().id(new ObjectId().toHexString()).description("Return to Work").io(true).build(),
            ClockInTypeModel.builder().id(new ObjectId().toHexString()).description("Leave Work").io(false).build()
    );

    @BeforeEach
    public void setup(WebApplicationContext context) {
        client = RestTestClient.bindToApplicationContext(context).build();
        clockInTypeModelRespository.saveAll(clockTypes);
    }

    @Test
    void createClockInEntryAndRetriveAllClockInEntries() {
        //createClockInEntry
        ClockInEntryRequest clockInEntryRequest = ClockInEntryRequest.builder()
                .clockInType(clockTypes.getFirst().getId())
                .device(DeviceEnum.PC.toString())
                .build();

        ApiResponse createResponse = client.post()
                .uri("/api/v1/clockin")
                .body(clockInEntryRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {})
                .returnResult()
                .getResponseBody();

        assertNotNull(createResponse);
        assertNotNull(createResponse.getMessage());

        //retriveAllClockInEntries
        List<ClockInEntryResponse> entries = client.get()
                .uri("/api/v1/clockin")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<ClockInEntryResponse>>() {})
                .returnResult()
                .getResponseBody();

        assertNotNull(entries);
        assertEquals(1, entries.size());

        ClockInEntryResponse entry = entries.getFirst();
        assertNotNull(entry);
        assertNotNull(entry.getId());

        assertNotNull(entry.getClockInType());
        assertNotNull(entry.getClockInType().getId());
        assertNotNull(entry.getClockInType().getDescription());

        assertNotNull(entry.getDate());
        assertNotNull(entry.getTime());
        assertNotNull(entry.getDevice());
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
        assertEquals(4, types.size());
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
        String id = new ObjectId().toHexString();

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