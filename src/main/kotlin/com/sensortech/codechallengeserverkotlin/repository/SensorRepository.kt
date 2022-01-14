package com.sensortech.codechallengeserverkotlin.repository

import com.sensortech.codechallengeserverkotlin.repository.model.Sensor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class SensorRepository (
        private val restTemplate: RestTemplate,
        @Value("\${client.url}")
        private var url: String
) {

    private val logger: Logger = LoggerFactory.getLogger(SensorRepository::class.java)

    fun getTemperature(id: String): Sensor? {

        val urlFormatted = String.format(url, id)

        logger.info("Request: {}", urlFormatted)
        val response: ResponseEntity<Sensor> = restTemplate.getForEntity(urlFormatted, Sensor::class.java)
        logger.info("Response Status: {}", response.statusCode)
        logger.info("Response: {}", response.body)

        return response.body
    }
}