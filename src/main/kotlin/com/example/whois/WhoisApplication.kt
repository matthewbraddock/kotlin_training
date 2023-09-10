package com.example.whois

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment

@SpringBootApplication
class WhoisApplication

fun main(args: Array<String>) {
	val applicationContext = runApplication<WhoisApplication>(*args)
	val environment: Environment = applicationContext.environment

	val serverPort = environment.getProperty("server.port")
	val serverContextPath = environment.getProperty("server.servlet.context-path")

	println("Spring Boot application is running at: http://localhost:$serverPort$serverContextPath")
}
