package com.example.fichaje.infrastructure.input.rest;

import com.example.fichaje.application.ports.input.ClockInServicePort;
import com.example.fichaje.application.exceptions.ClockInTypeNotFoundException;
import com.example.fichaje.domain.model.ClockInType;
import com.example.fichaje.infrastructure.input.rest.dto.common.ApiResponse;
import com.example.fichaje.infrastructure.input.rest.dto.request.ClockInTypeRequest;
import com.example.fichaje.infrastructure.input.rest.dto.response.ClockInTypeResponse;
import com.example.fichaje.infrastructure.input.rest.mapper.ClockInEntryRestMapper;
import com.example.fichaje.infrastructure.input.rest.mapper.ClockInTypeRestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@WebMvcTest(ClockInRestAdapter.class)
@ExtendWith(MockitoExtension.class)
class ClockInRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClockInServicePort clockInServicePort;

    @MockitoBean
    private ClockInTypeRestMapper clockInTypeRestMapper;
    @MockitoBean
    private ClockInEntryRestMapper clockInEntryRestMapper;

    private static final List<ClockInTypeResponse> clockTypes = List.of(
            ClockInTypeResponse.builder().id(UUID.randomUUID().toString()).description("Start Work").io(true).build(),
            ClockInTypeResponse.builder().id(UUID.randomUUID().toString()).description("Work Break").io(false).build(),
            ClockInTypeResponse.builder().id(UUID.randomUUID().toString()).description("Return to Work").io(true).build(),
            ClockInTypeResponse.builder().id(UUID.randomUUID().toString()).description("Leave Work").io(false).build()
    );

    @Test
    void createClockInType_Ok() {
        ClockInTypeRequest clockInTypeRequest = ClockInTypeRequest.builder()
                .description("Start Work")
                .io(true)
                .build();

        RestTestClient client = RestTestClient.bindTo(mockMvc).build();

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
    void createClockInType_BadRequest() {

        RestTestClient client = RestTestClient.bindTo(mockMvc).build();

        client.post()
                .uri("/api/v1/clockin/types")
                .body(new ClockInTypeRequest())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.error").exists();
    }

    @Test
    void getClockInTypes_Ok() {

        when(clockInTypeRestMapper.toDtoList(anyList())).thenReturn(clockTypes);

        RestTestClient client = RestTestClient.bindTo(mockMvc).build();

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
    void updateClockInType_Ok() {
        String id = UUID.randomUUID().toString();
        ClockInTypeRequest clockInTypeRequest = ClockInTypeRequest.builder()
                .description("New Entry")
                .io(true)
                .build();

        when(clockInTypeRestMapper.toDomain(any(ClockInTypeRequest.class)))
                .thenReturn(new ClockInType());

        RestTestClient client = RestTestClient.bindTo(mockMvc).build();

        ApiResponse response = client.put()
                .uri("/api/v1/clockin/types/" + id)
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
    void updateClockInType_BadRequest() {
        String id = UUID.randomUUID().toString();

        RestTestClient client = RestTestClient.bindTo(mockMvc).build();

        client.put()
                .uri("/api/v1/clockin/types/" + id)
                .body(new ClockInTypeRequest())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.error").exists();
    }

    @Test
    void updateClockInType_NotFound() {
        String id = UUID.randomUUID().toString();
        ClockInTypeRequest clockInTypeRequest = ClockInTypeRequest.builder()
                .description("New Entry")
                .io(true)
                .build();

        when(clockInTypeRestMapper.toDomain(any(ClockInTypeRequest.class)))
                .thenReturn(new ClockInType());

        doThrow(new ClockInTypeNotFoundException())
                .when(clockInServicePort)
                .updateClockInType(any(ClockInType.class));

        RestTestClient client = RestTestClient.bindTo(mockMvc).build();

        client.put()
                .uri("/api/v1/clockin/types/" + id)
                .body(clockInTypeRequest)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.error").exists();
    }

    @Test
    void deleteClockInType_Ok() {
        String id = UUID.randomUUID().toString();

        RestTestClient client = RestTestClient.bindTo(mockMvc).build();
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