package com.example.ensonotest.controllers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@ExtendWith(
    SpringExtension::class
)
@WebMvcTest(controllers = [GreetingController::class])
internal class GreetingControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun greetingReturnsHello() {
        val responseBody = mockMvc.get("/greeting")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn()
            .response.contentAsString

        assertEquals("hello world", responseBody)
    }
}