package com.sensortech.codechallengeserverkotlin.controller.integration

import com.sensortech.codechallengeserverkotlin.repository.model.Sensor
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.web.client.RestTemplate

@SpringBootTest
@AutoConfigureMockMvc
internal class TemperatureControllerIntegrationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var restTemplate: RestTemplate

    @Test
    fun shouldReturnTheTemperaturesSuccessfully() {
        val localUrl = "https://localhost:8081/temperature"
        val expected = Sensor("1", Integer.MIN_VALUE)

        `when`(restTemplate.getForEntity(anyString(), ArgumentMatchers.eq(Sensor::class.java))).thenReturn(ResponseEntity.ok(expected))

        this.mockMvc.perform(get(localUrl))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.temperatures[0].id").isNotEmpty)
                .andExpect(jsonPath("$.temperatures[0].name").isNotEmpty)
                .andExpect(jsonPath("$.temperatures[0].temperature").isNotEmpty)
                .andExpect(jsonPath("$.temperatures[0].status").value("too low"))
    }

    @Test
    fun shouldReturnTheTemperatureByIdSuccessfully() {
        val localUrl = "https://localhost:8081/temperature/1"
        val expected = Sensor("1", Integer.MIN_VALUE)

        `when`(restTemplate.getForEntity(anyString(), ArgumentMatchers.eq(Sensor::class.java))).thenReturn(ResponseEntity.ok(expected))

        this.mockMvc.perform(get(localUrl))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id").isNotEmpty)
                .andExpect(jsonPath("$.temperature").isNotEmpty)

    }
}