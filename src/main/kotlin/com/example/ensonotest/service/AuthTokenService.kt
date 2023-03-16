package com.example.ensonotest.service

import com.squareup.okhttp.Call
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class AuthTokenService(@Value("auth.token.url")val endpoint: String, val client: OkHttpClient) {

    //Will throw up the exception here where there is an error
    fun getToken(): String {
        val request = Request.Builder()
            .url(endpoint)
            .build()

        var token: String? = null

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                TODO("some nice error handling here")
                throw RuntimeException(e!!.message)
            }

            override fun onResponse(response: Response?) {
                TODO("handle other statuses")
                token = response?.body()?.string() ?: TODO("some nice error handling here")
            }
        })
        return token ?: TODO("some nice error handling here")
    }
}