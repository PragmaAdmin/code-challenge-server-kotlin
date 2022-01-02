package com.sensortech.codechallengeserverkotlin.repository

import com.sensortech.codechallengeserverkotlin.repository.model.Sensor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@ExtendWith(MockitoExtension::class)
internal class SensorRepositoryTests {

    @InjectMocks
    lateinit var repository: SensorRepository

    @Mock
    lateinit var restTemplate: RestTemplate

    @Test
    fun shouldReturnTemperatureSuccessfully() {
        val url = "https://temperature-sensor-service.herokuapp.com/sensor/1"
        val expected = Sensor("1", 2)

        `when`(restTemplate.getForEntity(url, Sensor::class.java)).thenReturn(ResponseEntity.ok(expected))

        val response = repository.getTemperature(expected.id)

        assertEquals(expected, response)
    }

    @Test
    fun shouldReturnNullWhenTemperatureCanNotBeFounded() {
        `when`(restTemplate.getForEntity(anyString(), eq(Sensor::class.java))).thenReturn(ResponseEntity.ok(null))

        val response = repository.getTemperature("1")

        assertNull(response)
    }
}