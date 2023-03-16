package com.example.ensonotest.service

import org.springframework.stereotype.Service
import java.net.URI

@Service
class UserService {
    fun createUser(token: String, name: String): URI{
        TODO("verify token not null or empty then make the actual call and return the url")
    }

    fun updateUser(): URI{
        TODO("")

    }
}