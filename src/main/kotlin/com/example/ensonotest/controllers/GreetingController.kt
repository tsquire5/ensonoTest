package com.example.ensonotest.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun hello(): String {
        return "hello world"
    }
}