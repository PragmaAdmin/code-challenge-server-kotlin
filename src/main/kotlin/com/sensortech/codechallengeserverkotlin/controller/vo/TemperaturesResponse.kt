package com.sensortech.codechallengeserverkotlin.controller.vo

import com.sensortech.codechallengeserverkotlin.service.model.Temperature

data class TemperaturesResponse(
    val temperatures: ArrayList<Temperature>
)
