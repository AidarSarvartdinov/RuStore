package com.aidarsarvartdinov.server.rustore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class RustoreApplication

fun main(args: Array<String>) {
	runApplication<RustoreApplication>(*args)
}
