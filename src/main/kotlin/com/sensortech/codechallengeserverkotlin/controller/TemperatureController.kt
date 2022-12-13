package com.sensortech.codechallengeserverkotlin.controller

import com.sensortech.codechallengeserverkotlin.model.Sensor
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
    private val restTemplate: RestTemplate
) {

    private val logger: Logger = LoggerFactory.getLogger(TemperatureController::class.java)

    @GetMapping("/{id}")
    fun index(@PathVariable id: String): ResponseEntity<Sensor> {

        val url = String.format("https://hasydbj5c4gpa2oozfpjpc677a0hxuob.lambda-url.ap-southeast-2.on.aws/sensor/%s", id)

        val response: ResponseEntity<Sensor> = restTemplate.getForEntity(url, Sensor::class.java)

        logger.info("Response: {}", response.body)
        logger.info("Status: {}", response.statusCode)

        return ResponseEntity.ok(response.body)
    }
}