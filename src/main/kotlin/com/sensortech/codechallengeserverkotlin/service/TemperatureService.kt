package com.sensortech.codechallengeserverkotlin.service

import com.sensortech.codechallengeserverkotlin.service.model.Product
import com.sensortech.codechallengeserverkotlin.service.model.Temperature
import com.sensortech.codechallengeserverkotlin.service.model.TemperatureStatus
import com.sensortech.codechallengeserverkotlin.repository.SensorRepository
import com.sensortech.codechallengeserverkotlin.repository.model.Sensor
import org.springframework.stereotype.Service

@Service
class TemperatureService (
        val repository: SensorRepository
        ){

    fun getTemperatures(): ArrayList<Temperature> {
        val temperatures = arrayListOf<Temperature>()

        Product.values().forEach {
            val sensor = repository.getTemperature(it.id)
            if (sensor != null) {
                val temperature = Temperature(it.id, it.productName, sensor.temperature, getTemperatureStatus(it, sensor))
                temperatures.add(temperature)
            }
        }

        return temperatures
    }

    private fun getTemperatureStatus(product: Product, sensor: Sensor): TemperatureStatus {
        val status: TemperatureStatus = if (sensor.temperature.toDouble() < product.minimumTemperature.toDouble()) {
            TemperatureStatus.TOO_LOO
        } else if (sensor.temperature.toDouble() > product.maximumTemperature.toDouble()) {
            TemperatureStatus.TOO_HIGH
        } else {
            TemperatureStatus.ALL_GOOD
        }
        return status
    }

}