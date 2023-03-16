package com.example.ensonotest.controllers

import com.example.ensonotest.dto.CreateUserDTO
import com.example.ensonotest.service.AuthTokenService
import com.example.ensonotest.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import java.lang.RuntimeException
import java.net.URI

@ExtendWith(
    SpringExtension::class
)
@WebMvcTest(controllers = [UserController::class])
internal class UserControllerTest{

    private val someToken = "sometoken"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var authTokenService: AuthTokenService

    @MockBean
    private lateinit var userService: UserService

    /*
    TODO some more testing involving failing downstream apis and how that is handled
    @Test
    fun whenAuthTokenFailsThen500Returned() {
        Mockito.`when`(authTokenService.getToken()).thenThrow(RuntimeException("service down"))
        val responseBody = mockMvc.post("/user")
            .andExpect { status { isInternalServerError() } }
            .andReturn()
            .response.contentAsString
    }
*/

    //TODO a test that verifies incorrect input for example a blank name

    @Test
    fun createUserWorks() {
        val name = "mr person"
        val newUserUrl = "http://www.someurl.com/user/1"
        Mockito.`when`(authTokenService.getToken()).thenReturn(someToken)
        Mockito.`when`(userService.createUser(someToken, name)).thenReturn(URI.create(newUserUrl))
        val responseBody = mockMvc.post("/user"){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateUserDTO(name))
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { is2xxSuccessful() } }
            .andReturn()
            .response.contentAsString

        Mockito.verify(userService).createUser(someToken, name)
        assertEquals(newUserUrl, responseBody)
    }


    @Test
    fun updateUserWorks() {
        val responseBody = mockMvc.put("/user")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn()
            .response.contentAsString
    }
}