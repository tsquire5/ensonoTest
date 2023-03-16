package com.example.ensonotest.controllers

import com.example.ensonotest.dto.CreateUserDTO
import com.example.ensonotest.service.AuthTokenService
import com.example.ensonotest.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val authTokenService: AuthTokenService, val userService: UserService) {

    @PostMapping("/user")
    fun createUser(@RequestBody userDTO: CreateUserDTO): String {
        val token = authTokenService.getToken()
        with(userDTO){
            return userService.createUser(token, name).toString()
        }
    }

    @PutMapping("/user")
    fun updateUser(): String {
        return "hello world"
    }
}