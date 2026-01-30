package com.example.fichaje.infrastructure.rest;

import com.example.fichaje.infrastructure.rest.dto.response.ClockInTypeResponse;
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


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClockInRestAdapterIntegrationTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongodb = new MongoDBContainer("mongo:8.0");

    @Autowired
    private WebApplicationContext context;

    RestTestClient client;

    @BeforeEach
    public void setup(WebApplicationContext context) {
        client = RestTestClient.bindToApplicationContext(context).build();
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
}