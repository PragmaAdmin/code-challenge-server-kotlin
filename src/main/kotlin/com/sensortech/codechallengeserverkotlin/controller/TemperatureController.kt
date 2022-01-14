package com.sensortech.codechallengeserverkotlin.controller

import com.sensortech.codechallengeserverkotlin.controller.vo.TemperaturesResponse
import com.sensortech.codechallengeserverkotlin.repository.model.Sensor
import com.sensortech.codechallengeserverkotlin.service.TemperatureService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("temperature")
class TemperatureController(
    private val service: TemperatureService,
    private val restTemplate: RestTemplate
) {

    private val logger: Logger = LoggerFactory.getLogger(TemperatureController::class.java)

    @GetMapping("/{id}")
    @Deprecated("Method without design, code quality and test",  level = DeprecationLevel.WARNING )
    fun index(@PathVariable id: String): ResponseEntity<Sensor> {

        val url = String.format("https://temperature-sensor-service.herokuapp.com/sensor/%s", id)

        val response: ResponseEntity<Sensor> = restTemplate.getForEntity(url, Sensor::class.java)

        logger.info("Response: {}", response.body)
        logger.info("Status: {}", response.statusCode)

        return ResponseEntity.ok(response.body)
    }

    @GetMapping
    fun getTemperatures(): ResponseEntity<TemperaturesResponse> {
        logger.info("Get Temperatures")
        val response = TemperaturesResponse(service.getTemperaturesFromCache())
        logger.info("Response: {}", response)

        return ResponseEntity.ok(response)
    }
}