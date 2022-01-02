package com.sensortech.codechallengeserverkotlin.controller

import com.sensortech.codechallengeserverkotlin.repository.model.Sensor
import com.sensortech.codechallengeserverkotlin.service.model.Temperature
import com.sensortech.codechallengeserverkotlin.service.model.TemperatureStatus
import com.sensortech.codechallengeserverkotlin.service.TemperatureService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.client.RestTemplate

@WebMvcTest(TemperatureController::class)
internal class TemperatureControllerTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var service: TemperatureService

    @MockBean
    lateinit var restTemplate: RestTemplate

    @Test
    fun shouldReturnTemperaturesSuccessfully() {
        val expected = arrayListOf(Temperature("1", "name", 1, TemperatureStatus.ALL_GOOD), Temperature("2", "name", 2, TemperatureStatus.TOO_LOO), Temperature("3", "name", 3, TemperatureStatus.TOO_HIGH))

        `when`(service.getTemperatures()).thenReturn(expected)

        this.mockMvc.perform(MockMvcRequestBuilders.get("/temperature"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[0].id").value(expected[0].id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[0].name").value(expected[0].name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[0].temperature").value(expected[0].temperature))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[0].status").value("all good"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[1].id").value(expected[1].id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[1].name").value(expected[1].name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[1].temperature").value(expected[1].temperature))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[1].status").value("too low"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[2].id").value(expected[2].id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[2].name").value(expected[2].name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[2].temperature").value(expected[2].temperature))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[2].status").value("too high"))

    }

    @Test
    fun shouldReturnTemperatureByIdSuccessfully() {
        val url = "https://temperature-sensor-service.herokuapp.com/sensor/1"
        val expected = Sensor("1", 2)

        `when`(restTemplate.getForEntity(url, Sensor::class.java)).thenReturn(ResponseEntity.ok(expected))

        this.mockMvc.perform(MockMvcRequestBuilders.get("/temperature/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expected.id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(expected.temperature))
    }
}