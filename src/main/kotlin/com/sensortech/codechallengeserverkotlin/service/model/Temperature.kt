package com.sensortech.codechallengeserverkotlin.service.model

data class Temperature(
    val id: String,
    val name: String,
    val temperature: Number,
    val status: TemperatureStatus
)
