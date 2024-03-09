package com.example.genus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GenusApplication

fun main(args: Array<String>) {
	runApplication<GenusApplication>(*args)
}
