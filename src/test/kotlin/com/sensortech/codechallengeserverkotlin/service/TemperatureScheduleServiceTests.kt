package com.sensortech.codechallengeserverkotlin.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class TemperatureScheduleServiceTests {
    @InjectMocks
    lateinit var service: TemperatureScheduleService

    @Mock
    lateinit var temperatureService: TemperatureService

    @Test
    fun shouldReturnAllTemperaturesSuccessfully(){
        service.getTemperaturesCacheable()

        verify(temperatureService, times(1)).putTemperaturesInTheCache()
    }
}