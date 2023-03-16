package com.example.ensonotest.service

import com.squareup.okhttp.Call
import com.squareup.okhttp.OkHttpClient
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AuthTokenServiceTest{

    val endpoint: String = "http://someendpoint.net"
    val client: OkHttpClient = mockk()
    val token = "sometoken"
    @Test
    fun getTokenReturnsTokenWhenServiceWorks(){
        val mockCall: Call = mockk()
        every { client.newCall(any()) }.returns(mockCall)
        every { mockCall.enqueue(any()) }.answers {

        }
        val target = AuthTokenService(endpoint, client)
        val response = target.getToken()
        assertEquals(token, response)
    }

    //TODO Some testing of the logging
}
