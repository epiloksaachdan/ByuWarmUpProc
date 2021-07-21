package com.example.postappapi

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("x-auth-key", "26dbdc93-a0a8-4a9d-9296-5c4d6482eec2")
            .build()
        return chain.proceed(request)
    }
}