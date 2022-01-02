package com.sensortech.codechallengeserverkotlin.repository

import com.sensortech.codechallengeserverkotlin.repository.model.Sensor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

private const val TEMPERATURE_URL = "https://temperature-sensor-service.herokuapp.com/sensor/%s"

@Repository
class SensorRepository (
        private val restTemplate: RestTemplate
) {

    private val logger: Logger = LoggerFactory.getLogger(SensorRepository::class.java)

    fun getTemperature(id: String): Sensor? {

        val url = String.format(TEMPERATURE_URL, id)

        logger.info("Request: {}", url)
        val response: ResponseEntity<Sensor> = restTemplate.getForEntity(url, Sensor::class.java)
        logger.info("Response Status: {}", response.statusCode)
        logger.info("Response: {}", response.body)

        return response.body
    }
}