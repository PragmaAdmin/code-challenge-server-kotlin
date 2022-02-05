package com.sensortech.codechallengeserverkotlin.service.model

import com.fasterxml.jackson.annotation.JsonProperty

enum class TemperatureStatus {
    @JsonProperty("too low")
    TOO_LOO,
    @JsonProperty("too high")
    TOO_HIGH,
    @JsonProperty("all good")
    ALL_GOOD
}
