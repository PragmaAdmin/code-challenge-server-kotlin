package com.sensortech.codechallengeserverkotlin.service.model

enum class Product(
        val id: String,
        val productName: String,
        val minimumTemperature: Int,
        val maximumTemperature: Int) {

    PILSNER("1", "Pilsner", 4, 6),
    IPA("2", "IPA", 5, 6),
    LAGER("3", "Lager", 4, 7),
    STOUT("4", "Stout", 6, 8),
    WHEAT_BEER("5", "Wheat Beer", 3, 5),
    PALE_ALE("6", "Pale Ale", 4, 6)

}