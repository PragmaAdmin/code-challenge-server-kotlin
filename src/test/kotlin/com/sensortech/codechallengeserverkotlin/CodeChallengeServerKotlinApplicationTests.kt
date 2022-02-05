package com.sensortech.codechallengeserverkotlin

import com.sensortech.codechallengeserverkotlin.controller.TemperatureController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CodeChallengeServerKotlinApplicationTests {
	@Autowired
	lateinit var controller:TemperatureController

	@Test
	fun contextLoads() {
		assertThat(controller).isNotNull
	}

}
