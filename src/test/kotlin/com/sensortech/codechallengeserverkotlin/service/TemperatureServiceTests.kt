package com.sensortech.codechallengeserverkotlin.service

import com.sensortech.codechallengeserverkotlin.service.model.Product
import com.sensortech.codechallengeserverkotlin.service.model.TemperatureStatus
import com.sensortech.codechallengeserverkotlin.repository.SensorRepository
import com.sensortech.codechallengeserverkotlin.repository.model.Sensor
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class TemperatureServiceTests {
    @InjectMocks
    lateinit var service: TemperatureService

    @Mock
    lateinit var repository: SensorRepository

    @Test
    fun shouldReturnAllTemperaturesSuccessfully(){
        `when`(repository.getTemperature(anyString())).thenReturn(Sensor("", Math.random()))

        val temperatures = service.getTemperatures()

        assertEquals(6, temperatures.size)
    }

    @Test
    fun shouldReturnStatusTooLow(){
        val temperature = Product.PILSNER.minimumTemperature - 0.1

        `when`(repository.getTemperature(anyString())).thenReturn(Sensor("", temperature))

        val temperatures = service.getTemperatures()

        assertEquals(TemperatureStatus.TOO_LOO, temperatures[0].status)
    }

    @Test
    fun shouldReturnStatusTooHigh(){
        val temperature = Product.IPA.maximumTemperature + 0.1

        `when`(repository.getTemperature(anyString())).thenReturn(Sensor("", temperature))

        val temperatures = service.getTemperatures()

        assertEquals(TemperatureStatus.TOO_HIGH, temperatures[1].status)
    }

    @Test
    fun shouldReturnStatusAllGood(){
        val temperature = Product.LAGER.minimumTemperature + 0.1

        `when`(repository.getTemperature(anyString())).thenReturn(Sensor("", temperature))

        val temperatures = service.getTemperatures()

        assertEquals(TemperatureStatus.ALL_GOOD, temperatures[2].status)
    }

    @Test
    fun shouldReturnEmptyListWhenWeCanNotGetTheSensorData(){
        `when`(repository.getTemperature(anyString())).thenReturn(null)

        val temperatures = service.getTemperatures()

        assertEquals(0, temperatures.size)
    }
}