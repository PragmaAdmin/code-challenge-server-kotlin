package com.sensortech.codechallengeserverkotlin.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class TemperatureScheduleService (
        val temperatureService: TemperatureService
        ){

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    fun getTemperaturesCacheable() {
        temperatureService.putTemperaturesInTheCache();
    }

}