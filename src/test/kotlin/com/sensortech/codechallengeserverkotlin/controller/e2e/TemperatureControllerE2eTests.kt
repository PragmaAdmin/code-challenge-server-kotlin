package com.sensortech.codechallengeserverkotlin.controller.e2e

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
internal class TemperatureControllerE2eTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun shouldReturnTheTemperaturesSuccessfully() {
        val localUrl = "https://localhost:8081/temperature"
        this.mockMvc.perform(get(localUrl))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.temperatures[0].id").isNotEmpty)
                .andExpect(jsonPath("$.temperatures[0].name").isNotEmpty)
                .andExpect(jsonPath("$.temperatures[0].temperature").isNotEmpty)
                .andExpect(jsonPath("$.temperatures[0].status").isNotEmpty)
    }

    @Test
    fun shouldReturnTheTemperatureByIdSuccessfully() {
        val localUrl = "https://localhost:8081/temperature/1"
        this.mockMvc.perform(get(localUrl))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id").isNotEmpty)
                .andExpect(jsonPath("$.temperature").isNotEmpty)
    }
}